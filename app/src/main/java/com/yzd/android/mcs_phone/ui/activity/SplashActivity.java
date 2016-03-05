package com.yzd.android.mcs_phone.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.github.obsessive.library.eventbus.EventCenter;
import com.github.obsessive.library.netstatus.NetUtils;
import com.github.obsessive.library.utils.SPUtils;
import com.yzd.android.mcs_phone.R;
import com.yzd.android.mcs_phone.api.McsApplication;
import com.yzd.android.mcs_phone.base.activity.BaseActivity;
import com.yzd.android.mcs_phone.presenter.impl.SplashPresenterImpl;
import com.yzd.android.mcs_phone.udp.InitData.ByteMerge;
import com.yzd.android.mcs_phone.utils.NdkUtils;
import com.yzd.android.mcs_phone.view.SplashView;
import com.yzd.android.mcs_phone.widgets.LoginCommentButton;
import com.yzd.android.mcs_phone.widgets.OkCommentButton;

import butterknife.InjectView;

public class SplashActivity extends BaseActivity implements SplashView, LoginCommentButton.OnSendClickListener, OkCommentButton.OnSendClickListener {

    @InjectView(R.id.splash_image)
    ImageView mSplashImageview;

    @InjectView(R.id.btnLogin)
    LoginCommentButton btnSendComment;

    @InjectView(R.id.btnOk)
    OkCommentButton btnOk;

    @InjectView(R.id.userName)
    EditText mEdtUserName;

    @InjectView(R.id.passWord)
    EditText mEdtPassWord;

    @InjectView(R.id.ipEdt)
    EditText mIP;

    @InjectView(R.id.portEdt)
    EditText mPort;

    @InjectView(R.id.splash_viewFlipper)
    ViewFlipper mViewFlipper;

    private SplashPresenterImpl mSplashPresenter;
    private McsApplication mApplication;

    @Override
    protected boolean isApplyKitKatTranslucency() {
        return false;
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_splash;
    }

    @Override
    protected void onEventComming(EventCenter eventCenter) {

    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    @Override
    protected void initViewsAndEvents() {

        mApplication = getMcsApplication();
        boolean setSuccess = (boolean) SPUtils.get(this, "setSuccess", false);
        if (mApplication.isFirstLaunch() && !setSuccess) {

            mViewFlipper.setDisplayedChild(1);
        }
        mSplashPresenter = new SplashPresenterImpl(this, this);
        mSplashPresenter.initialized();

        btnSendComment.setOnSendClickListener(this);
        btnOk.setOnSendClickListener(this);
    }

    @Override
    protected void onNetworkConnected(NetUtils.NetType type) {
    }

    @Override
    protected void onNetworkDisConnected() {
        showToast("当前网络不可用");
    }

    @Override
    protected boolean isApplyStatusBarTranslucency() {
        return true;
    }

    @Override
    protected boolean isBindEventBusHere() {
        return false;
    }

    @Override
    protected boolean toggleOverridePendingTransition() {
        return false;
    }

    @Override
    protected TransitionMode getOverridePendingTransitionMode() {
        return null;
    }

    @Override
    public void animateBackgroundImage(Animation animation) {
    }

    @Override
    public void initializeViews(String versionName, String copyright, int backgroundResId) {

    }

    @Override
    public void initializeUmengConfig() {

    }

    @Override
    public void navigateToHomePage() {
        readyGoThenKill(IndexActivity.class);
    }

    @Override
    public String getUserName() {
        return mEdtUserName.getText().toString().trim();
    }

    @Override
    public String getPassWord() {
        return mEdtPassWord.getText().toString().trim();
    }

    @Override
    public String getIP() {
        return mIP.getText().toString().trim();
    }

    @Override
    public String getPort() {
        return mPort.getText().toString().trim();
    }


    @Override
    public void onSendClickListener(View v) {
        int id = v.getId();
        if (id == R.id.btnLogin) {
            mSplashPresenter.login(btnSendComment, mApplication.isDebugMode());
        } else {
            mSplashPresenter.setIP((OkCommentButton) v, mViewFlipper);
        }

    }

}
