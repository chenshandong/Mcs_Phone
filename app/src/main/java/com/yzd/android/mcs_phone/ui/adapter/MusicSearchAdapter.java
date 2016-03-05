package com.yzd.android.mcs_phone.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.github.obsessive.library.listener.IRecyclerViewCheckBoxListener;
import com.yzd.android.mcs_phone.R;
import com.yzd.android.mcs_phone.bean.database.MusicsListEntity;
import com.yzd.android.mcs_phone.ui.adapter.viewholder.MusicSearchViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/10/19.
 */
public class MusicSearchAdapter extends RecyclerView.Adapter<MusicSearchViewHolder> {

    private Context mContext;
    private List<Integer> mCheckPositionLists = new ArrayList<>();
    private List<MusicsListEntity> mMusicAddLists = new ArrayList<>();
    private List<MusicsListEntity> musicsListEntities = new ArrayList<>();

    public MusicSearchAdapter(Context context) {
        mContext = context;
    }

    public List<MusicsListEntity> getMusicsListEntities() {
        return musicsListEntities;
    }

    public List<MusicsListEntity> getAddMusicLists() {
        return mMusicAddLists;
    }

    public void setMusicsListEntities(List<MusicsListEntity> musicsListEntities) {

        if (musicsListEntities != null && !musicsListEntities.isEmpty()) {
            this.musicsListEntities.clear();
            this.musicsListEntities.addAll(musicsListEntities);
        }
    }

    @Override
    public MusicSearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_music_search, null);
        MusicSearchViewHolder musicSearchViewHolder = new MusicSearchViewHolder(view);
        return musicSearchViewHolder;
    }

    @Override
    public void onBindViewHolder(final MusicSearchViewHolder holder, final int position) {
        final MusicsListEntity musicsListEntity = musicsListEntities.get(position);
        holder.setMusicName(musicsListEntity.getTitle());
        holder.setSinger(musicsListEntity.getArtist());
        holder.getCheckBox().setTag(new Integer(position));

        holder.getCheckBox().setOnCheckedChangeListener(null);
        holder.getCheckBox().setChecked(mCheckPositionLists.contains(new Integer(position)) ? true : false);
        holder.getCheckBox().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    if (!mCheckPositionLists.contains(holder.getCheckBox().getTag())) {
                        mCheckPositionLists.add(new Integer(position));
                    }
                    if (!mMusicAddLists.contains(musicsListEntity)) {
                        mMusicAddLists.add(musicsListEntity);
                    }
                } else {
                    if (mCheckPositionLists.contains(holder.getCheckBox().getTag())) {
                        mCheckPositionLists.remove(new Integer(position));
                    }

                    if (mMusicAddLists.contains(musicsListEntity)) {
                        mMusicAddLists.remove(musicsListEntity);
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return musicsListEntities.size();
    }



}
