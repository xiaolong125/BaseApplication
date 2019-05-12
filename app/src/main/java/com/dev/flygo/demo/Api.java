package com.dev.flygo.demo;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 作者：ly-xuxiaolong
 * 版本：1.0
 * 创建日期：2019/5/13
 * 描述：
 * 修订历史：
 */
public interface Api{
    @GET("citys")
    Observable<AllCity> getAllCity(@Query("key") String key);
}
