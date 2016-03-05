package com.yzd.android.mcs_phone.ui.fragment;


import android.app.Fragment;
import android.view.View;

import com.github.obsessive.library.eventbus.EventCenter;
import com.github.obsessive.library.smartlayout.SmartTabLayout;
import com.github.obsessive.library.widgets.XViewPager;
import com.yzd.android.mcs_phone.R;
import com.yzd.android.mcs_phone.base.fragment.BaseFragment;
import com.yzd.android.mcs_phone.bean.other.BaseEntity;
import com.yzd.android.mcs_phone.presenter.impl.AirContainPresenterImpl;
import com.yzd.android.mcs_phone.ui.adapter.AirContainerAdapter;
import com.yzd.android.mcs_phone.view.ICommonContainerView;

import java.util.List;

import butterknife.InjectView;

/**
 * A simple {@link Fragment} subclass.
 */
public class AirContainerFragment extends BaseFragment implements ICommonContainerView {


    @InjectView(R.id.fragment_images_pager)
    XViewPager mViewPager;

    @InjectView(R.id.fragment_images_tab_smart)
    SmartTabLayout mSmartTabLayout;

    @Override
    protected void initViewsAndEvents() {

        AirContainPresenterImpl airContainPresenter = new AirContainPresenterImpl(getActivity(), this);
        airContainPresenter.initialized();
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_air_container;
    }

    @Override
    public void initializePagerViews(List<BaseEntity> categoryList) {
        if (null != categoryList && !categoryList.isEmpty()) {
            mViewPager.setOffscreenPageLimit(categoryList.size());
            mViewPager.setAdapter(new AirContainerAdapter(getSupportFragmentManager(), categoryList));
            mSmartTabLayout.setViewPager(mViewPager);
        }
    }


    @Override
    protected void onFirstUserVisible() {

    }

    @Override
    protected void onUserVisible() {

    }

    @Override
    protected void onUserInvisible() {

    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    @Override
    protected void onEventComming(EventCenter eventCenter) {

    }

    @Override
    protected void onEventComming(List<EventCenter> eventCenter) {

    }

    @Override
    protected boolean isBindEventBusHere() {
        return false;
    }
}
