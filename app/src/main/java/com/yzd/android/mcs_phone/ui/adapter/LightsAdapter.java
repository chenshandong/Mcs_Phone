package com.yzd.android.mcs_phone.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.obsessive.library.listener.ICustomSwitchStateChangeListener;
import com.yzd.android.mcs_phone.R;
import com.yzd.android.mcs_phone.bean.database.LightEntity;
import com.yzd.android.mcs_phone.udp.InitData.StringMerge;
import com.yzd.android.mcs_phone.udp.Sender;
import com.yzd.android.mcs_phone.ui.adapter.viewholder.LightsViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/10/15.
 */
public class LightsAdapter extends RecyclerView.Adapter<LightsViewHolder> implements ICustomSwitchStateChangeListener {

    private List<LightEntity> mLights;
    private Context mContext;
    private ArrayList<Boolean> mLightState = new ArrayList<>();

    public LightsAdapter(Context context, List<LightEntity> lightEntities) {
        mLights = lightEntities;
        mContext = context;

        if (lightEntities != null && lightEntities.size() > 0) {

            for (int i = 0; i < lightEntities.size(); i++) {
                mLightState.add(false);
            }
        }
    }



    @Override
    public LightsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid_lights, null);
        LightsViewHolder lightsViewHolder = new LightsViewHolder(parent.getContext(), view);
        lightsViewHolder.setOnSwitchStateChangeListener(this);
        return lightsViewHolder;
    }

    @Override
    public void onBindViewHolder(LightsViewHolder holder, int position) {
        holder.setLightName(mLights.get(position).getName());

        holder.setOnSwitchStateChangeListener(null);
        holder.setSwitch(mLightState.get(position));
        holder.setOnSwitchStateChangeListener(this);
    }


    @Override
    public int getItemCount() {
        return mLights == null ? 0 : mLights.size();
    }

    @Override
    public void onStateChange(View view, int position, boolean checked) {
        String code = mLights.get(position).getCode();
        String substring = code.substring(code.lastIndexOf("_") + 1);
        String s = StringMerge.getInstances().lightControl(mContext, Integer.parseInt(substring), checked);
        Sender.getInstances().send(mContext, s);
    }

    public void updateLightState(ArrayList<Boolean> lightStates) {
        mLightState.clear();
        mLightState.addAll(lightStates);
        this.notifyDataSetChanged();
    }
}
