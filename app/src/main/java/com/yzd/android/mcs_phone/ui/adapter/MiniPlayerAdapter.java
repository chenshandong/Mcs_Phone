package com.yzd.android.mcs_phone.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.LocalBroadcastManager;

import com.yzd.android.mcs_phone.bean.database.MusicsListEntity;
import com.yzd.android.mcs_phone.ui.fragment.MiniPlayerFragment;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/11/11.
 */
public class MiniPlayerAdapter extends FragmentPagerAdapter {


    private ArrayList<MusicsListEntity> mMusicsListEntities;

    public MiniPlayerAdapter(FragmentManager fm, ArrayList<MusicsListEntity> musicsListEntities) {
        super(fm);
        mMusicsListEntities = musicsListEntities;
    }

    @Override
    public Fragment getItem(int position) {
        MiniPlayerFragment miniPlayerFragment = new MiniPlayerFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        if (null != mMusicsListEntities && !mMusicsListEntities.isEmpty()) {
            bundle.putParcelable("musicInfo", mMusicsListEntities.get(position));
        }
        miniPlayerFragment.setArguments(bundle);
        return miniPlayerFragment;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public int getCount() {
        return mMusicsListEntities == null ? 0 : mMusicsListEntities.size();
    }

    public void updateMusicList(ArrayList<MusicsListEntity> musicsListEntities) {
        mMusicsListEntities.clear();
        mMusicsListEntities.addAll(musicsListEntities);
        notifyDataSetChanged();
    }

    public void sendBroadcast(Context context) {
        if (!mMusicsListEntities.isEmpty()) {
            Intent intent = new Intent("UPDATE_MINI_PLAYER_INFO");
            for (int i = 0; i < mMusicsListEntities.size(); i++) {
                ArrayList<String> info = new ArrayList<>();
                info.add(mMusicsListEntities.get(i).getTitle());
                info.add(mMusicsListEntities.get(i).getArtist());
                intent.putStringArrayListExtra("UPDATE_MINI_PLAYER_INFO"+i,info );
            }
            LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
        }
    }
}
