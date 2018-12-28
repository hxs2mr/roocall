package com.gykj.rollcall.ui.call;

import android.app.Application;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;

import com.gykj.mvvmlibrary.base.BaseViewModel;
import com.gykj.mvvmlibrary.binding.command.BindingAction;
import com.gykj.mvvmlibrary.binding.command.BindingCommand;
import com.gykj.rollcall.BR;
import com.gykj.rollcall.R;
import com.gykj.rollcall.entity.CallEntity;
import com.gykj.rollcall.entity.MainEntity;
import com.gykj.rollcall.ui.notice.NoticeItemViewModel;
import com.gykj.rollcall.ui.notice.ReleaseActivity;

import me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter;
import me.tatarka.bindingcollectionadapter2.ItemBinding;

/**
 * desc   : 人员点名ViewModel
 * author : josh.lu
 * e-mail : 1113799552@qq.com
 * date   : 2018/12/2712:18
 * version: 1.0
 */
public class CallViewModel extends BaseViewModel {

    //发布点名点击绑定
    public BindingCommand releaseOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(ReleaseCallActivity.class);
        }
    });


    //给RecyclerView添加ObservableList
    public ObservableList<CallItemViewModel> observableList = new ObservableArrayList<>();
    //给RecyclerView添加ItemBinding
    public ItemBinding<CallItemViewModel> itemBinding = ItemBinding.of(BR.viewModel, R.layout.layout_call_item);
    //RecyclerView多布局写法
    //给RecyclerView添加Adpter，请使用自定义的Adapter继承BindingRecyclerViewAdapter，重写onBindBinding方法
    public final BindingRecyclerViewAdapter<CallItemViewModel> adapter = new BindingRecyclerViewAdapter<>();


    public CallViewModel(@NonNull Application application) {
        super(application);
    }


    /**
     * 请求网络
     */
    public void requestNetwork(){
        for(int i = 0;i<20;i++){
            CallEntity entity = new CallEntity();
            entity.setCallName("宿管人员"+i);
            entity.setCallType(i%2);
            entity.setCallDate("2018-12-25 10:25:26");
            CallItemViewModel itemViewModel = new CallItemViewModel(this, entity);
            observableList.add(itemViewModel);
        }
    }

}
