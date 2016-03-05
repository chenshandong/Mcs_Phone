package com.yzd.android.mcs_phone.presenter.impl;

import android.content.Context;

import com.yzd.android.mcs_phone.udp.InitData.StringMerge;
import com.yzd.android.mcs_phone.udp.Sender;

/**
 * Created by Clearlove on 15/11/4.
 */
public class UdpSendOrder {

    private static UdpSendOrder instances;
    private UdpSendOrder() {}
    public static UdpSendOrder getInstances() {
        if (null == instances) {
            synchronized (UdpSendOrder.class) {
                if (null == instances) {
                    instances = new UdpSendOrder();
                }
            }
        }
        return instances;
    }

    public void getAllEquipStatus(Context context) {
        String allEquipMentStatus = StringMerge.getInstances().getAllEquipMentStatus(context);
        Sender.getInstances().send(context, allEquipMentStatus);

    }
}
