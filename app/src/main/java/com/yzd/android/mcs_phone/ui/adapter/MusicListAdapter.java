package com.yzd.android.mcs_phone.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.obsessive.library.eventbus.EventCenter;
import com.github.obsessive.library.listener.IRecyclerItemClickListener;
import com.github.obsessive.library.utils.SPUtils;
import com.yzd.android.mcs_phone.R;
import com.yzd.android.mcs_phone.api.Constants;
import com.yzd.android.mcs_phone.api.EvenBusConstants;
import com.yzd.android.mcs_phone.api.MusicConstants;
import com.yzd.android.mcs_phone.bean.database.MusicsListEntity;
import com.yzd.android.mcs_phone.model.impl.DeleteDataBaseImpl;
import com.yzd.android.mcs_phone.services.MusicService;
import com.yzd.android.mcs_phone.services.player.MusicPlayState;
import com.yzd.android.mcs_phone.ui.adapter.viewholder.MusicListViewHolder;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;
import me.drakeet.materialdialog.MaterialDialog;

/**
 * Created by Administrator on 2015/10/16.
 */
public class MusicListAdapter extends RecyclerView.Adapter<MusicListViewHolder> implements IRecyclerItemClickListener {


    private ArrayList<MusicsListEntity> musicsListEntities = new ArrayList<>();
    private Context mContext;
    private int mShowIndex = -1;
    private int mPosition;
    private String mMusicKey;
    private MaterialDialog mMaterialDialog;

    public MusicListAdapter(Context context, int position, String musicKey) {
        mContext = context;
        mPosition = position;
        mMusicKey = musicKey;
    }
    @Override
    public MusicListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_music, null);
        MusicListViewHolder musicListViewHolder = new MusicListViewHolder(view);
        musicListViewHolder.setOnRecyclerItemClikListener(this);
        return musicListViewHolder;
    }

    public void setMusicsListEntities(ArrayList<MusicsListEntity> musicsListEntities) {

        if (musicsListEntities != null && musicsListEntities.size() > 0) {
            this.musicsListEntities.clear();
            this.musicsListEntities.addAll(musicsListEntities);
        }
    }

    @Override
    public void onBindViewHolder(MusicListViewHolder holder, int position) {
        holder.setName(musicsListEntities.get(position).getTitle(), musicsListEntities.get(position).getArtist());
        if (mShowIndex == position) {
            holder.setSelectorVisible();
        } else {
            holder.setSelectorInVisible();
        }
    }

    public void setShowIndex(int position) {
        mShowIndex = position;
    }

    @Override
    public int getItemCount() {
        return musicsListEntities.size();
    }

    @Override
    public void itemOnClick(View view, final int position) {

        SPUtils.put(mContext, EvenBusConstants.SAVE_MUSIC_KEY, mMusicKey);

        setShowIndex(position);

        int id = view.getId();
        if (id == R.id.btn_delete) {

                mMaterialDialog = new MaterialDialog(mContext)
                        .setTitle("提示")
                        .setMessage("确定删除这首歌曲?")
                        .setPositiveButton("确定", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                DeleteDataBaseImpl.getInstances().deleteOneMusic(musicsListEntities.get(position).getMusicList(), musicsListEntities.get(position).getUrl());
                                musicsListEntities.remove(position);
                                mMaterialDialog.dismiss();
                            }
                        })
                        .setNegativeButton("取消", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mMaterialDialog.dismiss();
                            }
                        });
            mMaterialDialog.show();

        } else if (id ==R.id.btn_music) {
            EventCenter<String> stringEventCenter = new EventCenter<>(EvenBusConstants.ECEN_MUSIC_KEY, mMusicKey);
            stringEventCenter.setPosition(position);
            EventBus.getDefault().post(stringEventCenter);
            LocalBroadcastManager.getInstance(mContext).sendBroadcast(new Intent(MusicPlayState.ACTION_MUSIC_PLAY).putExtra("position", position));
        }
        this.notifyDataSetChanged();
        MusicService.refreshMusicList(musicsListEntities);

        if (musicsListEntities.size() == 0) {
            EventBus.getDefault().post(new EventCenter<String>(mPosition, "showError"));
        }
    }
}
