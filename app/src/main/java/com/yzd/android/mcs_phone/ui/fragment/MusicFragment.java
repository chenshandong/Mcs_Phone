package com.yzd.android.mcs_phone.ui.fragment;


import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.obsessive.library.eventbus.EventCenter;
import com.yzd.android.mcs_phone.R;
import com.yzd.android.mcs_phone.api.EvenBusConstants;
import com.yzd.android.mcs_phone.api.MusicConstants;
import com.yzd.android.mcs_phone.base.fragment.BaseFragment;
import com.yzd.android.mcs_phone.bean.database.MusicsListEntity;
import com.yzd.android.mcs_phone.presenter.impl.MusicListPresenterImpl;
import com.yzd.android.mcs_phone.services.MusicService;
import com.yzd.android.mcs_phone.ui.activity.MusicSearchActivity;
import com.yzd.android.mcs_phone.view.IMusicListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

/**
 * A simple {@link Fragment} subclass.
 */
public class MusicFragment extends BaseFragment implements IMusicListView, View.OnClickListener {

    @InjectView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @InjectView(R.id.message_icon)
    ImageView mMessageIcon;

    @InjectView(R.id.message_info)
    TextView mMessageInfo;

    private BroadcastReceiver musicReceive;
    private MusicListPresenterImpl mMusicListPresenter;
    private String musicKey;
    private int mPosition;

    @Override
    protected void onFirstUserVisible() {
        mMusicListPresenter.initialized();
    }

    @Override
    protected void onUserVisible() {
        mMusicListPresenter.showIndicator();
    }

    @Override
    protected void onUserInvisible() {
        mMusicListPresenter.showIndicator();
    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    @Override
    protected void initViewsAndEvents() {

        mMessageIcon.setOnClickListener(this);

        mMusicListPresenter = new MusicListPresenterImpl(getActivity(), this);
        musicKey = mMusicListPresenter.getBundle(this);
        mPosition = mMusicListPresenter.getPosition();


    }


    @Override
    public void onResume() {
        super.onResume();
        musicReceive = new MusicSearchReceive();
        IntentFilter intentFilter = new IntentFilter(musicKey);
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(musicReceive, intentFilter);

    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(musicReceive);
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_music;
    }

    @Override
    protected void onEventComming(EventCenter eventCenter) {
        if (mPosition == eventCenter.getEventCode()) {

            String data = (String) eventCenter.getData();
            if (data.equals("showError")) {
                showError();
            } else if (data.equals("hideError")) {
                hideError();
            }

        } else if (EvenBusConstants.EVEN_MUSIC_SHOW_INDICATOR == eventCenter.getEventCode()) {
            mMusicListPresenter.showIndicator();
        }

    }

    @Override
    protected void onEventComming(List<EventCenter> eventCenter) {

    }

    @Override
    protected boolean isBindEventBusHere() {
        return true;
    }

    @Override
    public void initRecyclerView(RecyclerView.LayoutManager layoutManager, RecyclerView.Adapter adapter) {
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void showError() {
        mMessageIcon.setVisibility(View.VISIBLE);
        mMessageInfo.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideError() {
        mMessageIcon.setVisibility(View.INVISIBLE);
        mMessageInfo.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onClick(View view) {
        readyGo(MusicSearchActivity.class);
    }

    private class MusicSearchReceive extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            ArrayList<MusicsListEntity> addMusicLists = intent.getParcelableArrayListExtra(MusicConstants.MUSIC_SEARCH_RESULT);
            mMusicListPresenter.updateMusicList(addMusicLists);
        }
    }
}
