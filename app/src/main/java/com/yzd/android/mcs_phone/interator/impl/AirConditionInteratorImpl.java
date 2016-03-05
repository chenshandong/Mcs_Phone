package com.yzd.android.mcs_phone.interator.impl;

import android.content.Context;

import com.yzd.android.mcs_phone.api.UdpSend;
import com.yzd.android.mcs_phone.bean.other.AirCondition;
import com.yzd.android.mcs_phone.interator.IAirConditionInterator;
import com.yzd.android.mcs_phone.udp.InitData.StringMerge;
import com.yzd.android.mcs_phone.udp.Sender;

/**
 * Created by Clearlove on 15/10/30.
 */
public class AirConditionInteratorImpl implements IAirConditionInterator {

    private Context mContext;
    private String  mPosition;

    public AirConditionInteratorImpl(Context context) {
        mContext = context;
    }




    public void sendOneOrder(AirCondition airCondition) {

        String s = StringMerge.getInstances().airConditionControl(mContext, UdpSend.AIRCONDITION.AIRCONDITION, mPosition, airCondition);
        Sender.getInstances().send(mContext, s);
    }

    public void sendAllOrder(AirCondition airCondition, int size) {
        for (int k = 0; k < size; k++) {
            String position = "0" + String.valueOf(k + 1);
            String s = StringMerge.getInstances().airConditionControl(mContext, UdpSend.AIRCONDITION.AIRCONDITION, position, airCondition);
            Sender.getInstances().send(mContext, s);
        }
    }

    public String getPosition() {
        return mPosition;
    }

    public void setPosition(int position) {
        this.mPosition = "0" + String.valueOf(position);

    }
}
