package com.yzd.android.mcs_phone.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.EditText;

import com.github.obsessive.library.eventbus.EventCenter;
import com.github.obsessive.library.netstatus.NetUtils;
import com.yzd.android.mcs_phone.R;
import com.yzd.android.mcs_phone.base.activity.BaseSwipeBackActivity;
import com.yzd.android.mcs_phone.presenter.impl.SetPresenterImpl;
import com.yzd.android.mcs_phone.view.ISetView;
import com.yzd.android.mcs_phone.widgets.OkCommentButton;
import com.yzd.android.mcs_phone.widgets.RevealBackgroundView;

import butterknife.InjectView;

public class SetActivity extends BaseSwipeBackActivity implements View.OnClickListener, ISetView, OkCommentButton.OnSendClickListener {

    @InjectView(R.id.reveal)
    RevealBackgroundView mReveal;

    @InjectView(R.id.view1)
    FloatingActionButton mView1;

    @InjectView(R.id.view2)
    FloatingActionButton mView2;

    @InjectView(R.id.view3)
    FloatingActionButton mView3;

    @InjectView(R.id.view4)
    FloatingActionButton mView4;

    @InjectView(R.id.view5)
    FloatingActionButton mView5;

    @InjectView(R.id.ipEdt)
    EditText mIPEdt;

    @InjectView(R.id.portEdt)
    EditText mPortEdt;

    @InjectView(R.id.btnOk)
    OkCommentButton mOkBtn;

    private SetPresenterImpl mSetPresenterImpl;
    private boolean comeFromNewFeature = false;

    @Override
    protected boolean isOpenSetTheme() {
        return true;
    }

    @Override
    protected boolean isApplyKitKatTranslucency() {
        return true;
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_set;
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

        if (getIntent() != null) {

            String action = getIntent().getAction();
            if (action != null && action.equals("newFeatureFragment")) {
                comeFromNewFeature = true;
            }
        }

        mView1.setOnClickListener(this);
        mView2.setOnClickListener(this);
        mView3.setOnClickListener(this);
        mView4.setOnClickListener(this);
        mView5.setOnClickListener(this);
        mOkBtn.setOnSendClickListener(this);

        mSetPresenterImpl = new SetPresenterImpl(this, this);
        mSetPresenterImpl.initialized();
    }

    @Override
    protected void onNetworkConnected(NetUtils.NetType type) {

    }

    @Override
    protected void onNetworkDisConnected() {

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
        return TransitionMode.RIGHT;
    }

    @Override
    public void onClick(View view) {
        mSetPresenterImpl.viewOnClick(view);
    }

    @Override
    public RevealBackgroundView getReveal() {
        return mReveal;
    }

    @Override
    public String getIp() {
        return mIPEdt.getText().toString().trim();
    }

    @Override
    public String getPort() {
        return mPortEdt.getText().toString().trim();
    }

    @Override
    public OkCommentButton getOkBtn() {
        return mOkBtn;
    }

    @Override
    public void killNoAnimation() {
        readyGoNoAnimation(SetActivity.class);
    }

    @Override
    public void clearEdt() {
        mIPEdt.setText("");
        mPortEdt.setText("");
    }

    @Override
    public EditText getEdt() {
        return mIPEdt;
    }

    @Override
    public void onSendClickListener(View v) {
        boolean b = mSetPresenterImpl.customViewOnClick(v);
        if (b) {

            if (comeFromNewFeature) {
                Intent data = new Intent();
                data.putExtra("setSuccess", true);
                setResult(0, data);
            }
            finish();

        }

    }
}
