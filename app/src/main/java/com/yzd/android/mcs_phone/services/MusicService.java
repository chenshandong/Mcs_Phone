package com.yzd.android.mcs_phone.services;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

import com.github.obsessive.library.eventbus.EventCenter;
import com.yzd.android.mcs_phone.api.MusicConstants;
import com.yzd.android.mcs_phone.bean.database.MusicsListEntity;
import com.yzd.android.mcs_phone.services.player.MusicPlayState;
import com.yzd.android.mcs_phone.services.player.MusicPlayer;

import java.util.List;

import de.greenrobot.event.EventBus;

public class MusicService extends Service {

    private static MusicPlayer mPlayer = null;
    private PlayBroadCastReceiver mBroadCastReceiver = null;
    private PhoneCallReceiver mPhoneCallReceiver = null;
    private TelephonyManager mTelephonyManager = null;

    public MusicService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mPlayer = new MusicPlayer(this);

        mBroadCastReceiver = new PlayBroadCastReceiver();

        IntentFilter filter = new IntentFilter();
        filter.addAction(MusicPlayState.ACTION_MUSIC_NEXT);
        filter.addAction(MusicPlayState.ACTION_MUSIC_PAUSE);
        filter.addAction(MusicPlayState.ACTION_MUSIC_PLAY);
        filter.addAction(MusicPlayState.ACTION_MUSIC_PREV);
        filter.addAction(MusicPlayState.ACTION_MUSIC_REPLAY);
        filter.addAction(MusicPlayState.ACTION_MUSIC_STOP);
        filter.addAction(MusicPlayState.ACTION_EXIT);
        filter.addAction(MusicPlayState.ACTION_SEEK_TO);


        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(mBroadCastReceiver, filter);

        mPhoneCallReceiver = new PhoneCallReceiver();

        IntentFilter phoneCallFilter = new IntentFilter();
        phoneCallFilter.addAction(Intent.ACTION_NEW_OUTGOING_CALL);

        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(mPhoneCallReceiver, phoneCallFilter);

        mTelephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        mTelephonyManager.listen(new PhoneStateChangedListener(), PhoneStateListener.LISTEN_CALL_STATE);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mBroadCastReceiver);
        unregisterReceiver(mPhoneCallReceiver);

        mTelephonyManager.listen(null, PhoneStateListener.LISTEN_NONE);
        mPlayer.exit();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    public static void refreshMusicList(List<MusicsListEntity> listData) {
        if (null != listData && !listData.isEmpty()) {
            mPlayer.refreshMusicList(listData);
        }
    }

    public static int getPlayState(){
        if (mPlayer != null) {
            return mPlayer.getPlayState();
        } else {
            return -1;
        }
    }

    public static boolean isPlay(){
        return mPlayer.isPlay();
    }

    public static List<MusicsListEntity> getMusicLists() {
        if (mPlayer != null) {
            return mPlayer.getMusicList();
        } else {
            return null;
        }
    }

    public static void refreshPageInfo() {
        mPlayer.refrePageInfo();
    }

    public static int getPlayPosition() {
        return mPlayer.getPlayPosition();
    }

    private class PlayBroadCastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(MusicPlayState.ACTION_MUSIC_NEXT)) {
                mPlayer.playNext();
            } else if (action.equals(MusicPlayState.ACTION_MUSIC_PAUSE)) {
                mPlayer.pause();
            } else if (action.equals(MusicPlayState.ACTION_MUSIC_PLAY)) {
                int position = intent.getIntExtra("position", 0);
                mPlayer.play(position);
            } else if (action.equals(MusicPlayState.ACTION_MUSIC_PREV)) {
                mPlayer.playPrev();
            } else if (action.equals(MusicPlayState.ACTION_MUSIC_REPLAY)) {
                mPlayer.replay();
            } else if (action.equals(MusicPlayState.ACTION_MUSIC_STOP)) {
                mPlayer.stop();
            } else if (action.equals(MusicPlayState.ACTION_EXIT)) {
                mPlayer.exit();
            } else if (action.equals(MusicPlayState.ACTION_SEEK_TO)) {
                int progress = intent.getIntExtra(MusicConstants.KEY_PLAYER_SEEK_TO_PROGRESS, 0);
                mPlayer.seekTo(progress);
            }
        }

    }

    private class PhoneCallReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (null == intent) {
                return;
            }

            String action = intent.getAction();

            if (action.equals(Intent.ACTION_NEW_OUTGOING_CALL)) {
                EventBus.getDefault().post(new EventCenter(MusicConstants.EVENT_STOP_PLAY_MUSIC));
            }
        }
    }

    private class PhoneStateChangedListener extends PhoneStateListener {
        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            super.onCallStateChanged(state, incomingNumber);
            switch (state) {
                case TelephonyManager.CALL_STATE_OFFHOOK:
                case TelephonyManager.CALL_STATE_RINGING:
                    EventBus.getDefault().post(new EventCenter(MusicConstants.EVENT_STOP_PLAY_MUSIC));

                    break;

                case TelephonyManager.CALL_STATE_IDLE:
                    EventBus.getDefault().post(new EventCenter(MusicConstants.EVENT_START_PLAY_MUSIC));

                    break;
            }
        }
    }
}
