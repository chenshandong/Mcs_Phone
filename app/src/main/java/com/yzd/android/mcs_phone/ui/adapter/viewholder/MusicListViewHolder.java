package com.yzd.android.mcs_phone.ui.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.andexert.library.RippleView;
import com.github.obsessive.library.listener.IRecyclerItemClickListener;
import com.yzd.android.mcs_phone.R;

/**
 * Created by Administrator on 2015/10/16.
 */
public class MusicListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


    private TextView mMusicName;
    private TextView mSinger;
    private TextView mMusicSelector;

    private IRecyclerItemClickListener mRecyclerItemClickListener = null;
    public void setOnRecyclerItemClikListener(IRecyclerItemClickListener recyclerItemClikListener) {
        mRecyclerItemClickListener = recyclerItemClikListener;
    }

    public MusicListViewHolder(View itemView) {
        super(itemView);
        mMusicName = (TextView) itemView.findViewById(R.id.musicName);
        mSinger = (TextView) itemView.findViewById(R.id.singer);
        mMusicSelector = (TextView) itemView.findViewById(R.id.musicSelector);
        itemView.findViewById(R.id.btn_delete).setOnClickListener(this);
        itemView.findViewById(R.id.btn_music).setOnClickListener(this);
    }

    public void setName(String musicName, String singerName) {
        mMusicName.setText(musicName);
        mSinger.setText(singerName);
    }

    public void setSelectorInVisible() {
        mMusicSelector.setVisibility(View.INVISIBLE);
    }

    public void setSelectorVisible() {
        mMusicSelector.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View view) {
        mRecyclerItemClickListener.itemOnClick(view, getPosition());
    }
}
