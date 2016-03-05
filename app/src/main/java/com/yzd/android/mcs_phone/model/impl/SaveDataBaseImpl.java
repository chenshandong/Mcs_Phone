package com.yzd.android.mcs_phone.model.impl;

import com.activeandroid.ActiveAndroid;
import com.yzd.android.mcs_phone.bean.database.AirEntity;
import com.yzd.android.mcs_phone.bean.database.AudioEntity;
import com.yzd.android.mcs_phone.bean.database.BoardRoom;
import com.yzd.android.mcs_phone.bean.database.BoardRoomEntity;
import com.yzd.android.mcs_phone.bean.database.BoardRoomMachineCode;
import com.yzd.android.mcs_phone.bean.database.CurtainEntity;
import com.yzd.android.mcs_phone.bean.database.LightEntity;
import com.yzd.android.mcs_phone.bean.database.MachineCode;
import com.yzd.android.mcs_phone.bean.database.ModelEntity;
import com.yzd.android.mcs_phone.bean.database.MusicsListEntity;
import com.yzd.android.mcs_phone.bean.database.ProjectorEntity;
import com.yzd.android.mcs_phone.bean.database.SetAirEntity;
import com.yzd.android.mcs_phone.bean.database.TvEntity;
import com.yzd.android.mcs_phone.model.ISaveDataBase;

import java.util.List;

/**
 * Created by Administrator on 2015/10/13.
 */
public class SaveDataBaseImpl implements ISaveDataBase {

    private static SaveDataBaseImpl instances;
    private SaveDataBaseImpl(){}

    public static SaveDataBaseImpl getInstances() {

        if (instances == null) {
            synchronized (SaveDataBaseImpl.class) {
                if (null == instances) {
                    instances = new SaveDataBaseImpl();
                }
            }
        }

        return  instances;
    }


    @Override
    public boolean saveBoardRoom(BoardRoom boardRoom) {

        if (boardRoom == null) {
            return false;
        }

        ActiveAndroid.beginTransaction();
        try {

            boardRoom.save();
            List<BoardRoomEntity> boardRooms = boardRoom.getBoardRoom();

            if (boardRooms ==null) {
                return false;
            }

            int size = boardRooms.size();
            for (int i = 0; i < size; i++) {

                boardRooms.get(i).setBoardRoom(boardRoom);
                boardRooms.get(i).save();

                List<LightEntity> light = boardRooms.get(i).getLight();
                if (light != null) {
                    int lightSize = light.size();
                    for (int j = 0; j < lightSize; j++) {
                        light.get(j).setBoardRoomEntity(boardRooms.get(i));
                        light.get(j).setBoardRoomId(boardRooms.get(i).getTypeId());
                        light.get(j).save();
                    }
                }

                List<ProjectorEntity> projectorEntities = boardRooms.get(i).getProjector();
                if (projectorEntities != null) {
                    int projectSize = projectorEntities.size();
                    for (int j = 0; j < projectSize; j++) {
                        projectorEntities.get(j).setBoardRoomEntity(boardRooms.get(i));
                        projectorEntities.get(j).setBoardRoomId(boardRooms.get(i).getTypeId());
                        projectorEntities.get(j).save();
                    }
                }

                List<ModelEntity> modelEntities = boardRooms.get(i).getModel();
                if (modelEntities != null) {
                    int modelSize = modelEntities.size();
                    for (int j = 0; j < modelSize; j++) {
                        modelEntities.get(j).setBoardRoomEntity(boardRooms.get(i));
                        modelEntities.get(j).setBoardRoomId(boardRooms.get(i).getTypeId());
                        modelEntities.get(j).save();
                    }
                }

                List<AudioEntity> audioEntities = boardRooms.get(i).getAudio();
                if (audioEntities != null) {
                    int audioSize = audioEntities.size();
                    for (int j = 0; j < audioSize; j++) {
                        audioEntities.get(j).setBoardRoomEntity(boardRooms.get(i));
                        audioEntities.get(j).setBoardRoomId(boardRooms.get(i).getTypeId());
                        audioEntities.get(j).save();
                    }
                }

                List<TvEntity> tvEntities = boardRooms.get(i).getTv();
                if (tvEntities != null) {
                    int tvSize = tvEntities.size();
                    for (int j = 0; j < tvSize; j++) {
                        tvEntities.get(j).setBoardRoomEntity(boardRooms.get(i));
                        tvEntities.get(j).setBoardRoomId(boardRooms.get(i).getTypeId());
                        tvEntities.get(j).save();
                    }
                }

                List<AirEntity> airEntities = boardRooms.get(i).getAir();
                if (airEntities != null) {
                    int airSize = airEntities.size();
                    for (int j = 0; j < airSize; j++) {
                        airEntities.get(j).setBoardRoomEntity(boardRooms.get(i));
                        airEntities.get(j).setBoardRoomId(boardRooms.get(i).getTypeId());
                        airEntities.get(j).save();
                    }
                }

                List<CurtainEntity> curtainEntities = boardRooms.get(i).getCurtain();
                if (curtainEntities != null) {
                    int curtainSize = curtainEntities.size();
                    for (int j = 0; j < curtainSize; j++) {
                        curtainEntities.get(j).setBoardRoomEntity(boardRooms.get(i));
                        curtainEntities.get(j).setBoardRoomId(boardRooms.get(i).getTypeId());
                        curtainEntities.get(j).save();
                    }
                }

                List<SetAirEntity> setAirEntities = boardRooms.get(i).getSet();
                if (setAirEntities != null) {
                    int setAirSize = setAirEntities.size();
                    for (int j = 0; j < setAirSize; j++) {
                        setAirEntities.get(j).setBoardRoomEntity(boardRooms.get(i));
                        setAirEntities.get(j).setBoardRoomId(boardRooms.get(i).getTypeId());
                        setAirEntities.get(j).save();
                    }
                }

            }


            ActiveAndroid.setTransactionSuccessful();
        } finally {
            ActiveAndroid.endTransaction();
        }
        return true;
    }

    @Override
    public boolean saveMachineCode(BoardRoomMachineCode boardRoomMachineCode) {
        if (boardRoomMachineCode == null) {
            return false;
        }

        ActiveAndroid.beginTransaction();
        try {

                boardRoomMachineCode.save();
                List<MachineCode> machineCode = boardRoomMachineCode.getMachineCode();

                if (machineCode == null) {
                    return false;
                }

                int size = machineCode.size();
                for (int i = 0; i < size; i++) {
                    machineCode.get(i).setBoardRoomMachineCode(boardRoomMachineCode);
                    machineCode.get(i).save();
                }

            ActiveAndroid.setTransactionSuccessful();
        } finally {
            ActiveAndroid.endTransaction();
        }
        return true;
    }

    @Override
    public boolean saveMusicList(List<MusicsListEntity> musicsListEntities, String key) {

        if (musicsListEntities == null || musicsListEntities.size() == 0) {
            return false;
        }
        ActiveAndroid.beginTransaction();
        try {

            for (int i = 0; i < musicsListEntities.size(); i++) {
                musicsListEntities.get(i).setMusicList(key);
                musicsListEntities.get(i).save();
            }
            ActiveAndroid.setTransactionSuccessful();
        } finally {
            ActiveAndroid.endTransaction();
        }
        return true;
    }
}
