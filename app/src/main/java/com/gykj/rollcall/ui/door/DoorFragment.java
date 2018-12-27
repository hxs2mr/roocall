package com.gykj.rollcall.ui.door;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.gykj.mvvmlibrary.base.BaseFragment;
import com.gykj.rollcall.BR;
import com.gykj.rollcall.R;
import com.gykj.rollcall.databinding.FragmentNoticeBinding;
import com.gykj.rollcall.ui.notice.NoticeViewModel;

/**
 * desc   : 门禁考勤Fragment
 * author : josh.lu
 * e-mail : 1113799552@qq.com
 * date   : 2018/12/2711:23
 * version: 1.0
 */
public class DoorFragment extends BaseFragment<FragmentNoticeBinding,DoorViewModel> {
    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_door;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }
}
