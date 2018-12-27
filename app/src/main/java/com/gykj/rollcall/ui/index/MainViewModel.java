package com.gykj.rollcall.ui.index;

import android.app.Application;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;

import com.gykj.mvvmlibrary.base.BaseViewModel;
import com.gykj.mvvmlibrary.binding.command.BindingAction;
import com.gykj.mvvmlibrary.binding.command.BindingCommand;
import com.gykj.rollcall.ui.setting.ConverseActivity;

/**
 * desc   : 主界面ViewModel
 * author : josh.lu
 * e-mail : 1113799552@qq.com
 * date   : 2018/12/2710:20
 * version: 1.0
 */
public class MainViewModel extends BaseViewModel {

    //选中的位置
    public int index = 0;

    //菜单按钮的点击事件
    public BindingCommand menuOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            uc.showDrawer.set(!uc.showDrawer.get());
        }
    });

    //语音按钮的点击事件
    public BindingCommand voiceOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(ConverseActivity.class);
        }
    });

    //通知通告按钮的点击事件
    public BindingCommand noticeOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            index = 0;
            uc.clickPosition.set(!uc.clickPosition.get());
        }
    });


    //人员点名按钮的点击事件
    public BindingCommand callOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            index = 1;
            uc.clickPosition.set(!uc.clickPosition.get());
        }
    });


    //物品借用按钮的点击事件
    public BindingCommand borrowOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            index = 2;
            uc.clickPosition.set(!uc.clickPosition.get());
        }
    });


    //报损按钮的点击事件
    public BindingCommand lossOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            index = 3;
            uc.clickPosition.set(!uc.clickPosition.get());
        }
    });


    //门禁考勤按钮的点击事件
    public BindingCommand doorOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            index = 4;
            uc.clickPosition.set(!uc.clickPosition.get());
        }
    });


    //点名统计分析按钮的点击事件
    public BindingCommand analyseOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            index = 5;
            uc.clickPosition.set(!uc.clickPosition.get());
        }
    });


    //设置按钮的点击事件
    public BindingCommand settingOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            index = 6;
            uc.clickPosition.set(!uc.clickPosition.get());
        }
    });


    //封装一个界面发生改变的观察者
    public UIChangeObservable uc = new UIChangeObservable();

    public class UIChangeObservable {
        //Drawer显示
        public ObservableBoolean showDrawer = new ObservableBoolean(false);

        public ObservableBoolean clickPosition = new ObservableBoolean(false);
    }


    public MainViewModel(@NonNull Application application) {
        super(application);
    }
}
