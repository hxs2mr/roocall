package com.gykj.rollcall.ui.door;

import android.app.Application;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;

import com.gykj.mvvmlibrary.base.BaseViewModel;
import com.gykj.rollcall.BR;
import com.gykj.rollcall.R;
import com.gykj.rollcall.entity.DoorEntity;
import com.gykj.rollcall.ui.borrow.BorrowItemViewModel;

import me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter;
import me.tatarka.bindingcollectionadapter2.ItemBinding;

/**
 * desc   : 门径考勤ViewModel
 * author : josh.lu
 * e-mail : 1113799552@qq.com
 * date   : 2018/12/2712:18
 * version: 1.0
 */
public class DoorViewModel extends BaseViewModel {

    //给RecyclerView添加ObservableList
    public ObservableList<DoorItemViewModel> observableList = new ObservableArrayList<>();
    //给RecyclerView添加ItemBinding
    public ItemBinding<DoorItemViewModel> itemBinding = ItemBinding.of(BR.viewModel, R.layout.layout_door_item);
    //RecyclerView多布局写法
    //给RecyclerView添加Adpter，请使用自定义的Adapter继承BindingRecyclerViewAdapter，重写onBindBinding方法
    public final BindingRecyclerViewAdapter<DoorItemViewModel> adapter = new BindingRecyclerViewAdapter<>();

    public DoorViewModel(@NonNull Application application) {
        super(application);
    }


    /**
     * 请求网络
     */
    public void requestNetwork(){
        for(int i = 0;i<5;i++){
            DoorEntity entity = new DoorEntity();
            entity.setAvater("http://g.hiphotos.baidu.com/image/h%3D300/sign=f6a79e887f0e0cf3bff748fb3a44f23d/adaf2edda3cc7cd9ebe507433401213fb90e915b.jpg");
            entity.setName("张三"+i);
            entity.setDate("2018-12-25 10:25:26");
            entity.setGrade("一年级（"+(i+1)+"）班");
            entity.setType(i%2);
            entity.setRoom(String.valueOf(100+i)+"室");
            entity.setCode("2018036542");
            DoorItemViewModel itemViewModel = new DoorItemViewModel(this, entity);
            observableList.add(itemViewModel);
        }
    }
}
