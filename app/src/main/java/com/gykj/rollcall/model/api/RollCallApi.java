package com.gykj.rollcall.model.api;

import android.support.v4.util.ArrayMap;

import com.gykj.mvvmlibrary.entity.BaseEntity;
import com.gykj.mvvmlibrary.utils.JsonUtils;
import com.gykj.rollcall.entity.TokenEntity;
import com.gykj.rollcall.model.service.RollCallService;
import com.gykj.rollcall.utils.RollCallClient;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * desc   :
 * author : josh.lu
 * e-mail : 1113799552@qq.com
 * date   : 2018/12/2414:39
 * version: 1.0
 */
public class RollCallApi {

    RollCallService mService;

    public static final MediaType MEDIA_TYPE_MARKDOWN
            = MediaType.parse("application/json; charset=utf-8");



    private RollCallApi(){
        mService = RollCallClient.getInstance().create(RollCallService.class);
    }

    private static class SingletonHolder {
        private static RollCallApi INSTANCE = new RollCallApi();
    }

    public static RollCallApi getInstance() {
        return RollCallApi.SingletonHolder.INSTANCE;
    }


    public Observable<BaseEntity<TokenEntity>> login(String mobilephone,String password){
        Map<String,Object> map = new ArrayMap<>();
        map.put("mobilephone",mobilephone);
        map.put("password",password);
        map.put("app_type","client");
        RequestBody body = RequestBody.create(MEDIA_TYPE_MARKDOWN, JsonUtils.map2json(map));
        return mService.login(body);
    }



}
