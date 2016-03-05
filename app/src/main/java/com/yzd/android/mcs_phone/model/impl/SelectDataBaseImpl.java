package com.yzd.android.mcs_phone.model.impl;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.query.Select;
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
import com.yzd.android.mcs_phone.model.ISelectDataBase;

import java.util.List;

/**
 * Created by Administrator on 2015/10/13.
 */
public class SelectDataBaseImpl implements ISelectDataBase {

    private static SelectDataBaseImpl instances;

    private SelectDataBaseImpl(){}

    public static SelectDataBaseImpl getInstances() {
        if (null == instances) {
            synchronized (SelectDataBaseImpl.class) {
                if (null == instances) {
                    instances = new SelectDataBaseImpl();
                }
            }
        }
        return instances;
    }

    @Override
    public BoardRoom selectAllBoardRoom() {

        BoardRoom execute = null;

        ActiveAndroid.beginTransaction();
        try {

            execute = new Select()
                    .from(BoardRoom.class)
                    .executeSingle();

            List<BoardRoomEntity> boardRoom = execute.boardRooms();
            execute.setBoardRoom(boardRoom);

            if (boardRoom != null) {
                int size = boardRoom.size();
                for (int i = 0; i < size; i++) {
                    List<ProjectorEntity> projectors = boardRoom.get(i).projectors();
                    execute.getBoardRoom().get(i).setProjector(projectors);
                    List<ModelEntity> models = boardRoom.get(i).models();
                    execute.getBoardRoom().get(i).setModel(models);
                    List<AudioEntity> audios = boardRoom.get(i).audios();
                    execute.getBoardRoom().get(i).setAudio(audios);
                    List<TvEntity> tvs = boardRoom.get(i).tvs();
                    execute.getBoardRoom().get(i).setTv(tvs);
                    List<AirEntity> airs = boardRoom.get(i).airs();
                    execute.getBoardRoom().get(i).setAir(airs);
                    List<LightEntity> lights = boardRoom.get(i).lights();
                    execute.getBoardRoom().get(i).setLight(lights);
                    List<CurtainEntity> curtains = boardRoom.get(i).curtains();
                    execute.getBoardRoom().get(i).setCurtain(curtains);
                    List<SetAirEntity> setAirEntities = boardRoom.get(i).setAirs();
                    execute.getBoardRoom().get(i).setSet(setAirEntities);
                }
            }

            ActiveAndroid.setTransactionSuccessful();
        } finally {
            ActiveAndroid.endTransaction();
        }
        return execute;
    }

    @Override
    public BoardRoomEntity selectOneRoom(int typeId) {
        BoardRoomEntity boardRoom;
        ActiveAndroid.beginTransaction();
        try {

            boardRoom = new Select()
                    .from(BoardRoomEntity.class)
                    .where("typeId = ?", typeId)
                    .executeSingle();
            List<ProjectorEntity> projectors = boardRoom.projectors();
            boardRoom.setProjector(projectors);
            List<ModelEntity> models = boardRoom.models();
            boardRoom.setModel(models);
            List<AudioEntity> audios = boardRoom.audios();
            boardRoom.setAudio(audios);
            List<TvEntity> tvs = boardRoom.tvs();
            boardRoom.setTv(tvs);
            List<AirEntity> airs = boardRoom.airs();
            boardRoom.setAir(airs);
            List<LightEntity> lights = boardRoom.lights();
            boardRoom.setLight(lights);
            List<CurtainEntity> curtains = boardRoom.curtains();
            boardRoom.setCurtain(curtains);
            List<SetAirEntity> setAirEntities = boardRoom.setAirs();
            boardRoom.setSet(setAirEntities);

            ActiveAndroid.setTransactionSuccessful();
        } finally {
            ActiveAndroid.endTransaction();
        }
        return boardRoom;
    }

    @Override
    public BoardRoomMachineCode selectAllMachineCode() {

        BoardRoomMachineCode execute;
        ActiveAndroid.beginTransaction();
        try {

            execute = new Select()
                    .from(BoardRoomMachineCode.class)
                    .executeSingle();

            if (execute == null) {
                return null;
            }
            List<MachineCode> machineCodes = execute.boardRooms();
            execute.setMachineCode(machineCodes);

            ActiveAndroid.setTransactionSuccessful();
        } finally {
            ActiveAndroid.endTransaction();
        }
        return execute;
    }

    @Override
    public List<MusicsListEntity> selectMusicLists(String key) {
        return new Select()
                .from(MusicsListEntity.class)
                .where("musicList = ?", key)
                .execute();
    }

    @Override
    public List<LightEntity> selectLight(int boardRoomId) {
        return new Select()
                .from(LightEntity.class)
                .where("boardRoomId = ?", boardRoomId)
                .execute();
    }

    public List<CurtainEntity> selectCurtains(int boardRoomId) {
        return new Select()
                .from(CurtainEntity.class)
                .where("boardRoomId = ?", boardRoomId)
                .execute();
    }

    public List<AirEntity> selectAirs(int boardRoomId) {
        return new Select()
                .from(AirEntity.class)
                .where("boardRoomId = ?", boardRoomId)
                .execute();
    }

    public List<ProjectorEntity> selectProjects(int boardRoomId) {
        return new Select()
                .from(ProjectorEntity.class)
                .where("boardRoomId = ?", boardRoomId)
                .execute();
    }
}
