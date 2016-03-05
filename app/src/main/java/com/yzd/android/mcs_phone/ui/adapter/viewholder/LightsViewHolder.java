package com.yzd.android.mcs_phone.ui.adapter.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.github.obsessive.library.listener.ICustomSwitchStateChangeListener;
import com.rey.material.widget.Switch;
import com.yzd.android.mcs_phone.R;

/**
 * Created by Administrator on 2015/10/15.
 */
public class LightsViewHolder extends RecyclerView.ViewHolder implements Switch.OnCheckedChangeListener {


    private TextView mLightName;
    private Switch mLightSwitch;
    private Context mContext;
    private View mView;

    private ICustomSwitchStateChangeListener mSwitchChangeListener = null;
    public void setOnSwitchStateChangeListener(ICustomSwitchStateChangeListener customSwitchStateChangeListener){
        mSwitchChangeListener = customSwitchStateChangeListener;
    }

    public LightsViewHolder(Context context, View itemView) {
        super(itemView);
        mContext = context;
        mView = itemView;
        mLightName = (TextView) itemView.findViewById(R.id.tv_lightName);
        mLightSwitch = (Switch) itemView.findViewById(R.id.switch_light);
        mLightSwitch.setOnCheckedChangeListener(this);
    }

    public void setLightName(String name) {
        mLightName.setText(name);
    }

    public View getView() {
        return mView;
    }

    @Override
    public void onCheckedChanged(Switch view, boolean checked) {
        mSwitchChangeListener.onStateChange(view, getPosition(), checked);
    }

    public void setSwitch(Boolean b) {
        mLightSwitch.setOnCheckedChangeListener(null);
        mLightSwitch.setChecked(b);
        mLightSwitch.setOnCheckedChangeListener(this);
    }
}
