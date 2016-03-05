package com.yzd.android.mcs_phone.presenter.impl;

import android.content.Context;
import android.os.AsyncTask;

import com.yzd.android.mcs_phone.bean.database.MusicsListEntity;
import com.yzd.android.mcs_phone.interator.impl.MusicSearchInteratorImpl;
import com.yzd.android.mcs_phone.presenter.Presenter;
import com.yzd.android.mcs_phone.view.IMusicSearchView;

import java.util.List;

/**
 * Created by Administrator on 2015/10/19.
 */
public class MusicSearchPresenterImpl implements Presenter {

    private Context mContext;
    private IMusicSearchView mMusicSearchView;
    private MusicSearchInteratorImpl musicSearchInterator;

    public MusicSearchPresenterImpl(Context context, IMusicSearchView musicSearchView){
        mContext = context;
        mMusicSearchView = musicSearchView;
        musicSearchInterator = new MusicSearchInteratorImpl();
    }

    @Override
    public void initialized() {
        MusicSearchTask musicSearchTask = new MusicSearchTask();
        musicSearchTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    private class MusicSearchTask extends AsyncTask<String, Void, List<MusicsListEntity>> {

        @Override
        protected List<MusicsListEntity> doInBackground(String... voids) {
            return musicSearchInterator.readMusic(mContext);
        }

        @Override
        protected void onPostExecute(List<MusicsListEntity> musicsListEntities) {
            super.onPostExecute(musicsListEntities);
            mMusicSearchView.refreshRecyclerView(musicsListEntities);
        }
    }
}
