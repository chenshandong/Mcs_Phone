package com.yzd.android.mcs_phone.model;

import com.yzd.android.mcs_phone.bean.database.BoardRoom;
import com.yzd.android.mcs_phone.bean.database.BoardRoomEntity;
import com.yzd.android.mcs_phone.bean.database.BoardRoomMachineCode;
import com.yzd.android.mcs_phone.bean.database.LightEntity;
import com.yzd.android.mcs_phone.bean.database.MusicsListEntity;

import java.util.List;

/**
 * Created by Administrator on 2015/10/13.
 */
public interface ISelectDataBase {

    BoardRoom selectAllBoardRoom();

    BoardRoomEntity selectOneRoom(int typeId);

    BoardRoomMachineCode selectAllMachineCode();

    List<MusicsListEntity> selectMusicLists(String key);

    List<LightEntity> selectLight(int boardRoomId);

}
