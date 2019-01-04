package com.gykj.rollcall.ui.index;

import android.databinding.Observable;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;

import com.gykj.mvvmlibrary.base.BaseActivity;
import com.gykj.mvvmlibrary.utils.KLog;
import com.gykj.mvvmlibrary.utils.Utils;
import com.gykj.rollcall.BR;
import com.gykj.rollcall.R;
import com.gykj.rollcall.databinding.ActivityMainBinding;
import com.gykj.rollcall.ui.analyse.AnalyseFragment;
import com.gykj.rollcall.ui.borrow.BorrowFragment;
import com.gykj.rollcall.ui.call.CallFragment;
import com.gykj.rollcall.ui.door.DoorFragment;
import com.gykj.rollcall.ui.loss.LossFragment;
import com.gykj.rollcall.ui.notice.NoticeFragment;
import com.gykj.rollcall.ui.setting.SettingFragment;
import com.gykj.rollcall.utils.FragmentFactory;
import com.gykj.rollcall.utils.LocationUtils;
import com.gykj.rollcall.utils.MenuUtils;
import com.gykj.rollcall.utils.ParseXmlUtils;

/**
 * desc   :
 * author : josh.lu
 * e-mail : 1113799552@qq.com
 * date   : 2018/12/2710:19
 * version: 1.0
 */
public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> {

    private LocationManager locationManager;
    private String provider;

    private Fragment[] fragments;
    private int index;
    //当前fragment的index
    private int currentTabIndex;
    private Fragment noticeFragment, callFragment, borrowFragment, lossFragment, doorFragment, analyseFragment, settingFragment;

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }


    @Override
    public void initData() {
        noticeFragment = FragmentFactory.createFragment(NoticeFragment.class, true);
        callFragment = FragmentFactory.createFragment(CallFragment.class, true);
        borrowFragment = FragmentFactory.createFragment(BorrowFragment.class, true);
        lossFragment = FragmentFactory.createFragment(LossFragment.class, true);
        doorFragment = FragmentFactory.createFragment(DoorFragment.class, true);
        analyseFragment = FragmentFactory.createFragment(AnalyseFragment.class, true);
        settingFragment = FragmentFactory.createFragment(SettingFragment.class, true);
        fragments = new Fragment[]{noticeFragment, callFragment, borrowFragment, lossFragment, doorFragment, analyseFragment, settingFragment};
        getSupportFragmentManager().beginTransaction()
                .add(R.id.main_container, noticeFragment)
                .add(R.id.main_container, callFragment)
                .hide(callFragment).show(noticeFragment)
                .commitAllowingStateLoss();

        initLocation();

        try {
            ParseXmlUtils.parseXml(getAssets().open("city.xml"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initLocation() {
        LocationUtils.getCurrentLocation(this, new LocationUtils.LocationCallBack() {
            @Override
            public void onSuccess(Location location) {
                KLog.d("lanzhu","精度="+location.getLongitude());
                KLog.d("lanzhu","纬度="+location.getLatitude());
            }

            @Override
            public void onFail(String msg) {
                KLog.d("lanzhu","错误="+msg);
            }
        });
    }


    @Override
    public void initViewObservable() {
        viewModel.uc.showDrawer.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                binding.mainDrawerLayout.openDrawer(Gravity.START);
            }
        });
        viewModel.uc.clickPosition.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                index = viewModel.index;
                if (currentTabIndex != index) {
                    FragmentTransaction trx = getSupportFragmentManager().beginTransaction();
                    trx.hide(fragments[currentTabIndex]);
                    if (!fragments[index].isAdded()) {
                        trx.add(R.id.main_container, fragments[index]);
                    }
                    trx.show(fragments[index]).commit();
                }
                currentTabIndex = index;
                binding.mainTitleTv.setText(MenuUtils.getMenuList(Utils.getContext()).get(index).getMenu_title());
                binding.mainDrawerLayout.closeDrawer(Gravity.START);
            }
        });
    }
}
