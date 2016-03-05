package com.yzd.android.mcs_phone.presenter.impl;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.github.obsessive.library.utils.SPUtils;
import com.yzd.android.mcs_phone.api.EvenBusConstants;
import com.yzd.android.mcs_phone.api.MusicConstants;
import com.yzd.android.mcs_phone.bean.database.MusicsListEntity;
import com.yzd.android.mcs_phone.model.impl.SelectDataBaseImpl;
import com.yzd.android.mcs_phone.presenter.IMusicListPresenter;
import com.yzd.android.mcs_phone.services.MusicService;
import com.yzd.android.mcs_phone.ui.adapter.MusicListAdapter;
import com.yzd.android.mcs_phone.ui.fragment.MusicFragment;
import com.yzd.android.mcs_phone.view.IMusicListView;

import java.util.ArrayList;

/**
 * Created by Clearlove on 15/11/2.
 */
public class MusicListPresenterImpl implements IMusicListPresenter {


    private IMusicListView mMusicListView;
    private Context mContext;
    private int mPosition;
    private String musicKey;
    private MusicListAdapter mMusicListAdapter;

    public MusicListPresenterImpl(Context context, IMusicListView musicListView) {
        mMusicListView = musicListView;
        mContext = context;

    }

    @Override
    public void updateMusicList(ArrayList<MusicsListEntity> musicsListEntities) {
        if (mMusicListAdapter != null) {
            mMusicListAdapter.setMusicsListEntities(musicsListEntities);
            mMusicListAdapter.notifyDataSetChanged();

        }
    }

    @Override
    public String getBundle(MusicFragment musicFragment) {
        Bundle bundle = musicFragment.getArguments();
        mPosition = bundle.getInt("position");
        musicKey = null;
        switch (mPosition) {
            case 0:
                musicKey = MusicConstants.MEET_BEFORE;
                break;
            case 1:
                musicKey = MusicConstants.MEET_ING;
                break;
            case 2:
                musicKey = MusicConstants.RELAX;
                break;
            case 3:
                musicKey = MusicConstants.ACTIVE;
                break;
            case 4:
                musicKey = MusicConstants.PRIZE;
                break;
        }
        return musicKey;

    }

    @Override
    public int getPosition() {
        return mPosition;
    }

    @Override
    public void showIndicator() {
        String s = (String) SPUtils.get(mContext, EvenBusConstants.SAVE_MUSIC_KEY, "MusicConstants.MEET_BEFORE");
        if (s.equals(musicKey)) {
            if (mMusicListAdapter != null) {
                mMusicListAdapter.setShowIndex(MusicService.getPlayPosition()+1);
                mMusicListAdapter.notifyDataSetChanged();
            }
        }

    }

    @Override
    public void initialized() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        mMusicListAdapter = new MusicListAdapter(mContext, mPosition, musicKey);

        mMusicListView.initRecyclerView(linearLayoutManager, mMusicListAdapter);

        ArrayList<MusicsListEntity> musicsListEntities = null;
        try {
            musicsListEntities = (ArrayList<MusicsListEntity>) SelectDataBaseImpl.getInstances().selectMusicLists(musicKey);
        } catch (Exception e) {

        }
        if (musicsListEntities != null && musicsListEntities.size() > 0) {
            mMusicListView.hideError();
        }
        updateMusicList(musicsListEntities);
    }
}
