package com.yzd.android.mcs_phone.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.github.obsessive.library.eventbus.EventCenter;
import com.yzd.android.mcs_phone.api.Constants;
import com.yzd.android.mcs_phone.api.EvenBusConstants;
import com.yzd.android.mcs_phone.api.UdpSend;
import com.yzd.android.mcs_phone.utils.BytesUtils;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import de.greenrobot.event.EventBus;

public class ReceiverService extends Service {

    private byte[] data = new byte[1024];
    private ReceiveTask mReceiveTask;// 接收的线程
    private WifiManager.MulticastLock lock;
    private boolean isRunning = true;

    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }


    public class MyBinder extends Binder{

        public ReceiverService getReceiveService(){
            WifiManager manager = (WifiManager) ReceiverService.this.getSystemService(Context.WIFI_SERVICE);
            if (lock == null) {
                lock = manager.createMulticastLock("myWifi");
            }
            return ReceiverService.this;
        }
    }

    public void startTask(){
        if (mReceiveTask == null) {
            mReceiveTask = new ReceiveTask();
            isRunning = true;
            mReceiveTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
    }

    public void stopTask(){
        isRunning = false;
        mReceiveTask = null;
    }


    class ReceiveTask extends AsyncTask<String, Byte, Void>{

        @Override
        protected Void doInBackground(String... params) {

            Log.d("------------", "doInBackground");

            while(isRunning){
                lock.acquire();
                DatagramSocket socket = null;
                try {
                    socket = new DatagramSocket(Constants.DEFAULT_RECEIVEPORT);
                    DatagramPacket dp = new DatagramPacket(data , data.length);
                    socket.receive(dp);
                    String s = BytesUtils.bytesToHexString(data);
                    String orderCode = s.substring(51, 56).replaceAll(" ", "");
                    if (orderCode.equals(UdpSend.GET_SITUATION_ORDER_CODE)) {
                        EventBus.getDefault().post(new EventCenter<String>(EvenBusConstants.EVEN_UDP_RECEIVE_SITUATION, s));
                    } else if (orderCode.equals(UdpSend.GET_EQUIPMENT_STATUS_ORDER_CODE)) {
                        EventBus.getDefault().post(new EventCenter<String>(EvenBusConstants.EVEN_UDP_RECEIVE_ALL_EQUIP_STATE, s));
                    }
                } catch (SocketException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    lock.release();
                    if (socket != null) {
                        socket.close();
                    }
                }
            }
            return null;
        }

    }

}
