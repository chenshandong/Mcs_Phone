package com.yzd.android.mcs_phone.view;

import com.yzd.android.mcs_phone.bean.database.MusicsListEntity;

/**
 * Created by Clearlove on 15/10/18.
 */
public interface IMusicView {
    void refreshMusicsList();

    void addMoreMusicsList();

    void rePlayMusic();

    void startPlayMusic();

    void stopPlayMusic();

    void pausePlayMusic();

    void playNextMusic();

    void playPrevMusic();

    void seekToPosition(int position);

    void refreshPageInfo(MusicsListEntity entity, int totalDuration);

    void refreshPlayProgress(int progress);

    void refreshPlaySecondProgress(int progress);

    void setPlayIng();
    void setPlayPause();
}
