package com.gykj.rollcall.ui.notice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.gykj.mvvmlibrary.base.BaseActivity;
import com.gykj.rollcall.BR;
import com.gykj.rollcall.R;
import com.gykj.rollcall.databinding.ActivityNoticeDetailBinding;

public class NoticeDetailActivity extends BaseActivity<ActivityNoticeDetailBinding,NoticeDetailViewModel> {


    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_notice_detail;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }
}
