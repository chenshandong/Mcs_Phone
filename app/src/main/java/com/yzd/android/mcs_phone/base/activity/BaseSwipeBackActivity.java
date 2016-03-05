package com.yzd.android.mcs_phone.base.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.github.obsessive.library.base.BaseSwipeBackCompatActivity;
import com.github.obsessive.library.utils.SPUtils;
import com.umeng.analytics.MobclickAgent;
import com.yzd.android.mcs_phone.R;
import com.yzd.android.mcs_phone.api.McsApplication;
import com.yzd.android.mcs_phone.view.BaseView;

import butterknife.ButterKnife;

public abstract class BaseSwipeBackActivity extends BaseSwipeBackCompatActivity implements BaseView {

    protected Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (isOpenSetTheme()) {
            setTheme((int)SPUtils.get(this, "AUTO_AET_THEME", R.style.AppTheme));
        }
        super.onCreate(savedInstanceState);
        if (isApplyKitKatTranslucency()) {
            setSystemBarTintDrawable(getResources().getDrawable(R.drawable.sr_primary));
        }
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        mToolbar = ButterKnife.findById(this, R.id.common_toolbar);
        if (null != mToolbar) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    protected McsApplication getBaseApplication() {
        return (McsApplication) getApplication();
    }


    @Override
    public void showError(String msg) {
        toggleShowError(true, msg, null);
    }

    @Override
    public void showException(String msg) {
        toggleShowError(true, msg, null);
    }

    @Override
    public void showNetError() {
        toggleNetworkError(true, null);
    }

    @Override
    public void showLoading(String msg) {
        toggleShowLoading(true, null);
    }

    @Override
    public void hideLoading() {
        toggleShowLoading(false, null);
    }

    protected abstract boolean isOpenSetTheme();

    protected abstract boolean isApplyKitKatTranslucency();
}
