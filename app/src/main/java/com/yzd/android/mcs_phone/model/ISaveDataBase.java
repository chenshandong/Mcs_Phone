package com.yzd.android.mcs_phone.model;

import com.yzd.android.mcs_phone.bean.database.BoardRoom;
import com.yzd.android.mcs_phone.bean.database.BoardRoomMachineCode;
import com.yzd.android.mcs_phone.bean.database.MusicsListEntity;

import java.util.List;

/**
 * Created by Administrator on 2015/10/13.
 */
public interface ISaveDataBase {

    boolean saveBoardRoom(BoardRoom boardRoom);

    boolean saveMachineCode(BoardRoomMachineCode boardRoomMachineCode);

    boolean saveMusicList(List<MusicsListEntity> musicsListEntities, String key);
}
