package com.example.demo01.api;



import com.example.demo01.domain.Sheet;
import com.example.demo01.domain.SheetDetailWrapper;
import com.example.demo01.domain.SheetListWrapper;
import com.example.demo01.domain.User;
import com.example.demo01.domain.response.DetailResponse;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 *网络接口配置
 *
 * 之所以接口还能返回数据
 * 是因为Retrofit框架内部处理了
 */
public interface Service {
    /**
     * 歌单详情
     * @param id
     * @return
     */
    @GET("v1/sheets/{id}")
    Observable<DetailResponse<Sheet>> sheetDatil(@Path("id") String id);

    /**
     * 歌单列表
     * @return
     */
    @GET("v1/sheets/")
    Observable<SheetListWrapper> sheets();

    /**
     * 用户详情
     * @param id
     * @param data
     * @return
     */
    @GET("v1/users/{id}")
    Observable<DetailResponse<User>> userDetail(@Path("id") String id, @QueryMap Map<String,String> data);
}
