package com.yzd.android.mcs_phone.ui.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.andexert.library.RippleView;
import com.github.obsessive.library.listener.IRecyclerViewCheckBoxListener;
import com.rey.material.widget.CheckBox;
import com.yzd.android.mcs_phone.R;

/**
 * Created by Administrator on 2015/10/16.
 */
public class MusicSearchViewHolder extends RecyclerView.ViewHolder {


    private TextView mMusicName;
    private TextView mSinger;
    private CheckBox mCheckBox;
    private IRecyclerViewCheckBoxListener mCheckBoxListener;

    public MusicSearchViewHolder(View itemView) {
        super(itemView);
        mMusicName = (TextView) itemView.findViewById(R.id.musicName);
        mSinger = (TextView) itemView.findViewById(R.id.singer);
        mCheckBox = (CheckBox) itemView.findViewById(R.id.checkbox);
    }

    public void setSinger(String singer) {
        mSinger.setText(singer);
    }

    public void setMusicName(String musicName) {
        mMusicName.setText(musicName);
    }

    public CheckBox getCheckBox() {
        return mCheckBox;
    }

}
