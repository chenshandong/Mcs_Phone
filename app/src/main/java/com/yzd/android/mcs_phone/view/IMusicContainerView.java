package com.yzd.android.mcs_phone.view;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.yzd.android.mcs_phone.bean.database.MusicsListEntity;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/11/11.
 */
public interface IMusicContainerView extends ICommonContainerView {
    void initMiniPlayerViewPager(FragmentPagerAdapter adapter);

    void startActivity_1(Class clazz);

    void setPlayBackGround();

    void setPauseBackGround();

    FragmentManager getFM();

    void showSnackBar(String str);
}
