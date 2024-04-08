package com.example.projectone.enjoy.adapter;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectone.Manage.CommentManage;
import com.example.projectone.R;
import com.example.projectone.table.Comment;
import com.example.projectone.table.Friends;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentHolder> {
    Context context;
    String username;

    List<Comment> comments;

    View view;

    Comment comment;
    String name;
    String text;
    private View popupView;
    private PopupWindow popupWindow;
    private long firstPressedTime;
    private String commentname;
    private String commentdate;
    private String enjoydate;


    public void setData(List<Comment> comments){
        this.comments = comments;
        notifyDataSetChanged();
    }

    public CommentAdapter(Context context, String username) {
        this.context = context;
        this.username = username;
    }

    @NonNull
    @Override
    public CommentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = View.inflate(context, R.layout.commentitem,null);
        CommentHolder commentHolder = new CommentHolder(view);
        return commentHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CommentHolder holder, int position) {
        //评论初始化
        comment = comments.get(position);
        name = comment.getUsername();
        text = comment.getCommenttext();
        holder.commentitem.setText(name+" : "+text);

        //长按出现悬浮框，判断是不是自己的评论
        holder.commentitem.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                //获取评论的username
                comment = comments.get(holder.getAdapterPosition());
                name = comment.getUsername();
                text = comment.getCommenttext();
                if (name.equals(username)){//如果是自己的评论

                    popupView = LayoutInflater.from(context).inflate(R.layout.chat_popupwindow,null);
                    popupWindow = new PopupWindow(popupView);
                    popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
                    popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

                    TextView chatitem_deletename = popupView.findViewById(R.id.chatitem_deletename);
                    chatitem_deletename.setText(name+" : "+text);
                    Button chatitem_delete = popupView.findViewById(R.id.chatitem_delete);
                    chatitem_delete.setText("删除该评论");

                    //获取焦点，保证edittext能输入
                    popupWindow.setFocusable(true);
                    //外部是否可以点击
                    //只有加上它之后，PopupWindow才会对手机的返回按钮有响应：即，点击手机返回按钮，可以关闭PopupWindow；
                    // 如果不加setBackgroundDrawable（）将关闭的PopupWindow所在的Activity.
                    popupWindow.setBackgroundDrawable(new BitmapDrawable());
                    popupWindow.setOutsideTouchable(true);

                    //popupwidow放在rootview里
                    View rootview = LayoutInflater.from(context).inflate(R.layout.enjoy_fragment,null);
                    popupWindow.showAtLocation(rootview, Gravity.CENTER,0,0);

                    chatitem_delete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (System.currentTimeMillis() - firstPressedTime < 2000) {
                                //获取username,commentname,commentdate,enjoydate
                                comment = comments.get(holder.getAdapterPosition());
                                commentname = comment.getCommentname();
                                commentdate = comment.getCommentdate();
                                enjoydate = comment.getEnjoydate();

                                //删除该评论
                                CommentManage.DeleteAllComment(username,commentdate,commentname,enjoydate);

                                //查找给评论的所有评论
                                comments = CommentManage.FindALlComment(commentname,enjoydate);

                                setData(comments);

                                Toast.makeText(context, "已删除评论", Toast.LENGTH_SHORT).show();

                                popupWindow.dismiss();//悬浮框消失
                            } else {
                                Toast.makeText(context, "再按一次删除评论", Toast.LENGTH_SHORT).show();
                                firstPressedTime = System.currentTimeMillis();
                            }
                        }
                    });
                };

                return false;
            }
        });
    }



    @Override
    public int getItemCount() {
        return comments.size();
    }
}
