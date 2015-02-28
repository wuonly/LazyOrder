package com.cisoft.shop.welcome.view;import android.widget.ImageView;import com.cisoft.myapplication.R;import com.cisoft.shop.MainActivity;import com.cisoft.shop.login.view.LoginActivity;import com.cisoft.shop.welcome.presenter.WelcomePresenter;import org.kymjs.aframe.ui.activity.BaseSplash;public class WelcomeActivity extends BaseSplash implements IWelcomeView{    @Override    protected void setRootBackground(ImageView view) {        view.setBackgroundResource(R.drawable.ic_launcher);    }    @Override    protected void redirectTo() {        super.redirectTo();        new WelcomePresenter(this, this).checkAccount();//        skipActivity(this, SimpleActivity.class);    }    @Override    public void skipToLogin() {        skipActivity(this, LoginActivity.class);    }    @Override    public void skipToMain() {        skipActivity(this, MainActivity.class);    }}