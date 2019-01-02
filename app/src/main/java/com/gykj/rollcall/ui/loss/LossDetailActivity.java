package com.gykj.rollcall.ui.loss;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.gykj.mvvmlibrary.base.BaseActivity;
import com.gykj.mvvmlibrary.utils.KLog;
import com.gykj.rollcall.R;
import com.gykj.rollcall.BR;
import com.gykj.rollcall.databinding.ActivityLossDetailBinding;
import com.gykj.rollcall.entity.LossEntity;

import static com.gykj.mvvmlibrary.entity.Config.LOSS_DETAIL;

public class LossDetailActivity extends BaseActivity<ActivityLossDetailBinding,LossDetailViewModel> {


    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_loss_detail;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        LossEntity entity = (LossEntity) getIntent().getExtras().getSerializable(LOSS_DETAIL);
        KLog.d("lanzhu",entity.getRoom());
        viewModel.setLossDetail(entity);
    }
}
