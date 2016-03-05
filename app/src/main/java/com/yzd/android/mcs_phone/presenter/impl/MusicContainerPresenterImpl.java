package com.yzd.android.mcs_phone.presenter.impl;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.View;

import com.yzd.android.mcs_phone.R;
import com.yzd.android.mcs_phone.api.MusicConstants;
import com.yzd.android.mcs_phone.bean.database.MusicsListEntity;
import com.yzd.android.mcs_phone.interator.IBaseContainInterator;
import com.yzd.android.mcs_phone.interator.impl.MusicContainerInteratorImpl;
import com.yzd.android.mcs_phone.model.impl.SelectDataBaseImpl;
import com.yzd.android.mcs_phone.presenter.Presenter;
import com.yzd.android.mcs_phone.services.MusicService;
import com.yzd.android.mcs_phone.services.player.MusicPlayState;
import com.yzd.android.mcs_phone.ui.activity.MusicPlayActivity;
import com.yzd.android.mcs_phone.ui.activity.MusicSearchActivity;
import com.yzd.android.mcs_phone.ui.adapter.MiniPlayerAdapter;
import com.yzd.android.mcs_phone.view.ICommonContainerView;
import com.yzd.android.mcs_phone.view.IMusicContainerView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/10/16.
 */
public class MusicContainerPresenterImpl implements Presenter {

    private Context mContext;
    private IMusicContainerView mCommonContainerView;
    private IBaseContainInterator mMusicContainerInterator;
    private MiniPlayerAdapter mMiniPlayerAdapter;
    private ArrayList<MusicsListEntity> musicsListEntities = new ArrayList<>();

    public MusicContainerPresenterImpl(Context context, IMusicContainerView commonContainerView) {
        mContext = context;
        mCommonContainerView = commonContainerView;
        mMusicContainerInterator = new MusicContainerInteratorImpl();
    }

    @Override
    public void initialized() {
        mCommonContainerView.initializePagerViews(mMusicContainerInterator.getCommonCategoryList(mContext));
    }

    public void onItemClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_play_mini) {
        } else if (id == R.id.btn_search_mini_player) {
            mCommonContainerView.startActivity_1(MusicSearchActivity.class);

        } else if (id == R.id.btn_play_mini_player) {
            if (MusicService.isPlay()) {
                LocalBroadcastManager.getInstance(mContext).sendBroadcast(new Intent(MusicPlayState.ACTION_MUSIC_PAUSE));
                mCommonContainerView.setPauseBackGround();
            } else {
                LocalBroadcastManager.getInstance(mContext).sendBroadcast(new Intent(MusicPlayState.ACTION_MUSIC_REPLAY));
                mCommonContainerView.setPlayBackGround();
            }
        }
    }

    public void initMiniPlayerViewPager() {
        searchMusic(MusicConstants.MEET_BEFORE);
        mMiniPlayerAdapter = new MiniPlayerAdapter(mCommonContainerView.getFM(), musicsListEntities);
        mCommonContainerView.initMiniPlayerViewPager(mMiniPlayerAdapter);
        if (musicsListEntities != null && !musicsListEntities.isEmpty()) {

            MusicService.refreshMusicList(musicsListEntities);
        }
    }

    public void searchMusic(String key) {
        musicsListEntities.clear();
        try {
            musicsListEntities = (ArrayList<MusicsListEntity>) SelectDataBaseImpl.getInstances().selectMusicLists(key);
        } catch (Exception e) {
            Log.d("------Music", "数据库读取异常！");
        }
    }

    public void updateMiniAdapter() {
        mMiniPlayerAdapter.updateMusicList(musicsListEntities);
        mMiniPlayerAdapter.sendBroadcast(mContext);
    }
}
