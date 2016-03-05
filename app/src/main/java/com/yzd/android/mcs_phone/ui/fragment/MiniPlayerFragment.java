package com.yzd.android.mcs_phone.ui.fragment;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yzd.android.mcs_phone.R;
import com.yzd.android.mcs_phone.bean.database.MusicsListEntity;
import com.yzd.android.mcs_phone.services.MusicService;
import com.yzd.android.mcs_phone.ui.activity.MusicPlayActivity;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MiniPlayerFragment extends Fragment implements View.OnClickListener {


    private int mPosition;
    private TextView mMusicName;
    private TextView mSinger;
    private BroadcastReceiver mBroadcast;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        mPosition = bundle.getInt("position");
        MusicsListEntity musicInfo = bundle.getParcelable("musicInfo");

        View view = inflater.inflate(R.layout.fragment_mini_player, container, false);
        mMusicName = (TextView) view.findViewById(R.id.musicName);
        mSinger = (TextView) view.findViewById(R.id.singer);

        if (null != musicInfo) {
            mMusicName.setText(musicInfo.getTitle());
            mSinger.setText(musicInfo.getArtist());
        }
        view.setOnClickListener(this);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mBroadcast = new UpdateInfoReceive();
        IntentFilter intentFilter = new IntentFilter("UPDATE_MINI_PLAYER_INFO");
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(mBroadcast, intentFilter);
    }

    @Override
    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(mBroadcast);
    }

    @Override
    public void onClick(View view) {
        int playPosition = MusicService.getPlayPosition();
        if (playPosition != -1) {
            getActivity().startActivity(new Intent(getActivity(), MusicPlayActivity.class));
        } else {
            Snackbar.make(getActivity().getWindow().getDecorView(), "还没有正在播放的音乐", Snackbar.LENGTH_SHORT).show();
        }
    }

    private class UpdateInfoReceive extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            ArrayList<String> update_mini_player_info = intent.getStringArrayListExtra("UPDATE_MINI_PLAYER_INFO"+mPosition);
            if (null != update_mini_player_info && !update_mini_player_info.isEmpty()) {
                mMusicName.setText(update_mini_player_info.get(0));
                mSinger.setText(update_mini_player_info.get(1));
            }

        }
    }


}
