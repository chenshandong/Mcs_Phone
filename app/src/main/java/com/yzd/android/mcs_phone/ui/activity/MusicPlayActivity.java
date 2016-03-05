package com.yzd.android.mcs_phone.ui.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.github.obsessive.library.eventbus.EventCenter;
import com.github.obsessive.library.netstatus.NetUtils;
import com.github.obsessive.library.utils.CommonUtils;
import com.github.obsessive.library.widgets.PlayerDiscView;
import com.yzd.android.mcs_phone.R;
import com.yzd.android.mcs_phone.api.MusicConstants;
import com.yzd.android.mcs_phone.base.activity.BaseActivity;
import com.yzd.android.mcs_phone.bean.database.MusicsListEntity;
import com.yzd.android.mcs_phone.presenter.impl.MusicPresenterImpl;
import com.yzd.android.mcs_phone.services.MusicService;
import com.yzd.android.mcs_phone.services.player.MusicPlayState;
import com.yzd.android.mcs_phone.view.IMusicView;

import java.util.List;

import butterknife.InjectView;

public class MusicPlayActivity extends BaseActivity implements IMusicView {

    @InjectView(R.id.player_disc_view)
    PlayerDiscView mPlayerDiscView;
    @InjectView(R.id.musics_player_play_ctrl_btn)
    ImageButton mPlayerCtrlBtn;
    @InjectView(R.id.musics_player_play_next_btn)
    ImageButton mPlayerNextBtn;
    @InjectView(R.id.musics_player_play_prev_btn)
    ImageButton mPlayerPrevBtn;
    @InjectView(R.id.musics_player_name)
    TextView mTitle;
    @InjectView(R.id.musics_player_songer_name)
    TextView mSonger;
    @InjectView(R.id.musics_player_seekbar)
    SeekBar mPlayerSeekBar;
    @InjectView(R.id.musics_player_total_time)
    TextView mTotalTime;
    @InjectView(R.id.musics_player_current_time)
    TextView mCurrentTime;
    @InjectView(R.id.player_disc_image)
    ImageView mBackgroundImage;

    private PlayBundleBroadCast mBundleBroadCast;
    private PlayPositionBroadCast mPositionBroadCast;
    private boolean isPlaying = false;
    private MusicPresenterImpl mMusicsPresenter;
    private List<MusicsListEntity> musicLists;
    private int playPosition;

    @Override
    protected boolean isApplyKitKatTranslucency() {
        return true;
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_music_play;
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
        init();
        mMusicsPresenter = new MusicPresenterImpl(this, this);
        setOnListener();

    }

    private void setOnListener() {
        mPlayerCtrlBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPlaying) {
                    mMusicsPresenter.onPausePlay();
                } else {
                    mMusicsPresenter.onRePlay();
                }
            }
        });

        mPlayerNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMusicsPresenter.onNextClick();
            }
        });

        mPlayerPrevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMusicsPresenter.onPrevClick();
            }
        });
    }

    private void init() {
        int playState = MusicService.getPlayState();
        musicLists = MusicService.getMusicLists();
        playPosition = MusicService.getPlayPosition();
        if (MusicPlayState.MPS_PAUSE == playState || MusicPlayState.MPS_STOP == playState) {
            mPlayerDiscView.pause();
            isPlaying = false;
            mPlayerCtrlBtn.setBackground(getResources().getDrawable(R.mipmap.music_pause));
        } else if (MusicPlayState.MPS_PLAYING == playState){
            isPlaying = true;
            mPlayerCtrlBtn.setBackground(getResources().getDrawable(R.mipmap.music_play));
            mPlayerDiscView.startPlay();
        }

        setTextName(musicLists.get(playPosition));
    }

    @Override
    protected void onResume() {
        super.onResume();
        mBundleBroadCast = new PlayBundleBroadCast();
        IntentFilter bundleFilter = new IntentFilter();
        bundleFilter.addAction(MusicConstants.ACTION_MUSIC_BUNDLE_BROADCAST);

        LocalBroadcastManager.getInstance(this).registerReceiver(mBundleBroadCast, bundleFilter);

        mPositionBroadCast = new PlayPositionBroadCast();
        IntentFilter posFilter = new IntentFilter();
        posFilter.addAction(MusicConstants.ACTION_MUSIC_CURRENT_PROGRESS_BROADCAST);

        LocalBroadcastManager.getInstance(this).registerReceiver(mPositionBroadCast, posFilter);

        startService(new Intent(mContext, MusicService.class));

        MusicService.refreshPageInfo();// 主动跟新界面

    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mBundleBroadCast);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mPositionBroadCast);
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
    public void refreshMusicsList() {

    }

    @Override
    public void addMoreMusicsList() {

    }

    @Override
    public void rePlayMusic() {
        isPlaying = true;
        mPlayerDiscView.rePlay();
        mPlayerCtrlBtn.setBackground(getResources().getDrawable(R.mipmap.music_play));
        LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(MusicPlayState.ACTION_MUSIC_REPLAY));
    }

    @Override
    public void startPlayMusic() {
        isPlaying = true;
        mPlayerCtrlBtn.setBackground(getResources().getDrawable(R.mipmap.music_play));
        LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(MusicPlayState.ACTION_MUSIC_PLAY));
    }

    @Override
    public void stopPlayMusic() {
        isPlaying = false;
        mPlayerDiscView.pause();
        mPlayerCtrlBtn.setBackground(getResources().getDrawable(R.mipmap.music_pause));
        LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(MusicPlayState.ACTION_MUSIC_STOP));
    }

    @Override
    public void pausePlayMusic() {
        isPlaying = false;
        mPlayerDiscView.pause();
        mPlayerCtrlBtn.setBackground(getResources().getDrawable(R.mipmap.music_pause));
        LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(MusicPlayState.ACTION_MUSIC_PAUSE));
    }

    @Override
    public void playNextMusic() {
        isPlaying = true;
        mPlayerDiscView.next();
        mPlayerCtrlBtn.setBackground(getResources().getDrawable(R.mipmap.music_pause));
        LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(MusicPlayState.ACTION_MUSIC_NEXT));
    }

    @Override
    public void playPrevMusic() {
        isPlaying = true;
        mPlayerDiscView.next();
        mPlayerCtrlBtn.setBackground(getResources().getDrawable(R.mipmap.music_pause));
        LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(MusicPlayState.ACTION_MUSIC_PREV));
    }

    @Override
    public void seekToPosition(int position) {
        LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(MusicPlayState.ACTION_SEEK_TO).putExtra(MusicConstants.KEY_PLAYER_SEEK_TO_PROGRESS, position));
    }

    @Override
    public void refreshPageInfo(MusicsListEntity entity, int totalDuration) {
        mPlayerDiscView.startPlay();
        mPlayerCtrlBtn.setBackground(getResources().getDrawable(R.mipmap.music_play));

        if (null != entity) {
            setTextName(entity);
        }

        if (totalDuration > 0) {
            mPlayerSeekBar.setMax(totalDuration);
        }

        String imageUrl = entity.getPicture();
        if (!CommonUtils.isEmpty(imageUrl)) {
            mPlayerDiscView.loadAlbumCover(imageUrl);
        }

        String totalTime = CommonUtils.convertTime(totalDuration);
        if (null != totalTime && !TextUtils.isEmpty(totalTime)) {
            mTotalTime.setText(totalTime);
        }
    }

    private void setTextName(MusicsListEntity entity) {
        mTitle.setText(entity.getTitle());
        StringBuilder sb = new StringBuilder();
        sb.append("--\t");
        sb.append(entity.getArtist());
        sb.append("\t--");
        mSonger.setText(sb.toString().trim());
    }

    @Override
    public void refreshPlayProgress(int progress) {
        mPlayerSeekBar.setProgress(progress);
        String currentTime = CommonUtils.convertTime(progress);
        if (null != currentTime && !TextUtils.isEmpty(currentTime)) {
            mCurrentTime.setText(currentTime);
        }
    }

    @Override
    public void refreshPlaySecondProgress(int progress) {

    }

    @Override
    public void setPlayIng() {
        mPlayerCtrlBtn.setBackground(getResources().getDrawable(R.mipmap.music_play));
    }

    @Override
    public void setPlayPause() {
        mPlayerCtrlBtn.setBackground(getResources().getDrawable(R.mipmap.music_pause));
    }

    private class PlayPositionBroadCast extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (null != action && !TextUtils.isEmpty(action)) {
                if (action.equals(MusicConstants.ACTION_MUSIC_CURRENT_PROGRESS_BROADCAST)) {
                    Bundle extras = intent.getExtras();
                    if (null != extras) {
                        int progress = extras.getInt(MusicConstants.KEY_MUSIC_CURRENT_DUTATION);
                        mMusicsPresenter.refreshProgress(progress);
                    }
                }
            }

        }
    }

    private class PlayBundleBroadCast extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (null != action && !TextUtils.isEmpty(action)) {
                if (action.equals(MusicConstants.ACTION_MUSIC_BUNDLE_BROADCAST)) {
                    Bundle extras = intent.getExtras();
                    if (null != extras) {
                        MusicsListEntity entity = extras.getParcelable(MusicConstants.KEY_MUSIC_PARCELABLE_DATA);
                        int totalDuration = extras.getInt(MusicConstants.KEY_MUSIC_TOTAL_DURATION);

                        mMusicsPresenter.refreshPageInfo(entity, totalDuration);
                    }
                }
            }
        }

    }

}
