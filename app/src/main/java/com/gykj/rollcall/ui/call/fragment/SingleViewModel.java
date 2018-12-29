package com.gykj.rollcall.ui.call.fragment;

import android.app.Application;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;

import com.gykj.mvvmlibrary.base.BaseViewModel;
import com.gykj.mvvmlibrary.binding.command.BindingCommand;
import com.gykj.mvvmlibrary.binding.command.BindingConsumer;
import com.gykj.rollcall.R;
import com.gykj.rollcall.BR;
import com.gykj.rollcall.entity.RoomEntity;

import me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter;
import me.tatarka.bindingcollectionadapter2.ItemBinding;

/**
 * desc   : 单次点名ViewModel
 * author : josh.lu
 * e-mail : 1113799552@qq.com
 * date   : 2018/12/2814:32
 * version: 1.0
 */
public class SingleViewModel extends BaseViewModel {
    public SingleViewModel(@NonNull Application application) {
        super(application);
    }

    //给RecyclerView添加ObservableList
    public ObservableList<SingleItemViewModel> observableList = new ObservableArrayList<>();
    //给RecyclerView添加ItemBinding
    public ItemBinding<SingleItemViewModel> itemBinding = ItemBinding.of(BR.viewModel, R.layout.layout_single_room_item);
    //RecyclerView多布局写法
    //给RecyclerView添加Adpter，请使用自定义的Adapter继承BindingRecyclerViewAdapter，重写onBindBinding方法
    public final BindingRecyclerViewAdapter<SingleItemViewModel> adapter = new BindingRecyclerViewAdapter<>();


    //全选绑定
    public BindingCommand<Boolean> onCheckedChangeCommand = new BindingCommand<>(new BindingConsumer<Boolean>() {
        @Override
        public void call(Boolean isChecked) {
            for (int i = 0;i<observableList.size();i++){
                observableList.get(i).entity.get().setCheck(isChecked);
                observableList.set(i,observableList.get(i));
            }
        }
    });
    /**
     * 请求网络
     */
    public void requestNetwork(){
        for(int i = 0;i<20;i++){
            RoomEntity entity = new RoomEntity();
            entity.setRoomId(i);
            entity.setRoomName((i+1)+ "室");
            entity.setCheck(false);
            SingleItemViewModel itemViewModel = new SingleItemViewModel(this, entity);
            observableList.add(itemViewModel);
        }
    }
}
