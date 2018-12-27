package com.gykj.rollcall.ui.notice;

import android.app.Application;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;

import com.gykj.mvvmlibrary.base.BaseViewModel;
import com.gykj.mvvmlibrary.binding.command.BindingAction;
import com.gykj.mvvmlibrary.binding.command.BindingCommand;
import com.gykj.mvvmlibrary.utils.KLog;
import com.gykj.rollcall.BR;
import com.gykj.rollcall.R;
import com.gykj.rollcall.entity.MainEntity;

import me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter;
import me.tatarka.bindingcollectionadapter2.ItemBinding;

/**
 * desc   : 主界面ViewModel
 * author : josh.lu
 * e-mail : 1113799552@qq.com
 * date   : 2018/12/2416:02
 * version: 1.0
 */
public class NoticeViewModel extends BaseViewModel {


    //封装一个界面发生改变的观察者
    public UIChangeObservable uc = new UIChangeObservable();

    public class UIChangeObservable {
        //显示Menu菜单栏
        public ObservableBoolean showPopWindows = new ObservableBoolean(false);
    }

    //menu菜单绑定
    public BindingCommand menuOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            uc.showPopWindows.set(!uc.showPopWindows.get());
        }
    });

    //发布通知点击绑定
    public BindingCommand releaseOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
           startActivity(ReleaseActivity.class);
        }
    });

    //通话点击绑定
    public BindingCommand voiceOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            KLog.d("lanzhu","通话点击绑定");
        }
    });

    public NoticeViewModel(@NonNull Application application) {
        super(application);
    }

    //给RecyclerView添加ObservableList
    public ObservableList<NoticeItemViewModel> observableList = new ObservableArrayList<>();
    //给RecyclerView添加ItemBinding
    public ItemBinding<NoticeItemViewModel> itemBinding = ItemBinding.of(BR.viewModel, R.layout.layout_notice_item);
    //RecyclerView多布局写法
    //给RecyclerView添加Adpter，请使用自定义的Adapter继承BindingRecyclerViewAdapter，重写onBindBinding方法
    public final BindingRecyclerViewAdapter<NoticeItemViewModel> adapter = new BindingRecyclerViewAdapter<>();

    /**
     * 请求网络
     */
    public void requestNetwork(){
        for(int i = 0;i<20;i++){
            MainEntity entity = new MainEntity();
            entity.setTitle("标题"+i);
            entity.setContent("内容"+i);
            entity.setDate("2018-12-25 10:25:26");
            NoticeItemViewModel itemViewModel = new NoticeItemViewModel(this, entity);
            observableList.add(itemViewModel);
        }
    }

}
