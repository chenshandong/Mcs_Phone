package com.yzd.android.mcs_phone.interator;

import android.content.Context;

import com.yzd.android.mcs_phone.bean.database.MusicEntity;
import com.yzd.android.mcs_phone.bean.database.MusicsListEntity;

import java.util.List;

/**
 * Created by Administrator on 2015/10/19.
 */
public interface IMusicSearchInterator {

    List<MusicsListEntity> readMusic(Context context);
}
