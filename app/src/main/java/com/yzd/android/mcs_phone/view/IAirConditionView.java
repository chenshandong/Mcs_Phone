package com.yzd.android.mcs_phone.view;

/**
 * Created by Administrator on 2015/10/27.
 */
public interface IAirConditionView {

    void showFanSpeed(int position);

    void showMode(int position);

    void showTemp(int temp);

    void powerOff();

    void powerOn(int temp, int mode, int speed);
}
