package com.yzd.android.mcs_phone.ui.fragment;


import android.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.github.obsessive.library.eventbus.EventCenter;
import com.yzd.android.mcs_phone.R;
import com.yzd.android.mcs_phone.base.fragment.BaseFragment;
import com.yzd.android.mcs_phone.presenter.impl.CurtainPresenterImpl;
import com.yzd.android.mcs_phone.view.ICurtainView;

import java.util.List;

import butterknife.InjectView;
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;

/**
 * A simple {@link Fragment} subclass.
 */
public class CurtainFragment extends BaseFragment implements ICurtainView{

    @InjectView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private CurtainPresenterImpl mCurtainPresenter;

    @Override
    protected void onFirstUserVisible() {
        mCurtainPresenter.initialized();

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
        mCurtainPresenter = new CurtainPresenterImpl(getActivity(), this);
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_curtain;
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
    public void initRecycler(RecyclerView.LayoutManager layoutManager, RecyclerView.Adapter adapter) {

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setItemAnimator(new SlideInLeftAnimator());
    }
}
