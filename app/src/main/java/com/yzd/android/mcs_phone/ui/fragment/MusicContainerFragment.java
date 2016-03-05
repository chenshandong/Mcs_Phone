package com.yzd.android.mcs_phone.ui.fragment;


import android.app.Fragment;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.obsessive.library.eventbus.EventCenter;
import com.github.obsessive.library.smartlayout.SmartTabLayout;
import com.github.obsessive.library.widgets.XViewPager;
import com.yzd.android.mcs_phone.R;
import com.yzd.android.mcs_phone.api.EvenBusConstants;
import com.yzd.android.mcs_phone.api.MusicConstants;
import com.yzd.android.mcs_phone.base.fragment.BaseFragment;
import com.yzd.android.mcs_phone.bean.database.MusicsListEntity;
import com.yzd.android.mcs_phone.bean.other.BaseEntity;
import com.yzd.android.mcs_phone.presenter.impl.MusicContainerPresenterImpl;
import com.yzd.android.mcs_phone.services.MusicService;
import com.yzd.android.mcs_phone.ui.activity.MusicPlayActivity;
import com.yzd.android.mcs_phone.ui.activity.MusicSearchActivity;
import com.yzd.android.mcs_phone.ui.adapter.MiniPlayerAdapter;
import com.yzd.android.mcs_phone.ui.adapter.MusicContainerAdapter;
import com.yzd.android.mcs_phone.view.ICommonContainerView;
import com.yzd.android.mcs_phone.view.IMusicContainerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import de.greenrobot.event.EventBus;


/**
 * A simple {@link Fragment} subclass.
 */
public class MusicContainerFragment extends BaseFragment implements IMusicContainerView, View.OnClickListener, ViewPager.OnPageChangeListener {



    @InjectView(R.id.fragment_music_pager)
    XViewPager mViewPager;

    @InjectView(R.id.fragment_music_tab_smart)
    SmartTabLayout mSmartTabLayout;
    @InjectView(R.id.viewPager)
    ViewPager mMiniPlayerViewPager;
    @InjectView(R.id.btn_play_mini_player)
    RelativeLayout mBtnPlay;
    @InjectView(R.id.btn_play_mini)
    ImageView mImageViewPlay;
    @InjectView(R.id.btn_search_mini_player)
    RelativeLayout mBtnSearch;
    private MusicContainerPresenterImpl musicContainerPresenter;
    private String mLastMusicKey = MusicConstants.MEET_BEFORE;
    private int mPosition;
    private Intent intent;


    @Override
    protected void initViewsAndEvents() {

        musicContainerPresenter = new MusicContainerPresenterImpl(getActivity(), this);
        musicContainerPresenter.initialized();

        mBtnPlay.setOnClickListener(this);
        mBtnSearch.setOnClickListener(this);
        mMiniPlayerViewPager.setOnPageChangeListener(this);
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_music_container;
    }

    @Override
    public void initializePagerViews(List<BaseEntity> categoryList) {
        if (null != categoryList && !categoryList.isEmpty()) {
            mViewPager.setOffscreenPageLimit(categoryList.size());
            mViewPager.setAdapter(new MusicContainerAdapter(getSupportFragmentManager(), categoryList));
            mSmartTabLayout.setViewPager(mViewPager);
        }

    }

    @Override
    public void initMiniPlayerViewPager(FragmentPagerAdapter adapter) {
        mMiniPlayerViewPager.setAdapter(adapter);
    }

    @Override
    public void startActivity_1(Class clazz) {
        readyGo(clazz);
    }


    @Override
    public void setPlayBackGround() {
        mImageViewPlay.setBackground(getResources().getDrawable(R.mipmap.btn_mini_player_play));
    }

    @Override
    public void setPauseBackGround() {
        mImageViewPlay.setBackground(getResources().getDrawable(R.mipmap.btn_mini_player_pause));
    }

    @Override
    public FragmentManager getFM() {
        return getSupportFragmentManager();
    }

    @Override
    public void showSnackBar(String str) {
        showToast(str);
    }


    @Override
    protected void onFirstUserVisible() {
        musicContainerPresenter.initMiniPlayerViewPager();
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
        if (eventCenter.getEventCode() == EvenBusConstants.ECEN_MUSIC_KEY) {
            if (!mLastMusicKey.equals(eventCenter.getData())) {
                mLastMusicKey = (String) eventCenter.getData();
                musicContainerPresenter.searchMusic(mLastMusicKey);
                musicContainerPresenter.updateMiniAdapter();
            }
            mPosition = eventCenter.getPosition();
            mMiniPlayerViewPager.setOnPageChangeListener(null);
            mMiniPlayerViewPager.setCurrentItem(mPosition, false);
            mMiniPlayerViewPager.setOnPageChangeListener(this);

        }
    }

    @Override
    public void onResume() {
        super.onResume();
        intent = new Intent(mContext, MusicService.class);
        mContext.startService(intent);
    }

    @Override
    protected void onEventComming(List<EventCenter> eventCenter) {
    }

    @Override
    protected boolean isBindEventBusHere() {
        return true;
    }

    @Override
    public void onClick(View view) {
        musicContainerPresenter.onItemClick(view);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(final int position) {
        mMiniPlayerViewPager.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent("ACTION_MUSIC_PLAY");
                intent.putExtra("position", position);
                LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);
                EventBus.getDefault().post(new EventCenter<String>(EvenBusConstants.EVEN_MUSIC_SHOW_INDICATOR));
            }
        }, 1000);

    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }
}
