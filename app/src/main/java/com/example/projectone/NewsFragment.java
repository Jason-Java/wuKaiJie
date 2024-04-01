package com.example.projectone;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectone.adapter.NewsAdapter;
import com.example.projectone.table.News;
import com.scwang.smart.refresh.footer.BallPulseFooter;
import com.scwang.smart.refresh.header.BezierRadarHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import org.litepal.LitePal;
import org.litepal.tablemanager.Connector;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class NewsFragment extends Fragment {
    private List<News> data = new ArrayList<>();
    View view;
    RecyclerView recyclerView;
    NewsAdapter newsAdapter;

    EditText news_et;
    Button news_bt;

    SmartRefreshLayout smartRefreshLayout;
    int refreshitem;
    News news;
    private List<News> refreshdata = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.news_fragment,null);

        //模拟数据
//        initData();

        //读取数据库数据,倒叙时间排列
        Connector.getDatabase();
        data = LitePal.order("date desc").find(News.class);
        //初始页面item
        refreshitem = data.size()>=10?10:data.size();
        for (int i = 0; i < refreshitem; i++) {
            news = data.get(i);
            refreshdata.add(news);
        }



        //初始化rescycleciew
        initRecyclerView();
        //设置layoutManager,可以设置显示效果，是线性布局、grid布局，还是瀑布流布局
        //参数是：上下文、列表方向（横向还是纵向）、是否倒叙
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));

        //设置item的分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));

        //搜索
        news_et = view.findViewById(R.id.news_et);
        news_bt = view.findViewById(R.id.news_bt);
        news_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = news_et.getText().toString();
                data = LitePal.where("title like ? or content like ?","%"+content+"%","%"+content+"%").order("date desc").find(News.class);
                //初始页面item
                refreshdata = new ArrayList<>();
                refreshitem = data.size()>=10?10:data.size();
                for (int i = 0; i < refreshitem; i++) {
                    news = data.get(i);
                    refreshdata.add(news);
                }
                initRecyclerView();

            }
        });

        //下拉刷新
        smartRefreshLayout = view.findViewById(R.id.news_refresh);
        //设置头部刷新的样式
        smartRefreshLayout.setRefreshHeader(new BezierRadarHeader(getActivity()));
        //设置页脚刷新的样式
        smartRefreshLayout.setRefreshFooter(new BallPulseFooter(getActivity()));
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {

                smartRefreshLayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
                newsAdapter.notifyDataSetChanged();
                Toast.makeText(getActivity(), "刷新成功", Toast.LENGTH_SHORT).show();
            }
        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                smartRefreshLayout.finishLoadMore(2000);
                if (refreshitem+10 <= data.size()){
                    for (int i = refreshitem; i < refreshitem+10; i++) {
                        news = data.get(i);
                        refreshdata.add(news);
                    }
                    refreshitem += 10;
                    Toast.makeText(getActivity(), "刷新成功", Toast.LENGTH_SHORT).show();
                }else if (refreshitem < data.size() && refreshitem+10 > data.size()){
                    for (int i = refreshitem; i < data.size(); i++) {
                        news = data.get(i);
                        refreshdata.add(news);
                    }
                    refreshitem = data.size();
                    Toast.makeText(getActivity(), "刷新成功", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getActivity(), "没有更多了", Toast.LENGTH_SHORT).show();
                }
                newsAdapter.notifyDataSetChanged();
            }
        });
        return view;
    }

    private void initRecyclerView() {
        recyclerView = view.findViewById(R.id.news_list);

        newsAdapter = new NewsAdapter(refreshdata,getActivity());

        recyclerView.setAdapter(newsAdapter);

    }

    String date;
    DateFormat format;
    private void initData() {
        format = new SimpleDateFormat("yyyy-MM-dd");

//        News new1 = new News();
//        new1.setImage("https://p2.img.cctvpic.com/photoworkspace/2024/02/19/2024021913400460601.jpg");
//        new1.setTitle("多哈游泳世锦赛收官 中国游泳队创近30年最佳战绩");
//        new1.setContent("中新网北京2月19日电(记者 刘星晨)北京时间19日凌晨，2024多哈游泳世锦赛落幕，中国队以23金位居总奖牌榜榜首，其中跳水项目9金4银，花样游泳项目7金1银1铜。");
//        try {
//            date = "2024-02-19";
//            new1.setDate(format.parse(date));
//        }catch (Exception e){
//            Log.e("looknews", "写入日期失败 ");
//        }
//        new1.setUrl("https://sports.cctv.com/2024/02/19/ARTIMkzEM7MbtXCZ792HgwHX240219.shtml");
//        new1.save();
//
//        News new2 = new News();
//        new2.setImage("https://p1.img.cctvpic.com/photoworkspace/2024/03/22/2024032207553824248.jpg");
//        new2.setTitle("世界泳联花样游泳世界杯（北京站）将于4月举办");
//        new2.setContent("在今年初的多哈游泳世锦赛花样游泳全部11个项目争夺中，中国花样游泳队摘得7金1银1铜，创造世锦赛参赛以来最佳成绩。本次世界杯北京站，中国花样游泳队将有25名队员出战。");
//        try {
//            date = "2024-03-22";
//            new2.setDate(format.parse(date));
//        }catch (Exception e){
//            Log.e("looknews", "写入日期失败 ");
//        }
//        new2.setUrl("https://sports.cctv.com/2024/03/22/ARTI7XKbCSD9AnNdgdu9qNlJ240322.shtml");
//        new2.save();
//
//
//        News new3 = new News();
//        new3.setImage("https://p4.img.cctvpic.com/photoworkspace/2024/02/04/2024020421130648083.jpg");
//        new3.setTitle("中国队赢得游泳世锦赛花样游泳集体技巧自选金牌");
//        new3.setContent("视网消息：北京时间2月4日，在卡塔尔多哈举行的世界游泳锦标赛花样游泳集体技巧自选决赛中，中国队赢得冠军。");
//        try {
//            date = "2024-02-24";
//            new3.setDate(format.parse(date));
//        }catch (Exception e){
//            Log.e("looknews", "写入日期失败 ");
//        }
//        new3.setUrl("hhttps://sports.cctv.com/2024/02/04/ARTI2jSYeJFiife7SRE6riyq240204.shtml");
//        new3.save();
//
//
//        News new4 = new News();
//        new4.setImage("https://p1.img.cctvpic.com/photoworkspace/2023/12/23/2023122303305348961.jpg");
//        new4.setTitle("国家体育总局游泳中心公示《2024年巴黎奥运会游泳项目选拔办法》");
//        new4.setContent("根据《选拔办法》，选拔运动员的赛事依据来自三个赛事：2023年福冈世界游泳锦标赛、2024年多哈世界游泳锦标赛的各单项决赛成绩以及2024年全国游泳冠军赛成绩。");
//        try {
//            date = "2023-12-23";
//            new4.setDate(format.parse(date));
//        }catch (Exception e){
//            Log.e("looknews", "写入日期失败 ");
//        }
//        new4.setUrl("https://news.cctv.com/2023/12/23/ARTIFqAHXSPzuNVnw2gfI8Lt231223.shtml");
//        new4.save();
//
//
//        News new5 = new News();
//        new5.setImage("https://p3.img.cctvpic.com/photoworkspace/2024/02/17/2024021710594350857.jpg");
//        new5.setTitle("游泳世锦赛：中国队“逆转之夜”勇夺两金");
//        new5.setContent("截至目前，中国游泳队以6金2银1铜位列金牌榜第二位，金牌数已经超过福冈世锦赛。");
//        try {
//            date = "2024-02-17";
//            new5.setDate(format.parse(date));
//        }catch (Exception e){
//            Log.e("looknews", "写入日期失败 ");
//        }
//        new5.setUrl("https://news.cctv.com/2024/02/17/ARTI1nCoojViWtkL35ztsTZ6240217.shtml");
//        new5.save();
//
//
//        News new6 = new News();
//        new6.setImage("https://p3.img.cctvpic.com/photoworkspace/2024/02/19/2024021913361467678.jpg");
//        new6.setTitle("多哈游泳世锦赛：中国队双榜第一 收获颇丰");
//        new6.setContent("中国队所斩获的33枚奖牌来自3个大项，其中跳水项目9金4银，花样游泳项目7金1银1铜，游泳项目7金3银1铜。");
//        try {
//            date = "2024-02-19";
//            new6.setDate(format.parse(date));
//        }catch (Exception e){
//            Log.e("looknews", "写入日期失败 ");
//        }
//        new6.setUrl("https://sports.cctv.com/2024/02/19/ARTIHS7aTIQVd59HQSCDQHj3240219.shtml");
//        new6.save();
//
//
//        News new7 = new News();
//        new7.setImage("https://p3.img.cctvpic.com/photoworkspace/2024/01/06/2024010608200938801.jpg");
//        new7.setTitle("北京力争成功申办田径、游泳世锦赛");
//        new7.setContent("游泳世锦赛曾于2011年首次在中国举办，由上海承办。");
//        try {
//            date = "2024-01-06";
//            new7.setDate(format.parse(date));
//        }catch (Exception e){
//            Log.e("looknews", "写入日期失败 ");
//        }
//        new7.setUrl("https://sports.cctv.com/2024/01/06/ARTI1brsDc4A1hGDNHvK2qfP240106.shtml");
//        new7.save();
//
//
//        News new8 = new News();
//        new8.setImage("https://p5.img.cctvpic.com/photoworkspace/2024/02/11/2024021119343025962.jpg");
//        new8.setTitle("北京将举办2029年世界游泳锦标赛");
//        new8.setContent("世界泳联主席侯赛因·阿尔-穆萨拉姆当天在新闻发布会上宣布，届时北京游泳世锦赛将举办游泳、跳水、花样游泳、水球、公开水域游泳和高台跳水共6个大项的比赛。　");
//        try {
//            date = "2024-02-11";
//            new8.setDate(format.parse(date));
//        }catch (Exception e){
//            Log.e("looknews", "写入日期失败 ");
//        }
//        new8.setUrl("https://news.cctv.com/2024/02/11/ARTIuiv7AzTuGC3I2UgQkFvm240211.shtml");
//        new8.save();
//
//
//        News new9 = new News();
//        new9.setImage("https://p5.img.cctvpic.com/photoworkspace/2024/02/12/2024021208522226460.jpg");
//        new9.setTitle("北京将举办2029年世界游泳锦标赛");
//        new9.setContent("世界游泳锦标赛是世界泳联主办的世界顶级游泳赛事，代表世界游泳运动的最高水平，共设游泳、跳水、花样游泳、水球、公开水域游泳、高台跳水六大项目。");
//        try {
//            date = "2024-02-12";
//            new9.setDate(format.parse(date));
//        }catch (Exception e){
//            Log.e("looknews", "写入日期失败 ");
//        }
//        new9.setUrl("https://sports.cctv.com/2024/02/12/ARTIUuNW15tJ6b8Jkehk9waS240212.shtml");
//        new9.save();
//
//        News new10 = new News();
//        new10.setImage("https://p2.img.cctvpic.com/photoworkspace/2024/02/08/2024020808414985185.jpg");
//        new10.setTitle("游泳世锦赛男子3米板 王宗源夺冠谢思埸收获银牌");
//        new10.setContent("游泳世锦赛男子3米板 王宗源夺冠谢思埸银牌");
//        try {
//            date = "2024-02-08";
//            new10.setDate(format.parse(date));
//        }catch (Exception e){
//            Log.e("looknews", "写入日期失败 ");
//        }
//        new10.setUrl("https://sports.cctv.com/2024/02/08/ARTIKLiXJw4xXqeGPz4ODqJS240208.shtml");
//        new10.save();

        News new11 = new News();
        new11.setImage("https://p3.img.cctvpic.com/photoworkspace/2023/10/24/2023102414234049920.jpg");
        new11.setTitle("亚残运游泳：“飞鱼”入水 逐浪前行");
        new11.setContent("杭州亚残运会项目科普：游泳他们有的失去视力，依靠\"敲头提醒\"完成转身和到边；有的没有双臂，需要咬住毛巾完成出发动作，到达终点时用头撞向池壁，拼搏到底。");
        try {
            date = "2024-02-19";
            new11.setDate(format.parse(date));
        }catch (Exception e){
            Log.e("looknews", "写入日期失败 ");
        }
        new11.setUrl("https://sports.cctv.com/2023/10/24/ARTIbVNpVUNt91pcl5FyGjzf231024.shtml");
        new11.save();

        News new12 = new News();
        new12.setImage("https://p1.img.cctvpic.com/photoworkspace/2024/02/19/2024021913154119174.jpg");
        new12.setTitle("新增11位世界冠军 中国游泳的黄金时代");
        new12.setContent("7金3银1铜共11枚奖牌，\"小鬼当家\"的中国游泳队创造了1994年世锦赛以来的历史最佳战绩，排名游泳奖牌榜第二。这是游泳世锦赛首次在北半球的冬季举行，且首度与奥运会同年举办。");
        try {
            date = "2024-02-20";
            new12.setDate(format.parse(date));
        }catch (Exception e){
            Log.e("looknews", "写入日期失败 ");
        }
        new12.setUrl("https://sports.cctv.com/2024/02/19/ARTIcE0NDBXvMwTpgzglUqMN240219.shtml");
        new12.save();

        News new13 = new News();
        new13.setImage("https://p2.img.cctvpic.com/photoworkspace/2024/02/19/2024021920393096163.jpg");
        new13.setTitle("游泳世锦赛中国队23金收官 奖牌榜第一");
        new13.setContent("新华社多哈2月19日电（记者刘旸、周欣、孙哲）2024多哈游泳世锦赛18日结束全部赛程，中国队在跳水、花样游泳和游泳大项中共收获23金8银2铜，位居奖牌榜首位。　");
        try {
            date = "2024-02-19";
            new13.setDate(format.parse(date));
        }catch (Exception e){
            Log.e("looknews", "写入日期失败 ");
        }
        new13.setUrl("https://news.cctv.com/2024/02/19/ARTIQHd9B94MDZnbE5vAzxw2240219.shtml");
        new13.save();

        News new14 = new News();
        new14.setImage("https://p2.img.cctvpic.com/photoworkspace/2024/02/20/2024022009254984800.jpg");
        new14.setTitle("中国游泳队多哈世锦赛练兵成功 “00后”值得期待");
        new14.setContent("当然，因为这是首次在冬季、在奥运年举办的游泳世界锦标赛，有大批国际泳坛高手缺席，其中也包括中国名将覃海洋、张雨霏、汪顺、杨浚瑄、叶诗文、徐嘉余等人，竞争强度有所下降，但冠军就是冠军，中国游泳队获得的成绩同样可圈可点");
        try {
            date = "2024-02-20";
            new14.setDate(format.parse(date));
        }catch (Exception e){
            Log.e("looknews", "写入日期失败 ");
        }
        new14.setUrl("https://sports.cctv.com/2024/02/20/ARTI3iy7DJOfwax3bhYA4l9f240220.shtml");
        new14.save();

        News new15 = new News();
        new15.setImage("https://p2.img.cctvpic.com/photoworkspace/2024/02/10/2024021009075621084.jpg");
        new15.setTitle("中国花样游泳队获世锦赛集体自由自选冠军");
        new15.setContent("央视网消息：多哈世界泳联花样游泳集体自由自选决赛，中国队完美表现，以339.7604分获得冠军。日本队和美国队分别获得银牌和铜牌。");
        try {
            date = "2024-02-10";
            new15.setDate(format.parse(date));
        }catch (Exception e){
            Log.e("looknews", "写入日期失败 ");
        }
        new15.setUrl("https://sports.cctv.com/2024/02/10/ARTIBDAQQLVtty5xZVtfsoOe240210.shtml");
        new15.save();

        News new16 = new News();
        new16.setImage("https://p5.img.cctvpic.com/photoworkspace/2024/02/11/2024021116501828040.jpg");
        new16.setTitle("2029年世界游泳锦标赛将在北京举行");
        new16.setContent("新华社多哈2月11日电 11日，世界泳联宣布，2029年世界游泳锦标赛将在中国北京举行。");
        try {
            date = "2024-02-11";
            new16.setDate(format.parse(date));
        }catch (Exception e){
            Log.e("looknews", "写入日期失败 ");
        }
        new16.setUrl("https://news.cctv.com/2024/02/11/ARTI5KkRXyv2qMHlfVUIBWQ0240211.shtml");
        new16.save();

        News new17 = new News();
        new17.setImage("https://p1.img.cctvpic.com/photoworkspace/2024/02/04/2024020409294372492.jpg");
        new17.setTitle("张家齐/黄建杰赢得游泳世锦赛跳水混双10米台冠军");
        new17.setContent("央视网消息：北京时间2月3日，在卡塔尔多哈举行的世界游泳锦标赛跳水项目混合双人10米台决赛中，奥运冠军张家齐和不满14岁的黄建杰搭档赢得冠军，这是中国队在本届世锦赛收获的第一金。");
        try {
            date = "2024-02-04";
            new17.setDate(format.parse(date));
        }catch (Exception e){
            Log.e("looknews", "写入日期失败 ");
        }
        new17.setUrl("https://sports.cctv.com/2024/02/04/ARTITpWnAYxLvQF1IVE6ahYC240204.shtml");
        new17.save();

        News new18 = new News();
        new18.setImage("https://p3.img.cctvpic.com/photoworkspace/2024/02/09/2024020921413844543.jpg");
        new18.setTitle("新华社快讯｜中国花样游泳队获世锦赛集体自由自选冠军");
        new18.setContent("新华社多哈2月9日电在卡塔尔多哈举行的世界游泳锦标赛花样游泳集体自由自选决赛中，中国队收获金牌。");
        try {
            date = "2024-02-09";
            new18.setDate(format.parse(date));
        }catch (Exception e){
            Log.e("looknews", "写入日期失败 ");
        }
        new18.setUrl("https://news.cctv.com/2024/02/09/ARTIcZWNRRKIFYw8OkDJOyG0240209.shtml");
        new18.save();

        News new19 = new News();
        new19.setImage("https://p4.img.cctvpic.com/photoworkspace/2024/02/12/2024021209411599217.jpg");
        new19.setTitle("游泳世锦赛-男子4X100米自由泳接力中国队夺冠");
        new19.setContent("央视网消息：在卡塔尔多哈进行的2024年世界游泳锦标赛游泳项目男子4X100米自由泳接力决赛中，中国队以3分11秒08的成绩夺冠。意大利队和美国队分获银牌和铜牌。");
        try {
            date = "2024-02-12";
            new19.setDate(format.parse(date));
        }catch (Exception e){
            Log.e("looknews", "写入日期失败 ");
        }
        new19.setUrl("https://sports.cctv.com/2024/02/12/ARTIXUn1rFLiPDbo3NNUujHh240212.shtml");
        new19.save();

        News new20 = new News();
        new20.setImage("https://p1.img.cctvpic.com/photoworkspace/2024/02/16/2024021608574159091.jpg");
        new20.setTitle("中国队夺游泳世锦赛女子4×200米自由泳接力金牌");
        new20.setContent("中国队夺游泳世锦赛女子4×200米自由泳接力金牌中国队第一棒艾衍含第六个交接，第二棒14岁小将龚真琦稳定发挥帮助中国队提升一个名次，第三棒的李冰洁游出1分54秒59的好成绩，帮助中国队反超到第一位");
        try {
            date = "2024-02-16";
            new20.setDate(format.parse(date));
        }catch (Exception e){
            Log.e("looknews", "写入日期失败 ");
        }
        new20.setUrl("https://sports.cctv.com/2024/02/16/ARTIpd5LTDIrb2D5vg4wk7vk240216.shtml");
        new20.save();

        News new21 = new News();
        new21.setImage("https://p1.img.cctvpic.com/photoworkspace/2023/06/15/2023061510570253664.jpg");
        new21.setTitle("国家游泳中心（水立方）：恢复游泳、嬉水乐园等水上项目运营");
        new21.setContent("国家游泳中心（水立方）6月14日发布消息称，恢复水上项目运营，包括游泳、嬉水乐园、蓝旗亲子游泳等业态。　　此前，国家游泳中心（水立方）因场馆外围市政供热管道破裂，影响场馆供热，暂停部分业态营业。");
        try {
            date = "2023-06-15";
            new21.setDate(format.parse(date));
        }catch (Exception e){
            Log.e("looknews", "写入日期失败 ");
        }
        new21.setUrl("https://news.cctv.com/2023/06/15/ARTIwkhGNBT1KMufvGmvxRKX230615.shtml");
        new21.save();

        News new22 = new News();
        new22.setImage("https://p4.img.cctvpic.com/photoworkspace/2023/10/07/2023100715140523872.jpg");
        new22.setTitle("亚运会花样游泳自由自选 王柳懿/王芊懿夺得冠军");
        new22.setContent("央视网消息：10月7日，杭州亚运会花样游泳双人自由自选结束争夺，中国组合王柳懿/王芊懿以总分526.8620夺得冠军。[图]花样游泳自由自选 王柳懿/王芊懿夺得冠军");
        try {
            date = "2023-10-07";
            new22.setDate(format.parse(date));
        }catch (Exception e){
            Log.e("looknews", "写入日期失败 ");
        }
        new22.setUrl("https://sports.cctv.com/2023/10/07/ARTImxDWb8lzmqHxwl7MPXUR231007.shtml");
        new22.save();

        News new23 = new News();
        new23.setImage("https://p1.img.cctvpic.com/photoworkspace/2023/10/08/2023100811221495338.jpg");
        new23.setTitle("中国队夺得杭州亚运会花样游泳集体项目金牌");
        new23.setContent("央视网消息：北京时间10月8日，杭州亚运会花样游泳集体项目结束争夺，中国队夺得金牌，日本队和哈萨克斯坦队分别获得银牌和铜牌。[图]中国队夺得杭州亚运会花样游泳集体项目金牌");
        try {
            date = "2023-10-08";
            new23.setDate(format.parse(date));
        }catch (Exception e){
            Log.e("looknews", "写入日期失败 ");
        }
        new23.setUrl("https://sports.cctv.com/2023/10/08/ARTIHrmrWVS0bGFSAYkasJxZ231008.shtml");
        new23.save();

        News new24 = new News();
        new24.setImage("https://p2.img.cctvpic.com/photoworkspace/2023/10/16/2023101608012265226.jpg");
        new24.setTitle("学习军人扎实作风 国家游泳队新体验带来新收获");
        new24.setContent("10月14日，国家游泳队在军训中体验射击科目，在不一样的体验中收获了成长的力量。");
        try {
            date = "2023-10-16";
            new24.setDate(format.parse(date));
        }catch (Exception e){
            Log.e("looknews", "写入日期失败 ");
        }
        new24.setUrl("https://sports.cctv.com/2023/10/16/ARTIFl4D9FFBuTWn4ET1L01m231016.shtml");
        new24.save();

        News new25 = new News();
        new25.setImage("https://p4.img.cctvpic.com/photoworkspace/2023/10/06/2023100612051039643.jpg");
        new25.setTitle("马拉松游泳女子10公里决赛 中国选手吴姝彤夺金");
        new25.setContent("央视网消息：北京时间10月6日，杭州第19届亚运会马拉松游泳女子10公里决赛在淳安界首体育中心游泳赛场开赛，中国选手吴姝彤夺金，日本选手获得银牌，另一位中国选手孙嘉珂拿下铜牌。");
        try {
            date = "2023-10-06";
            new25.setDate(format.parse(date));
        }catch (Exception e){
            Log.e("looknews", "写入日期失败 ");
        }
        new25.setUrl("https://sports.cctv.com/2023/10/06/ARTIuE3665YQFglu2OOkckyV231006.shtml");
        new25.save();

        News new26 = new News();
        new26.setImage("https://p1.img.cctvpic.com/photoworkspace/2024/01/06/2024010615064916440.jpg");
        new26.setTitle("河北保定一游泳馆被雪压塌？警方：造谣者被处罚");
        new26.setContent("经进一步调查，该视频发布者为刘某某（男，26岁，现住保定竞秀区），其承认12月19日晚在家中闲聊无事，就把自己前几日用无人机拍摄的游泳馆顶棚拆除改造的视频附上\"大雪压塌了游泳馆\"文字发布在抖音，目的为蹭热点");
        try {
            date = "2024-01-06";
            new26.setDate(format.parse(date));
        }catch (Exception e){
            Log.e("looknews", "写入日期失败 ");
        }
        new26.setUrl("https://news.cctv.com/2024/01/06/ARTIIWHDsd5ED35M8EAQveuM240106.shtml");
        new26.save();

        News new27 = new News();
        new27.setImage("https://p4.img.cctvpic.com/photoworkspace/2023/07/17/2023071709410150311.jpg");
        new27.setTitle("夏日游泳解暑 选泳池有讲究");
        new27.setContent("暑期来临，各个游泳场所逐渐进入经营高峰期，到游泳馆消暑纳凉的市民也逐渐增多。如何判断游泳场所是否符合卫生要求，水质状况是否达标？泳客在游泳过程中应该注意哪些方面？天津卫生健康监督部门为您支招。");
        try {
            date = "2023-07-17";
            new27.setDate(format.parse(date));
        }catch (Exception e){
            Log.e("looknews", "写入日期失败 ");
        }
        new27.setUrl("https://style.cctv.com/2023/07/17/ARTIfpo3MZO7D7Q9hM26bDDM230717.shtml");
        new27.save();

        News new28 = new News();
        new28.setImage("https://p5.img.cctvpic.com/photoworkspace/2023/10/09/2023100917261366674.jpg");
        new28.setTitle("四川盐源通报儿童溺亡事件：关停涉事游泳馆，严肃追责问责");
        new28.setContent("央视网消息：据\"魅力盐源\"消息，10月9日下午，四川盐源县教育和体育局、盐源县市场监督管理局针对网络\"男孩游泳池溺亡\"视频发布通报。10月1日，盐源县一游泳馆内1名儿童溺水身亡，令人痛惜。");
        try {
            date = "2023-10-09";
            new28.setDate(format.parse(date));
        }catch (Exception e){
            Log.e("looknews", "写入日期失败 ");
        }
        new28.setUrl("https://news.cctv.com/2023/10/09/ARTIzYOoURjfmXTpr480qnoD231009.shtml");
        new28.save();

        News new29 = new News();
        new29.setImage("https://p4.img.cctvpic.com/photoworkspace/2023/10/17/2023101711420719452.jpg");
        new29.setTitle("游泳世界杯雅典站落幕 覃海洋继续包揽蛙泳三金");
        new29.setContent("本年度游泳世界杯共设三站，第一站于10月6日至8日在德国柏林举行，雅典是第二站，第三站将于10月20日至22日在匈牙利布达佩斯举行。");
        try {
            date = "2023-10-17";
            new29.setDate(format.parse(date));
        }catch (Exception e){
            Log.e("looknews", "写入日期失败 ");
        }
        new29.setUrl("https://sports.cctv.com/2023/10/17/ARTIz4QOtVMlhZKx9zj89dQ4231017.shtml");
        new29.save();

        News new30 = new News();
        new30.setImage("https://p5.img.cctvpic.com/photoworkspace/2024/01/04/2024010409104339441.jpg");
        new30.setTitle("中国游泳队世锦赛参赛名单出炉 张雨霏覃海洋等缺席");
        new30.setContent("2024年多哈游泳世锦赛将于2月2日至18日在卡塔尔多哈举行，中国游泳队将派出21名运动员参加游泳项目比赛，4名运动员参加公开水域游泳项目比赛。");
        try {
            date = "2024-01-04";
            new30.setDate(format.parse(date));
        }catch (Exception e){
            Log.e("looknews", "写入日期失败 ");
        }
        new30.setUrl("https://sports.cctv.com/2024/01/04/ARTIwhYy3EGMCSb5LRBZBeIr240104.shtml");
        new30.save();




    }

}
