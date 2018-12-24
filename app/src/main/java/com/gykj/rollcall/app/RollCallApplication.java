package com.gykj.rollcall.app;

import com.gykj.mvvmlibrary.BuildConfig;
import com.gykj.mvvmlibrary.base.BaseApplication;
import com.gykj.mvvmlibrary.utils.KLog;

import me.jessyan.autosize.AutoSizeConfig;

/**
 * desc   : 点名系统Application
 * author : josh.lu
 * e-mail : 1113799552@qq.com
 * date   : 2018/12/2410:25
 * version: 1.0
 */
public class RollCallApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        //是否开启打印日志
        KLog.init(BuildConfig.DEBUG);
    }
}
