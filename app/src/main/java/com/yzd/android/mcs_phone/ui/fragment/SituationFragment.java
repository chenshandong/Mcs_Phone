package com.yzd.android.mcs_phone.ui.fragment;


import android.app.Fragment;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.github.obsessive.library.eventbus.EventCenter;
import com.yzd.android.mcs_phone.R;
import com.yzd.android.mcs_phone.base.fragment.BaseFragment;
import com.yzd.android.mcs_phone.presenter.ISituationPresenter;
import com.yzd.android.mcs_phone.presenter.impl.SituationPresenterImpl;
import com.yzd.android.mcs_phone.presenter.impl.UdpSendOrder;
import com.yzd.android.mcs_phone.view.ISituationView;

import java.util.List;

import butterknife.InjectView;
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;

/**
 * A simple {@link Fragment} subclass.
 */
public class SituationFragment extends BaseFragment implements ISituationView, View.OnClickListener {

    @InjectView(R.id.recyclerView)
    RecyclerView mRecycleView;

    @InjectView(R.id.btn_day)
    ImageView mBtnDay;

    @InjectView(R.id.btn_night)
    ImageView mBtnNight;

    @InjectView(R.id.fab)
    FloatingActionButton mFab;

    private FragmentActivity mActivity;
    private ISituationPresenter mSituationPresenter;

    @Override
    protected void onFirstUserVisible() {
    }

    @Override
    protected void onUserVisible() {
        UdpSendOrder.getInstances().getAllEquipStatus(getActivity());
    }

    @Override
    protected void onUserInvisible() {
        UdpSendOrder.getInstances().getAllEquipStatus(getActivity());
    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    @Override
    protected void initViewsAndEvents() {
        mActivity = getActivity();

        mBtnDay.setOnClickListener(this);
        mBtnNight.setOnClickListener(this);
        mFab.setOnClickListener(this);

        mSituationPresenter = new SituationPresenterImpl(mActivity, this);
        mSituationPresenter.initialized();
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_situation;
    }

    @Override
    protected void onEventComming(EventCenter eventCenter) {
        mSituationPresenter.evenComming(eventCenter);
    }

    @Override
    protected void onEventComming(List<EventCenter> eventCenter) {

    }

    @Override
    protected boolean isBindEventBusHere() {
        return true;
    }

    @Override
    public ImageView getDayImageView() {
        return mBtnDay;
    }

    @Override
    public ImageView getNightImageView() {
        return mBtnNight;
    }

    @Override
    public void showDayMode() {
        mBtnDay.setBackground(getResources().getDrawable(R.mipmap.day_highlight));
        mBtnNight.setBackground(getResources().getDrawable(R.mipmap.night_default));
    }

    @Override
    public void showNightMode() {
        mBtnDay.setBackground(getResources().getDrawable(R.mipmap.day_default));
        mBtnNight.setBackground(getResources().getDrawable(R.mipmap.night_highlight));
    }

    @Override
    public void initRecyclerView(RecyclerView.LayoutManager layoutManager, RecyclerView.Adapter adapter) {
        mRecycleView.setLayoutManager(layoutManager);
        mRecycleView.setHasFixedSize(true);
        mRecycleView.setAdapter(adapter);
        mRecycleView.setItemAnimator(new SlideInLeftAnimator());
    }


    @Override
    public void onClick(View view) {
        mSituationPresenter.getOnClickViewId(view.getId());
    }
}
