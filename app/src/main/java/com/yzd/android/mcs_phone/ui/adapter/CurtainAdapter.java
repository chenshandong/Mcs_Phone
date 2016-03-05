package com.yzd.android.mcs_phone.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.obsessive.library.listener.IRecyclerItemClickListener;
import com.yzd.android.mcs_phone.R;
import com.yzd.android.mcs_phone.api.UdpSend;
import com.yzd.android.mcs_phone.bean.database.CurtainEntity;
import com.yzd.android.mcs_phone.udp.InitData.StringMerge;
import com.yzd.android.mcs_phone.udp.Sender;
import com.yzd.android.mcs_phone.ui.adapter.viewholder.CurtainViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2015/10/15.
 */
public class CurtainAdapter extends RecyclerView.Adapter<CurtainViewHolder> implements IRecyclerItemClickListener {

    private List<CurtainEntity> mCurtainEntities;
    private Context mContext;

    public CurtainAdapter(Context context, List<CurtainEntity> curtainEntities) {
        mContext = context;
        mCurtainEntities = curtainEntities;
    }

    @Override
    public CurtainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_curtain, null);
        CurtainViewHolder curtainViewHolder = new CurtainViewHolder(parent.getContext(), view);
        curtainViewHolder.setOnCurtainClickListener(this);
        return curtainViewHolder;
    }

    @Override
    public void onBindViewHolder(CurtainViewHolder holder, int position) {
        holder.setCurtainName(mCurtainEntities.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return mCurtainEntities == null ? 0 : mCurtainEntities.size();
    }

    @Override
    public void itemOnClick(View view, int position) {
        String s = null;
        switch (view.getId()) {
            case R.id.btn_open:
                s = StringMerge.getInstances().curtainControl(mContext, String.valueOf(position), UdpSend.CURTAIN.OPEN);
                break;
            case R.id.btn_pause:
                s = StringMerge.getInstances().curtainControl(mContext, String.valueOf(position), UdpSend.CURTAIN.PAUSE);

                break;
            case R.id.btn_close:
                s = StringMerge.getInstances().curtainControl(mContext, String.valueOf(position), UdpSend.CURTAIN.CLOSE);

                break;

        }

        if (null != s) {

            Sender.getInstances().send(mContext, s);
        }
    }
}
