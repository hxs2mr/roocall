package com.gykj.rollcall.ui.index;


import android.arch.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.gykj.mvvmlibrary.base.BaseActivity;

import com.gykj.rollcall.BR;
import com.gykj.rollcall.R;
import com.gykj.rollcall.databinding.ActivityMainBinding;


public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> {


    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public MainViewModel initViewModel() {
        return ViewModelProviders.of(this).get(MainViewModel.class);
    }

    @Override
    public void initData() {
        viewModel.requestNetwork();
    }
}
