package com.yzd.android.mcs_phone.view;


import com.yzd.android.mcs_phone.bean.database.MusicEntity;
import com.yzd.android.mcs_phone.bean.database.MusicsListEntity;
import com.yzd.android.mcs_phone.bean.other.BaseEntity;

import java.util.ArrayList;
import java.util.List;

public interface ICommonContainerView {

    void initializePagerViews(List<BaseEntity> categoryList);


}
