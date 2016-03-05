package com.yzd.android.mcs_phone.ui.fragment;


import android.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.github.obsessive.library.eventbus.EventCenter;
import com.yzd.android.mcs_phone.R;
import com.yzd.android.mcs_phone.base.fragment.BaseFragment;
import com.yzd.android.mcs_phone.presenter.impl.TvChannelPresenterImpl;
import com.yzd.android.mcs_phone.view.ITvChannelView;

import java.util.List;

import butterknife.InjectView;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvChannelFragment extends BaseFragment implements ITvChannelView{

    @InjectView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private TvChannelPresenterImpl mTvChannelPresenter;

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
    protected void initViewsAndEvents() {
        mTvChannelPresenter = new TvChannelPresenterImpl(getActivity(), this);
        mTvChannelPresenter.initialized();
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_tv_channel;
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

    @Override
    public void initRecyclerView(RecyclerView.LayoutManager layoutManager, RecyclerView.Adapter adapter) {
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(adapter);
    }


}
