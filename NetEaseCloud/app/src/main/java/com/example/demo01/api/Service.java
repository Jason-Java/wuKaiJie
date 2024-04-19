package com.example.demo01.api;



import com.example.demo01.domain.SheetDetailWrapper;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 *网络接口配置
 *
 * 之所以接口还能返回数据
 * 是因为Retrofit框架内部处理了
 */
public interface Service {
    /**
     * 歌单项请
     * @param id
     * @return
     */
    @GET("v1/sheets/{id}")
    Observable<SheetDetailWrapper> sheetDatil(@Path("id") String id);
}
