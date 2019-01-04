package com.gykj.rollcall.ui.login;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;

import com.gykj.mvvmlibrary.base.BaseActivity;
import com.gykj.mvvmlibrary.entity.CityRealm;
import com.gykj.rollcall.R;
import com.gykj.rollcall.databinding.ActivityLoginBinding;
import com.gykj.rollcall.BR;
import com.gykj.rollcall.utils.ParseXmlUtils;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * desc   : 登录界面
 * author : josh.lu
 * e-mail : 1113799552@qq.com
 * date   : 2018/12/2412:28
 * version: 1.0
 */
public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginViewModel> {
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_login;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public LoginViewModel initViewModel() {
        return ViewModelProviders.of(this).get(LoginViewModel.class);
    }

    @Override
    public void initData() {
        RealmResults<CityRealm> all = Realm.getDefaultInstance().where(CityRealm.class).findAll();
        if(null == all || all.size() == 0){
            try {
                ParseXmlUtils.parseXml(getAssets().open("city.xml"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }
}
