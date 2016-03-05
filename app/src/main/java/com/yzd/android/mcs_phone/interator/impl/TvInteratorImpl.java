package com.yzd.android.mcs_phone.interator.impl;

import android.content.Context;

import com.yzd.android.mcs_phone.api.UdpSend;
import com.yzd.android.mcs_phone.interator.ITvInterator;
import com.yzd.android.mcs_phone.udp.InitData.StringMerge;
import com.yzd.android.mcs_phone.udp.Sender;

/**
 * Created by Administrator on 2015/11/6.
 */
public class TvInteratorImpl implements ITvInterator {

    private Context mContext;

    public TvInteratorImpl(Context context) {
        mContext = context;
    }

    @Override
    public void powerOn() {
        send(UdpSend.TV.OPEN);
    }

    @Override
    public void powerOff() {
        send(UdpSend.TV.CLOSE);
    }

    @Override
    public void volUp() {
        send(UdpSend.TV.VOL_PLUS);
    }

    @Override
    public void volDown() {
        send(UdpSend.TV.VOL_DESC);
    }

    @Override
    public void channelUp() {
        send(UdpSend.TV.CHANNEL_PLUS);
    }

    @Override
    public void channelDown() {
        send(UdpSend.TV.CHANNEL_DESC);
    }

    @Override
    public void keyNum(int num) {
        send(Integer.toHexString(num + 15));
    }

    @Override
    public void keyBoardUp() {
        send(UdpSend.TV.KEY_UP);
    }

    @Override
    public void keyBoardDown() {
        send(UdpSend.TV.KEY_DOWN);
    }

    @Override
    public void keyBoardRight() {
        send(UdpSend.TV.KEY_RIGHT);
    }

    @Override
    public void keyBoardLeft() {
        send(UdpSend.TV.KEY_LEFT);
    }

    @Override
    public void keyBoardOk() {
        send(UdpSend.TV.OK_BUTTON);
    }

    @Override
    public void keySource() {
        send(UdpSend.TV.SOURCE);
    }

    @Override
    public void keyBack() {
        send(UdpSend.TV.BACK);
    }

    @Override
    public void keyDelete() {
        send(UdpSend.TV.BUTTON);
    }

    @Override
    public void send(String order) {
        String s = StringMerge.getInstances().infrafedControl(mContext, UdpSend.TV.TV, "01", order);
        Sender.getInstances().send(mContext, s);
    }
}
