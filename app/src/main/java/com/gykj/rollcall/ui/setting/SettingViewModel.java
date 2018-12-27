package com.gykj.rollcall.ui.setting;

import android.app.Application;
import android.support.annotation.NonNull;

import com.gykj.mvvmlibrary.base.BaseViewModel;
import com.gykj.mvvmlibrary.binding.command.BindingAction;
import com.gykj.mvvmlibrary.binding.command.BindingCommand;

/**
 * desc   : 设置ViewModel
 * author : josh.lu
 * e-mail : 1113799552@qq.com
 * date   : 2018/12/2712:18
 * version: 1.0
 */
public class SettingViewModel extends BaseViewModel {



    //退出登录绑定
    public BindingCommand logoutOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {

        }
    });

    //资料修改绑定
    public BindingCommand dataOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {

        }
    });

    //密码修改绑定
    public BindingCommand passwordOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {

        }
    });


    public SettingViewModel(@NonNull Application application) {
        super(application);
    }
}
