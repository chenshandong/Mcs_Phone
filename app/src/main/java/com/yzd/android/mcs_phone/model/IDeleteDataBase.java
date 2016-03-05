package com.yzd.android.mcs_phone.model;

/**
 * Created by Administrator on 2015/10/20.
 */
public interface IDeleteDataBase {

    boolean deleteMusicList(String key);

    boolean deleteOneMusic(String key, String urlKey);

    boolean deleteMachineCode();

    boolean deleteAllBoardRoom();
}
