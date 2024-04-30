package com.unite.okhttpdemo.api;

import com.unite.okhttpdemo.domain.PasswordLogin;
import com.unite.okhttpdemo.domain.cabinet.CabinetInfoResponse;
import com.unite.okhttpdemo.domain.cabinet.CabinetResponse;
import com.unite.okhttpdemo.domain.limit.Children;
import com.unite.okhttpdemo.domain.response.DetailResponse;
import com.unite.okhttpdemo.domain.response.ListResponse;
import com.unite.okhttpdemo.domain.user.UserResponse;
import com.unite.okhttpdemo.domain.shiji.ShiJiJson;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 *网络接口配置
 * 之所以接口还能返回数据
 * 是因为Retrofit框架内部处理了
 */
public interface Service {

    /**
     * 用户密码登录
     */
    @GET("api/Login/JWTToken3.0")
    Observable<PasswordLogin> passwordLogin(@Query("name") String user,@Query("pass") String password);

    /**
     * 用户信息
     * @param token
     * @return
     */
    @GET("api/zu/user/getInfoByToken")
    Observable<DetailResponse<UserResponse>> getUser(@Header("Authorization") String token);

    /**
     * 获取权限
     * @param token
     * @return
     */
    @GET("api/zu/Permission")
    Observable<DetailResponse<Children>> getLimit(@Header("Authorization") String token);

    /**
     * 获取柜子id
     */
    @GET("api/Department/{id}/box")
    Observable<ListResponse<CabinetResponse>> getCabinet(@Header("Authorization") String token, @Path("id") int id);


    /**
     * 获取柜子信息和抽屉信息
     */
    @GET("api/Box/{id}")
    Observable<DetailResponse<CabinetInfoResponse>> getCabinetInfo(@Header("Authorization") String token, @Path("id") int id);

    /**
     * 获取试剂
     */
    @GET("api/ReagentModel")
    Observable<ShiJiJson> getShiJi(@Header("Authorization") String token,@Query("rows") int rows,@Query("name") String name);

}
