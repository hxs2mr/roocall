package com.gykj.rollcall.ui.analyse;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.gykj.mvvmlibrary.base.BaseFragment;
import com.gykj.rollcall.BR;
import com.gykj.rollcall.R;
import com.gykj.rollcall.databinding.FragmentAnalyseBinding;
import com.gykj.rollcall.databinding.FragmentNoticeBinding;
import com.gykj.rollcall.ui.call.adapter.CallViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * desc   : 点名统计分析Fragment
 * author : josh.lu
 * e-mail : 1113799552@qq.com
 * date   : 2018/12/2711:23
 * version: 1.0
 */
public class AnalyseFragment extends BaseFragment<FragmentAnalyseBinding,AnalyseViewModel> {

    CallViewPagerAdapter adapter;

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_analyse;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }


    @Override
    public void initData() {
        viewModel.initFragments();
        binding.analyseTabLayout.addTab(binding.analyseTabLayout.newTab().setText(getString(R.string.analyse_start)));
        binding.analyseTabLayout.addTab(binding.analyseTabLayout.newTab().setText(getString(R.string.analyse_end)));
        initPageAdapter();
        binding.analyseTabLayout.setupWithViewPager(binding.analyseViewPager);
        binding.analyseViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.analyseTabLayout));

    }

    private void initPageAdapter(){
        List<String> mTitleList = new ArrayList<>();
        mTitleList.add(getString(R.string.analyse_start));
        mTitleList.add(getString(R.string.analyse_end));
        if(null == adapter){
            adapter = new CallViewPagerAdapter(getChildFragmentManager(),viewModel.fragments,mTitleList);
        }
        binding.analyseViewPager.setAdapter(adapter);
    }
}
