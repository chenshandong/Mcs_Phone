package com.yzd.android.mcs_phone.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.github.obsessive.library.eventbus.EventCenter;
import com.github.obsessive.library.netstatus.NetUtils;
import com.yzd.android.mcs_phone.R;
import com.yzd.android.mcs_phone.base.activity.BaseActivity;
import com.yzd.android.mcs_phone.other.CrossfadePageTransformer;
import com.yzd.android.mcs_phone.ui.adapter.NewFeatureAdapter;
import com.yzd.android.mcs_phone.widgets.CircleIndicator;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class NewFeatureActivity extends BaseActivity {


    @InjectView(R.id.viewPager)
    ViewPager mViewPager;
    @InjectView(R.id.indicator)
    CircleIndicator mIndicator;

    @Override
    protected boolean isApplyKitKatTranslucency() {
        return true;
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_new_feature;
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
        if (getMcsApplication().getNewFeature()) {
            mViewPager.setAdapter(new NewFeatureAdapter(getSupportFragmentManager()));
            mViewPager.setPageTransformer(true, new CrossfadePageTransformer());
            mIndicator.setViewPager(mViewPager);
        } else {
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(NewFeatureActivity.this, SplashActivity.class));
                    finish();
                }
            }, 500);
        }

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
        return null;
    }

}
