
package com.yzd.android.mcs_phone.services.player;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;

import com.github.obsessive.library.utils.TLog;
import com.yzd.android.mcs_phone.api.MusicConstants;
import com.yzd.android.mcs_phone.bean.database.MusicsListEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MusicPlayer implements OnCompletionListener, OnErrorListener, OnBufferingUpdateListener, OnPreparedListener {

    private final static String TAG = MusicPlayer.class.getSimpleName();

    private final static long SLEEP_TIME = 1000;

    private MediaPlayer mMediaPlayer;

    private List<MusicsListEntity> mMusicList;

    private int mCurPlayIndex;

    private int mPlayState;

    private int mPLayMode;

    private Random mRandom;

    private Context mContext;

    public MusicPlayer(Context context) {
        initParameter(context);
    }

    private void initParameter(Context context) {
        mContext = context;

        mMediaPlayer = new MediaPlayer();

        mMediaPlayer.setOnErrorListener(this);
        mMediaPlayer.setOnPreparedListener(this);
        mMediaPlayer.setOnCompletionListener(this);
        mMediaPlayer.setOnBufferingUpdateListener(this);

        mMusicList = new ArrayList<MusicsListEntity>();

        mCurPlayIndex = -1;
        mPlayState = MusicPlayState.MPS_LIST_EMPTY;
        mPLayMode = MusicPlayMode.MPM_LIST_LOOP_PLAY;

        mRandom = new Random();
        mRandom.setSeed(System.currentTimeMillis());
    }

    public void exit() {
        mMediaPlayer.reset();
        mMediaPlayer.release();
        mMusicList.clear();
        mCurPlayIndex = -1;
        mPlayState = MusicPlayState.MPS_LIST_EMPTY;
    }

    public void refreshMusicList(List<MusicsListEntity> entity) {
        if (entity == null) {
            return;
        }

        mMusicList.clear();
        mMusicList.addAll(entity);

        if (mMusicList.size() == 0) {
            mPlayState = MusicPlayState.MPS_LIST_EMPTY;
            mCurPlayIndex = -1;
            return;
        }

        mPlayState = MusicPlayState.MPS_LIST_FULL;
    }

    public List<MusicsListEntity> getMusicList() {
        return mMusicList;
    }

    public int getPlayPosition() {
        return mCurPlayIndex;
    }

    public int getMusicListCount() {
        return null == mMusicList || mMusicList.isEmpty() ? 0 : mMusicList.size();
    }

    public int getPlayState() {
        return mPlayState;
    }

    public void play() {
        preparedMusic(0);
    }

    public void play(int position) {
        mCurPlayIndex = position;
        preparedMusic(position);
    }

    public void replay() {
        if (mPlayState == MusicPlayState.MPS_LIST_EMPTY) {
            return;
        }

        mMediaPlayer.start();
        mPlayState = MusicPlayState.MPS_PLAYING;
    }

    public void pause() {
        if (mPlayState != MusicPlayState.MPS_PLAYING) {
            return;
        }

        mMediaPlayer.pause();
        mPlayState = MusicPlayState.MPS_PAUSE;
    }

    public void stop() {
        if (mPlayState != MusicPlayState.MPS_PLAYING && mPlayState != MusicPlayState.MPS_PAUSE) {
            return;
        }

        mMediaPlayer.stop();
        mPlayState = MusicPlayState.MPS_STOP;
    }

    public void playNext() {
        mCurPlayIndex++;
        mCurPlayIndex = reviceIndex(mCurPlayIndex);

        preparedMusic(mCurPlayIndex);
    }

    public void playPrev() {
        mCurPlayIndex--;
        mCurPlayIndex = reviceIndex(mCurPlayIndex);

        preparedMusic(mCurPlayIndex);
    }

    public void seekTo(int rate) {
        if (mPlayState == MusicPlayState.MPS_LIST_EMPTY) {
            return;
        }

        int r = reviceSeekValue(rate);
        int time = mMediaPlayer.getDuration();
        int curTime = (int) ((float) r / 100 * time);

        mMediaPlayer.seekTo(curTime);
    }

    public int getCurPosition() {
        if (mPlayState == MusicPlayState.MPS_PLAYING || mPlayState == MusicPlayState.MPS_PAUSE) {
            return mMediaPlayer.getCurrentPosition();
        }

        return 0;
    }

    public int getDuration() {
        if (mPlayState == MusicPlayState.MPS_LIST_EMPTY) {
            return 0;
        }

        return mMediaPlayer.getDuration();
    }

    public void setPlayMode(int mode) {
        switch (mode) {
            case MusicPlayMode.MPM_SINGLE_LOOP_PLAY:
            case MusicPlayMode.MPM_ORDER_PLAY:
            case MusicPlayMode.MPM_LIST_LOOP_PLAY:
            case MusicPlayMode.MPM_RANDOM_PLAY:
                mPLayMode = mode;
                break;
        }
    }

    public int getPlayMode() {
        return mPLayMode;
    }

    private int reviceIndex(int index) {
        if (index < 0) {
            index = mMusicList.size() - 1;
        }

        if (index >= mMusicList.size()) {
            index = 0;
        }

        return index;
    }

    private int reviceSeekValue(int value) {
        if (value < 0) {
            value = 0;
        }

        if (value > 100) {
            value = 100;
        }

        return value;
    }

    private int getRandomIndex() {
        int size = mMusicList.size();
        if (size == 0) {
            return -1;
        }
        return Math.abs(mRandom.nextInt() % size);
    }

    private void preparedMusic(int index) {
        if (mPLayMode == MusicPlayState.MPS_LIST_EMPTY || index < 0 || index >= getMusicListCount()) {
            return;
        }

        mCurPlayIndex = index;

        mMediaPlayer.reset();
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        String dataSource = mMusicList.get(mCurPlayIndex).getUrl();
        try {
            mMediaPlayer.setDataSource(dataSource);
            mMediaPlayer.prepareAsync();
            mPlayState = MusicPlayState.MPS_PREPARED;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        TLog.i(TAG, "mPLayMode = " + mPLayMode);
        switch (mPLayMode) {
            case MusicPlayMode.MPM_SINGLE_LOOP_PLAY:
                play(mCurPlayIndex);
                break;
            case MusicPlayMode.MPM_ORDER_PLAY:
                if (mCurPlayIndex != mMusicList.size() - 1) {
                    playNext();
                } else {
                    stop();
                }
                break;
            case MusicPlayMode.MPM_LIST_LOOP_PLAY:
                if (mCurPlayIndex != mMusicList.size() - 1) {
                    playNext();
                } else {
                    play();
                }
                break;
            case MusicPlayMode.MPM_RANDOM_PLAY:
                int index = getRandomIndex();
                if (index != -1) {
                    mCurPlayIndex = index;
                } else {
                    mCurPlayIndex++;
                }
                mCurPlayIndex = reviceIndex(mCurPlayIndex);

                play(mCurPlayIndex);
                break;
            default:
                break;
        }

    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        TLog.e(TAG, "MusicPlayer		onError!!!\n");
        return false;
    }

    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {
        TLog.d(TAG, "second percent --> " + percent);
        if (percent < 100) {
            Intent intent = new Intent();
            intent.setAction(MusicConstants.ACTION_MUSIC_SECOND_PROGRESS_BROADCAST);
            intent.putExtra(MusicConstants.KEY_MUSIC_SECOND_PROGRESS, percent);
            mContext.sendBroadcast(intent);
        }
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mMediaPlayer.start();
        mPlayState = MusicPlayState.MPS_PLAYING;
        sendPlayBundle();
        sendPlayCurrentPosition();
    }

    public void refrePageInfo(){
        sendPlayBundle();
    }

    private void sendPlayBundle() {
        Intent intent = new Intent(MusicConstants.ACTION_MUSIC_BUNDLE_BROADCAST);
        Bundle extras = new Bundle();
        extras.putInt(MusicConstants.KEY_MUSIC_TOTAL_DURATION, getDuration());
        extras.putParcelable(MusicConstants.KEY_MUSIC_PARCELABLE_DATA, mMusicList.get(mCurPlayIndex));
        intent.putExtras(extras);
        LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);
    }

    public boolean isPlay() {
        return mMediaPlayer.isPlaying();
    }

    private void sendPlayCurrentPosition() {
        new Thread() {
            public void run() {
                Intent intent = new Intent(MusicConstants.ACTION_MUSIC_CURRENT_PROGRESS_BROADCAST);
                while (mMediaPlayer.isPlaying()) {
                    intent.putExtra(MusicConstants.KEY_MUSIC_CURRENT_DUTATION, getCurPosition());
                    LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);
                    try {
                        sleep(SLEEP_TIME);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
}
