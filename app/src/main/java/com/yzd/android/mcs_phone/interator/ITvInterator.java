package com.yzd.android.mcs_phone.interator;

/**
 * Created by Administrator on 2015/11/6.
 */
public interface ITvInterator {

    void powerOn();
    void powerOff();
    void volUp();
    void volDown();
    void channelUp();
    void channelDown();
    void keyNum(int num);
    void keyBoardUp();
    void keyBoardDown();
    void keyBoardRight();
    void keyBoardLeft();
    void keyBoardOk();
    void keySource();
    void keyBack();
    void keyDelete();

    void send(String order);
}
