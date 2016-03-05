package com.yzd.android.mcs_phone.bean.database;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by Administrator on 2015/10/9.
 */
@Table(name = "SetAirEntity")
public class SetAirEntity extends Model{

    @Column
    private String speed;
    @Column
    private String temp;
    @Column
    private String mode;
    @Column
    private int boardRoomId;
    @Column(name = "BoardRoomEntity")
    private BoardRoomEntity boardRoomEntity;

    public BoardRoomEntity getBoardRoomEntity() {
        return boardRoomEntity;
    }

    public void setBoardRoomEntity(BoardRoomEntity boardRoomEntity) {
        this.boardRoomEntity = boardRoomEntity;
    }


    public int getBoardRoomId() {
        return boardRoomId;
    }

    public void setBoardRoomId(int boardRoomId) {
        this.boardRoomId = boardRoomId;
    }
    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }
}
