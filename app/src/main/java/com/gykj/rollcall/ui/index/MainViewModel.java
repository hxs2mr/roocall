package com.gykj.rollcall.ui.index;

import android.app.Application;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.gykj.mvvmlibrary.base.BaseViewModel;
import com.gykj.mvvmlibrary.binding.command.BindingAction;
import com.gykj.mvvmlibrary.binding.command.BindingCommand;
import com.gykj.mvvmlibrary.utils.KLog;
import com.gykj.mvvmlibrary.utils.Utils;
import com.gykj.rollcall.R;

/**
 * desc   : 主界面ViewModel
 * author : josh.lu
 * e-mail : 1113799552@qq.com
 * date   : 2018/12/2416:02
 * version: 1.0
 */
public class MainViewModel extends BaseViewModel {


    //menu菜单绑定
    public BindingCommand menuOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            KLog.d("lanzhu","menu菜单绑定");
        }
    });

    //通话点击绑定
    public BindingCommand voiceOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            KLog.d("lanzhu","通话点击绑定");
        }
    });

    public MainViewModel(@NonNull Application application) {
        super(application);
    }
}
