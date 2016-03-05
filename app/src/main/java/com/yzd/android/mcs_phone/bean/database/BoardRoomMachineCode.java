package com.yzd.android.mcs_phone.bean.database;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.List;

/**
 * Created by Administrator on 2015/8/27.
 */
@Table(name = "BoardRoomMachineCode")
public class BoardRoomMachineCode extends Model{

    /**
     * boardRoom : [{"buildName":"A 栋","floorName":"F2","macCode":"800000071","roomId":32,"boardRoomName":"鑫哥专用","typeId":13,"ip":"192.168.1.17"},{"buildName":"A 栋","floorName":"F3","macCode":"1010101010","roomId":33,"boardRoomName":"二货专用","typeId":14,"ip":"192.168.1.111"}]
     */
    @Column(name = "typeId", unique = true, onUniqueConflict = Column.ConflictAction.REPLACE)
    private int num = 1;
    private List<MachineCode> boardRoom;

    public List<MachineCode> boardRooms() {
        return getMany(MachineCode.class, "BoardRoomMachineCode");
    }

    public void setMachineCode(List<MachineCode> machineCode) {
        this.boardRoom = machineCode;
    }

    public List<MachineCode> getMachineCode() {
        return boardRoom;
    }


}
