package com.yzd.android.mcs_phone.base.fragment;

import com.github.obsessive.library.base.BaseLazyFragment;
import com.umeng.analytics.MobclickAgent;
import com.yzd.android.mcs_phone.view.BaseView;

/**
 */
public abstract class BaseFragment extends BaseLazyFragment implements BaseView {

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(TAG_LOG);
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(TAG_LOG);
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
}
