package com.gykj.rollcall.ui.call;

import android.os.Bundle;
import android.support.design.widget.TabLayout;

import com.gykj.mvvmlibrary.base.BaseActivity;
import com.gykj.rollcall.BR;
import com.gykj.rollcall.R;
import com.gykj.rollcall.databinding.ActivityReleaseCallBinding;
import com.gykj.rollcall.ui.call.adapter.CallViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * desc   : 发布点名界面
 * author : josh.lu
 * e-mail : 1113799552@qq.com
 * date   : 2018/12/2813:55
 * version: 1.0
 */
public class ReleaseCallActivity extends BaseActivity<ActivityReleaseCallBinding,ReleaseCallViewModel> {

    private CallViewPagerAdapter adapter;

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_release_call;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        viewModel.initFragments();
        binding.callTabLayout.addTab(binding.callTabLayout.newTab().setText(getString(R.string.call_single)));
        binding.callTabLayout.addTab(binding.callTabLayout.newTab().setText(getString(R.string.call_point)));
        initPageAdapter();
        binding.callTabLayout.setupWithViewPager(binding.callViewPager);
        binding.callViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.callTabLayout));

    }

    private void initPageAdapter(){
        List<String> mTitleList = new ArrayList<>();
        mTitleList.add(getString(R.string.call_single));
        mTitleList.add(getString(R.string.call_point));
        if(null == adapter){
            adapter = new CallViewPagerAdapter(getSupportFragmentManager(),viewModel.fragments,mTitleList);
        }
        binding.callViewPager.setAdapter(adapter);
    }
}
