package com.yzd.android.mcs_phone.udp;


import android.content.Context;

import com.github.obsessive.library.utils.SPUtils;
import com.yzd.android.mcs_phone.api.Constants;
import com.yzd.android.mcs_phone.utils.BytesUtils;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Sender implements Runnable {

    private String ip;
    private int port;
    private byte[] mSendData;
    private static Sender instances;
    private Context mContext;


    private Sender() {
    }

    public static Sender getInstances() {
        if (null == instances) {
            synchronized (Sender.class) {
                if (null == instances) {
                    instances = new Sender();
                }
            }
        }
        return instances;
    }

    public void send(Context context,String msg){
        mContext = context;
        this.mSendData = BytesUtils.hexStringToBytes(msg);
        new Thread(this).start();
    }

    public synchronized void run() {
        DatagramSocket socket = null;

        try {
            if (socket == null) {
                socket = new DatagramSocket();
            }

            DatagramPacket dp = new DatagramPacket(mSendData,
                    mSendData.length,
                    InetAddress.getByName((String) SPUtils.get(mContext, Constants.IP,Constants.DEFAULT_IP)),
                    (int) SPUtils.get(mContext, Constants.SENDPORT,Constants.DEFAULT_SENDPORT));

            socket.send(dp);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                socket.close();
            }
        }

    }

}
