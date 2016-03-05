package com.yzd.android.mcs_phone.ui.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.yzd.android.mcs_phone.bean.other.BaseEntity;
import com.yzd.android.mcs_phone.ui.fragment.MusicFragment;

import java.util.List;

/**
 * Created by Administrator on 2015/10/16.
 */
public class MusicContainerAdapter extends FragmentPagerAdapter {

    private List<BaseEntity> mCategoryList = null;

    public MusicContainerAdapter(FragmentManager fm, List<BaseEntity> categoryList) {
        super(fm);
        mCategoryList = categoryList;
    }


    @Override
    public Fragment getItem(int position) {
        MusicFragment musicFragment = new MusicFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        musicFragment.setArguments(bundle);
        return musicFragment;
    }

    @Override
    public int getCount() {
        return null != mCategoryList ? mCategoryList.size() : 0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return null != mCategoryList ? mCategoryList.get(position).getName() : null;
    }
}
