package com.gykj.rollcall.ui.notice;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.gykj.mvvmlibrary.base.ItemViewModel;
import com.gykj.mvvmlibrary.binding.command.BindingAction;
import com.gykj.mvvmlibrary.binding.command.BindingCommand;
import com.gykj.mvvmlibrary.entity.Config;
import com.gykj.mvvmlibrary.utils.KLog;
import com.gykj.rollcall.entity.MainEntity;
import com.gykj.rollcall.ui.index.MainViewModel;

/**
 * desc   : 主界面ItemViewModel
 * author : josh.lu
 * e-mail : 1113799552@qq.com
 * date   : 2018/12/259:52
 * version: 1.0
 */
public class NoticeItemViewModel extends ItemViewModel<NoticeViewModel> {

    public ObservableField<MainEntity> entity = new ObservableField<>();

    public NoticeItemViewModel(@NonNull NoticeViewModel viewModel, MainEntity entity) {
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

    //条目的查看事件
    public BindingCommand scanClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            Bundle bundle = new Bundle();
            bundle.putSerializable(Config.NOTICE_DETAIL,entity.get());
            viewModel.startActivity(NoticeDetailActivity.class,bundle);
        }
    });

}
