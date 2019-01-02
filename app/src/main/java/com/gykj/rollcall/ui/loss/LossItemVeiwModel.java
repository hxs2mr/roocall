package com.gykj.rollcall.ui.loss;

import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.gykj.mvvmlibrary.base.ItemViewModel;
import com.gykj.rollcall.entity.LossEntity;

/**
 * desc   : 物品报损ItemVeiwModel
 * author : josh.lu
 * e-mail : 1113799552@qq.com
 * date   : 2019/1/29:28
 * version: 1.0
 */
public class LossItemVeiwModel extends ItemViewModel<LossViewModel> {

    public ObservableField<LossEntity> entity = new ObservableField<>();

    public LossItemVeiwModel(@NonNull LossViewModel viewModel,LossEntity entity) {
        super(viewModel);
        this.entity.set(entity);
    }
}
