package com.yzd.android.mcs_phone.interator.impl;

import android.content.Context;

import com.yzd.android.mcs_phone.api.UdpSend;
import com.yzd.android.mcs_phone.interator.IProjectorInterator;
import com.yzd.android.mcs_phone.udp.InitData.StringMerge;
import com.yzd.android.mcs_phone.udp.Sender;

/**
 * Created by Clearlove on 15/10/30.
 */
public class ProjectorInteratorImpl implements IProjectorInterator {


    @Override
    public void powerOn(Context context, int position) {
        sendOrder(context, position, UdpSend.PROJECTION.OPEN);
    }

    @Override
    public void powerClose(Context context, int position) {
        sendOrder(context, position, UdpSend.PROJECTION.CLOSE);

    }

    @Override
    public void mode(Context context, int position) {
        sendOrder(context, position, UdpSend.PROJECTION.MODE_BUTTON);

    }

    @Override
    public void source(Context context, int position) {
        sendOrder(context, position, UdpSend.PROJECTION.SOURCE);

    }

    @Override
    public void ok(Context context, int position) {
        sendOrder(context, position, UdpSend.PROJECTION.OK_BUTTON);

    }

    @Override
    public void up(Context context, int position) {
        sendOrder(context, position, UdpSend.PROJECTION.UP);

    }

    @Override
    public void down(Context context, int position) {
        sendOrder(context, position, UdpSend.PROJECTION.DOWN);

    }

    @Override
    public void left(Context context, int position) {
        sendOrder(context, position, UdpSend.PROJECTION.LEFT);

    }

    @Override
    public void right(Context context, int position) {
        sendOrder(context, position, UdpSend.PROJECTION.RIGHT);

    }

    @Override
    public void volUp(Context context, int position) {
        sendOrder(context, position, UdpSend.PROJECTION.VOL_PLUS);

    }

    @Override
    public void volDown(Context context, int position) {
        sendOrder(context, position, UdpSend.PROJECTION.VOL_DESC);

    }

    @Override
    public void screenShotUp(Context context, int position) {
        sendOrder(context, position, UdpSend.PROJECTION.UP_BUTTON);

    }

    @Override
    public void screenShotDown(Context context, int position) {
        sendOrder(context, position, UdpSend.PROJECTION.DOWN_BUTTON);

    }

    @Override
    public void screenLongUp(Context context, int position) {
        sendOrder(context, position, UdpSend.PROJECTION.UP_LONG_BUTTON);

    }

    @Override
    public void screenLongDown(Context context, int position) {
        sendOrder(context, position, UdpSend.PROJECTION.DOWN_LONG_BUTTON);

    }

    private void sendOrder(Context context, int position, String order){
        StringBuffer pos = new StringBuffer();
        if (position < 10) {
            pos.append("0");
            pos.append(String.valueOf(position));
        } else {
            pos.append(String.valueOf(position));
        }
        String s = StringMerge.getInstances().infrafedControl(context,
                UdpSend.PROJECTION.PROJECTION,
                pos.toString(),
                order);
        Sender.getInstances().send(context, s);
    }
}
