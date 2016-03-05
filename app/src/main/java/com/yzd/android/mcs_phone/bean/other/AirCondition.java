package com.yzd.android.mcs_phone.bean.other;

/**
 * Created by Administrator on 2015/8/13.
 */
public class AirCondition {

    public AirCondition(){
        super();
    }

    public int roomId = 0;

    public int position = 0;

    public int temperature = 16;

    public int fanRate = 0;

    public int mode = 0;

    public int status = 0;

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getFanRate() {
        return fanRate;
    }

    public void setFanRate(int fanRate) {
        this.fanRate = fanRate;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public int isStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
