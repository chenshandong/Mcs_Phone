package com.yzd.android.mcs_phone.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.fenjuly.mylibrary.SpinnerLoader;
import com.github.obsessive.library.eventbus.EventCenter;
import com.github.obsessive.library.netstatus.NetUtils;
import com.yzd.android.mcs_phone.R;
import com.yzd.android.mcs_phone.api.MusicConstants;
import com.yzd.android.mcs_phone.base.activity.BaseActivity;
import com.yzd.android.mcs_phone.bean.database.MusicsListEntity;
import com.yzd.android.mcs_phone.model.impl.DeleteDataBaseImpl;
import com.yzd.android.mcs_phone.model.impl.SaveDataBaseImpl;
import com.yzd.android.mcs_phone.presenter.impl.MusicSearchPresenterImpl;
import com.yzd.android.mcs_phone.ui.adapter.MusicSearchAdapter;
import com.yzd.android.mcs_phone.view.IMusicSearchView;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import de.greenrobot.event.EventBus;

public class MusicSearchActivity extends BaseActivity implements IMusicSearchView {

    @InjectView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @InjectView(R.id.one)
    SpinnerLoader mSpinnerLoader;

    private MusicSearchAdapter mMusicSearchAdapter;
    private int mPosition = -1;

    @Override
    protected boolean isApplyKitKatTranslucency() {
        return true;
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_music_search;
    }

    @Override
    protected void onEventComming(EventCenter eventCenter) {

    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    @Override
    protected void initViewsAndEvents() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setHasFixedSize(true);

        mMusicSearchAdapter = new MusicSearchAdapter(this);
        mRecyclerView.setAdapter(mMusicSearchAdapter);

        MusicSearchPresenterImpl musicSearchPresenter = new MusicSearchPresenterImpl(this, this);
        musicSearchPresenter.initialized();

    }

    @Override
    protected void onNetworkConnected(NetUtils.NetType type) {

    }

    @Override
    protected void onNetworkDisConnected() {

    }

    @Override
    protected boolean isApplyStatusBarTranslucency() {
        return true;
    }

    @Override
    protected boolean isBindEventBusHere() {
        return false;
    }

    @Override
    protected boolean toggleOverridePendingTransition() {
        return false;
    }

    @Override
    protected TransitionMode getOverridePendingTransitionMode() {
        return null;
    }

    @Override
    public void refreshRecyclerView(List<MusicsListEntity> musicsListEntities) {
        mSpinnerLoader.setVisibility(View.INVISIBLE);
        mMusicSearchAdapter.setMusicsListEntities(musicsListEntities);
        mMusicSearchAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_music_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.music_add_brfore:
                mPosition = 0;
                saveMusic(MusicConstants.MEET_BEFORE);
                return true;
            case R.id.music_add_ing:
                mPosition = 1;
                saveMusic(MusicConstants.MEET_ING);
                return true;
            case R.id.music_add_relax:
                mPosition = 2;
                saveMusic(MusicConstants.RELAX);
                return true;
            case R.id.music_add_active:
                mPosition = 3;
                saveMusic(MusicConstants.ACTIVE);
                return true;
            case R.id.music_add_pri:
                mPosition = 4;
                saveMusic(MusicConstants.PRIZE);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    private void saveMusic(String key) {

        ArrayList<MusicsListEntity> addMusicLists = (ArrayList<MusicsListEntity>) mMusicSearchAdapter.getAddMusicLists();
        Intent intent = new Intent(key);
        intent.putParcelableArrayListExtra(MusicConstants.MUSIC_SEARCH_RESULT, addMusicLists);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
        finish();

        if (addMusicLists != null && addMusicLists.size() > 0) {
            try {
                DeleteDataBaseImpl.getInstances().deleteMusicList(key);
            } catch (Exception e) {
            }

            SaveDataBaseImpl.getInstances().saveMusicList(addMusicLists, key);
        }
        EventBus.getDefault().post(new EventCenter<String>(mPosition, "hideError"));
    }
}
