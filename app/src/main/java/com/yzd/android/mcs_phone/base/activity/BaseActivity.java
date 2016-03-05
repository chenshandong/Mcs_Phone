package com.yzd.android.mcs_phone.base.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.github.obsessive.library.base.BaseAppCompatActivity;
import com.umeng.analytics.MobclickAgent;
import com.yzd.android.mcs_phone.api.McsApplication;
import com.yzd.android.mcs_phone.R;
import com.yzd.android.mcs_phone.view.BaseView;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2015/9/1.
 */
public abstract class BaseActivity extends BaseAppCompatActivity implements BaseView {


    protected Toolbar mToolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
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

    protected McsApplication getMcsApplication() {
        return (McsApplication) getApplication();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);// 友盟反馈
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
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

    /**
     * 判断是否4.4
     * @return
     */
    protected abstract boolean isApplyKitKatTranslucency();
}
