package com.gykj.rollcall.ui.notice;

import android.os.Bundle;

import com.gykj.mvvmlibrary.base.BaseActivity;
import com.gykj.rollcall.R;
import com.gykj.rollcall.databinding.ActivityReleaseBinding;
import com.gykj.rollcall.BR;

/**
 * desc   : 发布通告界面
 * author : josh.lu
 * e-mail : 1113799552@qq.com
 * date   : 2018/12/2711:54
 * version: 1.0
 */
public class ReleaseActivity extends BaseActivity<ActivityReleaseBinding,ReleaseViewModel> {
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_release;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }
}
