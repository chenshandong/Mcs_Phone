package com.yzd.android.mcs_phone.presenter.impl;

import android.content.Context;

import com.yzd.android.mcs_phone.bean.database.MusicsListEntity;
import com.yzd.android.mcs_phone.presenter.IMusicPresenter;
import com.yzd.android.mcs_phone.view.IMusicView;

/**
 * Created by Clearlove on 15/10/18.
 */
public class MusicPresenterImpl implements IMusicPresenter {


    private Context mContext;
    private IMusicView mMusicView;

    public MusicPresenterImpl(Context context, IMusicView musicView) {
        mContext = context;
        mMusicView = musicView;
    }




    @Override
    public void loadListData(String requestTag, String keywords, int event_tag) {

    }

    @Override
    public void onNextClick() {
        mMusicView.playNextMusic();
    }

    @Override
    public void onPrevClick() {
        mMusicView.playPrevMusic();
    }

    @Override
    public void onStartPlay() {
        mMusicView.startPlayMusic();
    }

    @Override
    public void onPausePlay() {
        mMusicView.pausePlayMusic();
    }

    @Override
    public void onRePlay() {
        mMusicView.rePlayMusic();
    }

    @Override
    public void seekTo(int position) {
        mMusicView.seekToPosition(position);
    }

    @Override
    public void onStopPlay() {
        mMusicView.stopPlayMusic();
    }

    @Override
    public void refreshPageInfo(MusicsListEntity entity, int totalDuration) {
        mMusicView.refreshPageInfo(entity, totalDuration);
    }

    @Override
    public void refreshProgress(int progress) {
        mMusicView.refreshPlayProgress(progress);
    }

    @Override
    public void refreshSecondProgress(int progress) {

    }
}
