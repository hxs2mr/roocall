package com.gykj.rollcall.model.service;

import com.gykj.mvvmlibrary.entity.BaseEntity;
import com.gykj.rollcall.entity.TokenEntity;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * desc   : 点名系统后台接口
 * author : josh.lu
 * e-mail : 1113799552@qq.com
 * date   : 2018/12/2414:48
 * version: 1.0
 */
public interface RollCallService {

    @POST("user/login_by_phone")
    Observable<BaseEntity<TokenEntity>> login(@Body RequestBody body);

}
