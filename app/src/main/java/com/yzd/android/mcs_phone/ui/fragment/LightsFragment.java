package com.yzd.android.mcs_phone.ui.fragment;


import android.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.github.obsessive.library.eventbus.EventCenter;
import com.yzd.android.mcs_phone.R;
import com.yzd.android.mcs_phone.base.fragment.BaseFragment;
import com.yzd.android.mcs_phone.presenter.impl.LightPresenterImpl;
import com.yzd.android.mcs_phone.presenter.impl.UdpSendOrder;
import com.yzd.android.mcs_phone.view.ILightView;

import java.util.List;

import butterknife.InjectView;

/**
 * A simple {@link Fragment} subclass.
 */
public class LightsFragment extends BaseFragment implements ILightView{

    @InjectView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private LightPresenterImpl mLightPresenter;

    @Override
    protected void onFirstUserVisible() {
        mLightPresenter.initialized();
        UdpSendOrder.getInstances().getAllEquipStatus(getActivity());

    }

    @Override
    protected void onUserVisible() {
        UdpSendOrder.getInstances().getAllEquipStatus(getActivity());
    }

    @Override
    protected void onUserInvisible() {

    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    @Override
    protected void initViewsAndEvents() {
        mLightPresenter = new LightPresenterImpl(getActivity(), this);
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_lights;
    }

    @Override
    protected void onEventComming(EventCenter eventCenter) {
        mLightPresenter.evenComming(eventCenter);
    }

    @Override
    protected void onEventComming(List<EventCenter> eventCenter) {

    }

    @Override
    protected boolean isBindEventBusHere() {
        return true;
    }

    @Override
    public void initRecycler(RecyclerView.LayoutManager layoutManager, RecyclerView.Adapter adapter) {
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(adapter);
    }
}
