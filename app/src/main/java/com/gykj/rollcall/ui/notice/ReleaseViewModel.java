package com.gykj.rollcall.ui.notice;

import android.app.Application;
import android.support.annotation.NonNull;

import com.gykj.mvvmlibrary.base.BaseViewModel;
import com.gykj.mvvmlibrary.binding.command.BindingAction;
import com.gykj.mvvmlibrary.binding.command.BindingCommand;


/**
 * desc   :
 * author : josh.lu
 * e-mail : 1113799552@qq.com
 * date   : 2018/12/2711:52
 * version: 1.0
 */
public class ReleaseViewModel extends BaseViewModel {

    //返回按钮点击绑定
    public BindingCommand cancleOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            finish();
        }
    });

    public ReleaseViewModel(@NonNull Application application) {
        super(application);
    }
}
