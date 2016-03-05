package com.yzd.android.mcs_phone.interator.impl;

import android.content.Context;

import com.yzd.android.mcs_phone.interator.ISituationInterator;
import com.yzd.android.mcs_phone.udp.InitData.StringMerge;
import com.yzd.android.mcs_phone.udp.Sender;

/**
 * Created by Administrator on 2015/10/14.
 */
public class SituationInteratorImpl implements ISituationInterator {
    @Override
    public void sendOrder(Context context, String order) {
        Sender.getInstances().send(context,order);
    }

    @Override
    public String getSituationOrder(Context context, int situation, int mode) {
        return StringMerge.getInstances().situationControl(context,situation, mode);
    }
}
