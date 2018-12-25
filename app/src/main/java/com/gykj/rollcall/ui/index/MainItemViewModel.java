package com.gykj.rollcall.ui.index;

import android.app.Application;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;

import com.gykj.mvvmlibrary.base.BaseViewModel;
import com.gykj.mvvmlibrary.base.ItemViewModel;
import com.gykj.mvvmlibrary.binding.command.BindingAction;
import com.gykj.mvvmlibrary.binding.command.BindingCommand;
import com.gykj.mvvmlibrary.utils.KLog;
import com.gykj.rollcall.entity.MainEntity;

/**
 * desc   : 主界面ItemViewModel
 * author : josh.lu
 * e-mail : 1113799552@qq.com
 * date   : 2018/12/259:52
 * version: 1.0
 */
public class MainItemViewModel extends ItemViewModel<MainViewModel> {

    public ObservableField<MainEntity> entity = new ObservableField<>();

    public MainItemViewModel(@NonNull MainViewModel viewModel, MainEntity entity) {
        super(viewModel);
        this.entity.set(entity);
    }

    //条目的修改事件
    public BindingCommand modifyClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            KLog.d("lanzhu","modifyClick");
        }
    });

    //条目的删除事件
    public BindingCommand deleteClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            KLog.d("lanzhu","deleteClick");
        }
    });
}
