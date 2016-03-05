package com.yzd.android.mcs_phone.model.impl;

import com.activeandroid.query.Delete;
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
import com.yzd.android.mcs_phone.model.IDeleteDataBase;

/**
 * Created by Administrator on 2015/10/20.
 */
public class DeleteDataBaseImpl implements IDeleteDataBase {

    private static DeleteDataBaseImpl instances;
    private DeleteDataBaseImpl() {}
    public static DeleteDataBaseImpl getInstances() {
        if (null == instances) {
            synchronized (DeleteDataBaseImpl.class) {
                if (null == instances) {
                    instances = new DeleteDataBaseImpl();
                }
            }
        }
        return instances;
    }

    @Override
    public boolean deleteMusicList(String key) {
        new Delete()
                .from(MusicsListEntity.class)
                .where("musicList = ?",key)
                .execute();
        return true;
    }

    @Override
    public boolean deleteOneMusic(String key, String urlKey) {
        new Delete()
                .from(MusicsListEntity.class)
                .where("musicList = ? and url = ?", new Object[]{key, urlKey})
                .execute();
        return true;
    }

    @Override
    public boolean deleteMachineCode() {
        new Delete().from(MachineCode.class).execute();
        new Delete().from(BoardRoomMachineCode.class).execute();
        return true;
    }

    @Override
    public boolean deleteAllBoardRoom() {

        new Delete().from(LightEntity.class).execute();
        new Delete().from(ProjectorEntity.class).execute();
        new Delete().from(ModelEntity.class).execute();
        new Delete().from(ModelEntity.class).execute();
        new Delete().from(AudioEntity.class).execute();
        new Delete().from(TvEntity.class).execute();
        new Delete().from(AirEntity.class).execute();
        new Delete().from(CurtainEntity.class).execute();
        new Delete().from(SetAirEntity.class).execute();

        new Delete().from(BoardRoomEntity.class).execute();
        new Delete().from(BoardRoom.class).execute();
        return true;
    }
}
