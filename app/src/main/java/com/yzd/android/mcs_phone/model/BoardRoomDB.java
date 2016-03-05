package com.yzd.android.mcs_phone.model;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.yzd.android.mcs_phone.bean.database.AirEntity;
import com.yzd.android.mcs_phone.bean.database.BoardRoomEntity;
import com.yzd.android.mcs_phone.bean.database.CurtainEntity;
import com.yzd.android.mcs_phone.bean.database.LightEntity;
import com.yzd.android.mcs_phone.bean.database.MachineCode;
import com.yzd.android.mcs_phone.bean.database.ModelEntity;
import com.yzd.android.mcs_phone.bean.database.ProjectorEntity;
import com.yzd.android.mcs_phone.bean.database.SetAirEntity;
import com.yzd.android.mcs_phone.bean.database.TvEntity;

import java.util.List;

/**
 * Created by Administrator on 2015/8/26.
 */
public class BoardRoomDB {

    public static boolean saveBoardRoom(List<BoardRoomEntity> boardRoomEntityArrayList) {

        if (boardRoomEntityArrayList == null) {
            return false;
        }

        ActiveAndroid.beginTransaction();
        try{
            int boardRoomSize = boardRoomEntityArrayList.size();
            for (int i = 0; i < boardRoomSize; i++) {

                BoardRoomEntity boardRoomEntity = boardRoomEntityArrayList.get(i);
                boardRoomEntity.save();

                if (boardRoomEntity.getAir() != null) {
                    int size = boardRoomEntity.getAir().size();
                    for (int j = 0; j < size; j++) {
                        boardRoomEntity.getAir().get(j).setBoardRoomId(boardRoomEntity.getTypeId());
                        boardRoomEntity.getAir().get(j).save();
                    }
                }

                if (boardRoomEntity.getLight() != null) {
                    int size = boardRoomEntity.getLight().size();
                    for (int j = 0; j < size; j++) {
                        boardRoomEntity.getLight().get(j).setBoardRoomId(boardRoomEntity.getTypeId());
                        boardRoomEntity.getLight().get(j).save();
                    }
                }

                if (boardRoomEntity.getSet() != null) {
                    int size = boardRoomEntity.getSet().size();
                    for (int j = 0; j < size; j++) {
                        boardRoomEntity.getSet().get(j).setBoardRoomId(boardRoomEntity.getTypeId());
                        boardRoomEntity.getSet().get(j).save();
                    }
                }

                if (boardRoomEntity.getAudio() != null) {
                    int size = boardRoomEntity.getAudio().size();
                    for (int j = 0; j < size; j++) {
                        boardRoomEntity.getAudio().get(j).setBoardRoomId(boardRoomEntity.getTypeId());
                        boardRoomEntity.getAudio().get(j).save();
                    }
                }

                if (boardRoomEntity.getCurtain() != null) {
                    int size = boardRoomEntity.getCurtain().size();
                    for (int j = 0; j < size; j++) {
                        boardRoomEntity.getCurtain().get(j).setBoardRoomId(boardRoomEntity.getTypeId());
                        boardRoomEntity.getCurtain().get(j).save();
                    }
                }

                if (boardRoomEntity.getProjector() != null) {
                    int size = boardRoomEntity.getProjector().size();
                    for (int j = 0; j < size; j++) {
                        boardRoomEntity.getProjector().get(j).setBoardRoomId(boardRoomEntity.getTypeId());
                        boardRoomEntity.getProjector().get(j).save();
                    }
                }

                if (boardRoomEntity.getTv() != null) {
                    int size = boardRoomEntity.getTv().size();
                    for (int j = 0; j < size; j++) {
                        boardRoomEntity.getTv().get(j).setBoardRoomId(boardRoomEntity.getTypeId());
                        boardRoomEntity.getTv().get(j).save();
                    }
                }

                if (boardRoomEntity.getModel() != null) {
                    int size = boardRoomEntity.getModel().size();
                    for (int j = 0; j < size; j++) {
                        boardRoomEntity.getModel().get(j).setBoardRoomId(boardRoomEntity.getTypeId());
                        boardRoomEntity.getModel().get(j).save();
                    }
                }

            }
            ActiveAndroid.setTransactionSuccessful();
        } finally {
            ActiveAndroid.endTransaction();
        }
        return true;
    }

    public static void deleteBoardRoomList() {
        new Delete()
                .from(BoardRoomEntity.class)
                .execute();
    }

    public static BoardRoomEntity getOneBoardRoom(int typeName) {
        return new Select()
                .from(BoardRoomEntity.class)
                .where("typeId = ?", typeName)
                .executeSingle();
    }

    public static boolean saveMachineCode(List<MachineCode> machineCodeList) {

        if (machineCodeList == null) {
            return false;
        }

        ActiveAndroid.beginTransaction();
        try {

            int size = machineCodeList.size();
            for (int i = 0; i < size; i++) {
                machineCodeList.get(i).save();
            }

            ActiveAndroid.setTransactionSuccessful();
        } finally {
            ActiveAndroid.endTransaction();
        }
        return true;
    }

    public static void saveOneMachineIp(MachineCode machineCode) {
        new Delete()
                .from(MachineCode.class)
                .where("typeId = ?", machineCode.getTypeId())
                .execute();
        machineCode.save();
    }

    public static void deleteMachineCode() {
        new Delete()
                .from(MachineCode.class)
                .execute();
    }

    public static List<MachineCode> getMachineCodeList() {
        return new Select()
                .from(MachineCode.class)
                .execute();
    }

    public static List<BoardRoomEntity> getBoardRoomList(){
        return new Select()
                .from(BoardRoomEntity.class)
                .execute();
    }

    public static List<LightEntity> getLight(int typeId) {
        return new Select()
                .from(LightEntity.class)
                .where("boardRoomId = ?", typeId)
                .execute();
    }

    public static void deleteLight() {
        new Delete()
                .from(LightEntity.class)
                .execute();
    }

    public static List<AirEntity> getAir(int typeId) {
        return new Select()
                .from(AirEntity.class)
                .where("boardRoomId = ?",typeId)
                .execute();
    }

    public static void deleteAir() {
        new Delete()
                .from(AirEntity.class)
                .execute();
    }

    public static List<ProjectorEntity> getProjector(int typeId) {
        return new Select()
                .from(ProjectorEntity.class)
                .where("boardRoomId = ?",typeId)
                .execute();
    }

    public static void deleteProjector() {
        new Delete()
                .from(ProjectorEntity.class)
                .execute();
    }

    public static List<TvEntity> getTv(int typeId) {
        return new Select()
                .from(TvEntity.class)
                .where("boardRoomId = ?",typeId)
                .execute();
    }

    public static void deleteTv() {
        new Delete()
                .from(TvEntity.class)
                .execute();
    }

    public static List<CurtainEntity> getCurtain(int typeId) {
        return new Select()
                .from(CurtainEntity.class)
                .where("boardRoomId = ?",typeId)
                .execute();
    }

    public static List<SetAirEntity> getSetAir(int typeId) {
        return new Select()
                .from(SetAirEntity.class)
                .where("boardRoomId = ?",typeId)
                .execute();
    }

    public static void deleteCurtain() {
        new Delete()
                .from(CurtainEntity.class)
                .execute();
    }

    public static List<ModelEntity> getModel(int typeId) {
        return new Select()
                .from(ModelEntity.class)
                .where("boardRoomId = ?",typeId)
                .execute();
    }

    public static void deleteModel() {
        new Delete()
                .from(ModelEntity.class)
                .execute();
    }



}
