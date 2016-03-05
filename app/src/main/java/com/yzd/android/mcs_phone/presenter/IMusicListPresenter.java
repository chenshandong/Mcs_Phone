package com.yzd.android.mcs_phone.presenter;

import com.yzd.android.mcs_phone.bean.database.MusicsListEntity;
import com.yzd.android.mcs_phone.ui.fragment.MusicFragment;

import java.util.ArrayList;

/**
 * Created by Clearlove on 15/11/2.
 */
public interface IMusicListPresenter extends Presenter {


    void updateMusicList(ArrayList<MusicsListEntity> musicsListEntities);

    String getBundle(MusicFragment musicFragment);
    int getPosition();

    void showIndicator();
}
