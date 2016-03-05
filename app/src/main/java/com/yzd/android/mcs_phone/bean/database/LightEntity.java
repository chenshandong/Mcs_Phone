package com.yzd.android.mcs_phone.bean.database;

import android.os.Parcel;
import android.os.Parcelable;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "LightEntity")
public  class LightEntity extends Model implements Parcelable {
    /**
     * name : 死猴子
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.code);
        dest.writeInt(this.boardRoomId);
    }

    public LightEntity() {
    }

    protected LightEntity(Parcel in) {
        this.name = in.readString();
        this.code = in.readString();
        this.boardRoomId = in.readInt();
    }

    public static final Parcelable.Creator<LightEntity> CREATOR = new Parcelable.Creator<LightEntity>() {
        public LightEntity createFromParcel(Parcel source) {
            return new LightEntity(source);
        }

        public LightEntity[] newArray(int size) {
            return new LightEntity[size];
        }
    };
}