package com.gykj.rollcall.ui.call.fragment;

import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.gykj.mvvmlibrary.base.ItemViewModel;
import com.gykj.mvvmlibrary.binding.command.BindingAction;
import com.gykj.mvvmlibrary.binding.command.BindingCommand;
import com.gykj.rollcall.entity.RoomEntity;

/**
 * desc   : 单次点名ItemViewModel
 * author : josh.lu
 * e-mail : 1113799552@qq.com
 * date   : 2018/12/2816:10
 * version: 1.0
 */
public class SingleItemViewModel extends ItemViewModel<SingleViewModel> {


    public ObservableField<RoomEntity> entity = new ObservableField<>();

    public SingleItemViewModel(@NonNull SingleViewModel viewModel,RoomEntity entity) {
        super(viewModel);
        this.entity.set(entity);
    }
}
