package com.gykj.rollcall.ui.index;

import android.databinding.Observable;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;

import com.google.gson.Gson;
import com.gykj.mvvmlibrary.base.BaseActivity;
import com.gykj.mvvmlibrary.utils.DateUtil;
import com.gykj.mvvmlibrary.utils.KLog;
import com.gykj.mvvmlibrary.utils.LunarCalendar;
import com.gykj.mvvmlibrary.utils.Utils;
import com.gykj.rollcall.BR;
import com.gykj.rollcall.R;
import com.gykj.rollcall.databinding.ActivityMainBinding;
import com.gykj.rollcall.entity.CityInfo;
import com.gykj.rollcall.entity.WeatherEntity;
import com.gykj.rollcall.manager.RCManager;
import com.gykj.rollcall.ui.analyse.AnalyseFragment;
import com.gykj.rollcall.ui.borrow.BorrowFragment;
import com.gykj.rollcall.ui.call.CallFragment;
import com.gykj.rollcall.ui.door.DoorFragment;
import com.gykj.rollcall.ui.loss.LossFragment;
import com.gykj.rollcall.ui.notice.NoticeFragment;
import com.gykj.rollcall.ui.setting.SettingFragment;
import com.gykj.rollcall.utils.FragmentFactory;
import com.gykj.rollcall.utils.HttpUtils;
import com.gykj.rollcall.utils.LocationUtils;
import com.gykj.rollcall.utils.MapUrlUtils;
import com.gykj.rollcall.utils.MenuUtils;
import com.gykj.rollcall.utils.ParseXmlUtils;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * desc   :
 * author : josh.lu
 * e-mail : 1113799552@qq.com
 * date   : 2018/12/2710:19
 * version: 1.0
 */
public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> {


    private Fragment[] fragments;
    private int index;
    //当前fragment的index
    private int currentTabIndex;
    private Fragment noticeFragment, callFragment, borrowFragment, lossFragment, doorFragment, analyseFragment, settingFragment;
    private Gson gson;

    private Timer mTimer;
    private TimerTask mDateTask;
    private TimerTask mWeatherTask;
    private Handler mHandler;

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initParam() {
        gson = new Gson();
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
    }

    @Override
    public void initData() {
        initTimer();
        initLocation();
        showDrawerDate();
        showDrawerWeather();
        showDrawerLocation();
    }

    private void initTimer() {
        mHandler = new Handler();
        mTimer = new Timer();
        mDateTask = new TimerTask() {
            @Override
            public void run() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        showDrawerDate();
                    }
                });
            }
        };
        mWeatherTask = new TimerTask() {
            @Override
            public void run() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        initLocation();
                    }
                });
            }
        };
        mTimer.schedule(mDateTask,0,30*1000);
        mTimer.schedule(mWeatherTask,0,60*60*1000);
    }

    private void initLocation() {
        LocationUtils.getCurrentLocation(this, new LocationUtils.LocationCallBack() {
            @Override
            public void onSuccess(Location location) {
                KLog.d("lanzhu","精度="+location.getLongitude());
                KLog.d("lanzhu","纬度="+location.getLatitude());
                HttpUtils.sendOkHttpRequest(MapUrlUtils.getMapUrl(String.valueOf(location.getLongitude()), String.valueOf(location.getLatitude())), new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        KLog.d("lanzhu","获取城市失败="+e.getMessage());
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        final CityInfo info = gson.fromJson(response.body().string(), CityInfo.class);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                RCManager.getInstance().saveCityInfo(info);
                                KLog.d("lanzhu","info="+info.getResult().getAddressComponent().getCity());
                                showDrawerLocation();
                            }
                        });
                        HttpUtils.sendOkHttpRequest(MapUrlUtils.getWeatherUrl(info.getResult().getAddressComponent().getCity()), new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {
                                KLog.d("lanzhu","获取天气失败="+e.getMessage());
                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                WeatherEntity weather = gson.fromJson(response.body().string(), WeatherEntity.class);
                                RCManager.getInstance().saveWeatherInfo(weather);
                                KLog.d("lanzhu","获取天气="+weather.getData().getGanmao());
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        showDrawerWeather();
                                    }
                                });
                            }
                        });
                    }
                });
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


    public void showDrawerDate(){
        binding.mainTimeTv.setText(DateUtil.getDateNow("HH:mm"));
        binding.mainWeekTv.setText(DateUtil.getWeek());
        StringBuilder builder = new StringBuilder();
        builder.append(DateUtil.getDateNow("yyyy"))
                .append("年")
                .append(DateUtil.getDateNow("MM"))
                .append("月")
                .append(DateUtil.getDateNow("dd"))
                .append("日");
        binding.mainDateTv.setText(builder.toString());
        int[] lunar = LunarCalendar.solarToLunar(Integer.valueOf(DateUtil.getDateNow("yyyy")),
                Integer.valueOf(DateUtil.getDateNow("MM")),
                Integer.valueOf(DateUtil.getDateNow("dd")));
        binding.mainCalendarTv.setText(LunarCalendar.LUNAR_MONTH_INFO[(lunar[1]-1)]+LunarCalendar.LAUNAR_MONTH_DAY_INFO[(lunar[2]-1)]);
    }


    private void showDrawerWeather(){
        if(null != RCManager.getInstance().getWeatherEntity()){
            WeatherEntity entity = RCManager.getInstance().getWeatherEntity();
            binding.mainTempTv.setText(entity.getData().getWendu()+"℃");
            binding.mainHighTempTv.setText(entity.getData().getForecast().get(DateUtil.getWeekNumber()).getLow()+"~"+
                    entity.getData().getForecast().get(DateUtil.getWeekNumber()).getHigh());
            binding.mainWeatherTv.setText(entity.getData().getForecast().get(DateUtil.getWeekNumber()).getType());
        }
    }

    private void showDrawerLocation(){
        CityInfo info = RCManager.getInstance().getCityInfo();
        if(null != info){
            binding.mainAddressTv.setText(info.getResult().getAddressComponent().getCity());
        }
    }


    @Override
    protected void onDestroy() {
        if(null != mWeatherTask){
            mWeatherTask.cancel();
        }
        if(null != mDateTask){
            mDateTask.cancel();
        }
        if(null != mTimer){
            mTimer.cancel();
        }
        KLog.d("lanzhu","定时器取消了");
        super.onDestroy();
    }
}
