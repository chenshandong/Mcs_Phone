package com.yzd.android.mcs_phone.bean.database;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "CurtainEntity")
public  class CurtainEntity extends Model{
    /**
     * name : 大窗帘
     * code : 13_1
     */

    @Column
    private String name;
    // 这样可以避免重复
    @Column(name = "code", unique = true, onUniqueConflict = Column.ConflictAction.REPLACE)
    private String code;
    @Column
    private int boardRoomId;

    public BoardRoomEntity getBoardRoomEntity() {
        return boardRoomEntity;
    }

    public void setBoardRoomEntity(BoardRoomEntity boardRoomEntity) {
        this.boardRoomEntity = boardRoomEntity;
    }

    @Column(name = "BoardRoomEntity")
    private BoardRoomEntity boardRoomEntity;

    public int getBoardRoomId() {
        return boardRoomId;
    }

    public void setBoardRoomId(int boardRoomId) {
        this.boardRoomId = boardRoomId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }
}