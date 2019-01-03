package com.gykj.rollcall.ui.notice;

import android.app.Application;
import android.databinding.ObservableBoolean;
import android.support.annotation.NonNull;

import com.gykj.mvvmlibrary.base.BaseViewModel;
import com.gykj.mvvmlibrary.binding.command.BindingAction;
import com.gykj.mvvmlibrary.binding.command.BindingCommand;
import com.gykj.rollcall.ui.index.MainViewModel;


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

    //选择照片按钮的点击事件
    public BindingCommand selectOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            uc.selectPhoto.set(!uc.selectPhoto.get());
        }
    });


    //封装一个界面发生改变的观察者
    public UIChangeObservable uc = new UIChangeObservable();

    public class UIChangeObservable {
        //选择图片
        public ObservableBoolean selectPhoto = new ObservableBoolean(false);

    }
    public ReleaseViewModel(@NonNull Application application) {
        super(application);
    }
}
