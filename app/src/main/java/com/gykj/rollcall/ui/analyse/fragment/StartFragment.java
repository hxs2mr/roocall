package com.gykj.rollcall.ui.analyse.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.gykj.mvvmlibrary.base.BaseFragment;
import com.gykj.rollcall.BR;
import com.gykj.rollcall.R;
import com.gykj.rollcall.databinding.FragmentStartBinding;

/**
 * desc   : 进行中 Fragment
 * author : josh.lu
 * e-mail : 1113799552@qq.com
 * date   : 2019/1/229:30
 * version: 1.0
 */
public class StartFragment extends BaseFragment<FragmentStartBinding,StartViewModel> {
    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_start;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }
}
