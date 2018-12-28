package com.gykj.rollcall.ui.call.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.gykj.mvvmlibrary.base.BaseFragment;
import com.gykj.rollcall.R;
import com.gykj.rollcall.BR;
import com.gykj.rollcall.databinding.FragmentSingleBinding;

/**
 * desc   : 单次点名Fragment
 * author : josh.lu
 * e-mail : 1113799552@qq.com
 * date   : 2018/12/2814:30
 * version: 1.0
 */
public class SingleFragment extends BaseFragment<FragmentSingleBinding,SingleViewModel> {
    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_single;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }


    @Override
    public void initData() {
        viewModel.requestNetwork();
    }
}
