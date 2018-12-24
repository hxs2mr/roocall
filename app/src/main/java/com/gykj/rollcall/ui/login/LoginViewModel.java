package com.gykj.rollcall.ui.login;

import android.app.Application;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.gykj.mvvmlibrary.base.BaseViewModel;
import com.gykj.mvvmlibrary.binding.command.BindingAction;
import com.gykj.mvvmlibrary.binding.command.BindingCommand;
import com.gykj.mvvmlibrary.entity.BaseEntity;
import com.gykj.mvvmlibrary.utils.ToastUtils;
import com.gykj.rollcall.ui.index.MainActivity;
import com.gykj.rollcall.entity.TokenEntity;
import com.gykj.rollcall.model.api.RollCallApi;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * desc   : 登录ViewModel
 * author : josh.lu
 * e-mail : 1113799552@qq.com
 * date   : 2018/12/2412:29
 * version: 1.0
 */
public class LoginViewModel extends BaseViewModel {

    //用户名的绑定
    public ObservableField<String> userName = new ObservableField<>("");
    //密码的绑定
    public ObservableField<String> password = new ObservableField<>("");

    public LoginViewModel(@NonNull Application application) {
        super(application);
    }

    //登录按钮的点击事件
    public BindingCommand loginOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            login();
        }
    });


    /**
     * 网络模拟一个登陆操作
     **/
    private void login() {
        if (TextUtils.isEmpty(userName.get())) {
            ToastUtils.showShort("请输入账号！");
            return;
        }
        if (TextUtils.isEmpty(password.get())) {
            ToastUtils.showShort("请输入密码！");
            return;
        }
        showDialog("努力加载中...");
        Disposable subscribe = RollCallApi.getInstance().login(userName.get(), password.get())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseEntity<TokenEntity>>() {
                    @Override
                    public void accept(BaseEntity<TokenEntity> tokenEntityBaseEntity) throws Exception {
                        if(tokenEntityBaseEntity.isOk()){
                            dismissDialog();
                            //进入DemoActivity页面
                            startActivity(MainActivity.class);
                        }else {
                            ToastUtils.showShort(tokenEntityBaseEntity.getMsg());
                        }

                    }
                },getErrorConsumer());
        addDisposable(subscribe);

    }

    @Override
    public void onDestroy() {
        if(isAttached()){
            cancel();
        }
        super.onDestroy();
    }



}
