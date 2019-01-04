package com.gykj.rollcall.app;

import android.os.Environment;

import com.gykj.mvvmlibrary.BuildConfig;
import com.gykj.mvvmlibrary.base.BaseApplication;
import com.gykj.mvvmlibrary.utils.KLog;

import java.io.File;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * desc   : 点名系统Application
 * author : josh.lu
 * e-mail : 1113799552@qq.com
 * date   : 2018/12/2410:25
 * version: 1.0
 */
public class RollCallApplication extends BaseApplication {

    private final String FACE_DB_PATH = Environment.getExternalStorageDirectory() + File.separator+"realmDb/";

    @Override
    public void onCreate() {
        super.onCreate();
        //是否开启打印日志
        KLog.init(BuildConfig.DEBUG);
        //realm初始化
        File file = new File(FACE_DB_PATH);
        if(!file.exists()){
            file.mkdir();
        }
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .directory(file)
                .name("roll_call.realm") //文件名
                .schemaVersion(1)
                .build();
        Realm.setDefaultConfiguration(config);
    }
}
