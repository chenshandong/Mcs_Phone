package com.yzd.android.mcs_phone.view;

import com.yzd.android.mcs_phone.bean.database.MusicsListEntity;

import java.util.List;

/**
 * Created by Administrator on 2015/10/19.
 */
public interface IMusicSearchView {

    void refreshRecyclerView(List<MusicsListEntity> musicsListEntities);
}
