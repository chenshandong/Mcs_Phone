package com.yzd.android.mcs_phone.ui.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.yzd.android.mcs_phone.bean.other.BaseEntity;
import com.yzd.android.mcs_phone.ui.fragment.TvChannelFragment;

import java.util.List;

/**
 * Created by Clearlove on 15/11/2.
 */
public class TvChannelAdapter extends FragmentPagerAdapter {

    private List<BaseEntity> mCategoryList = null;

    public TvChannelAdapter(FragmentManager fm, List<BaseEntity> categoryList) {
        super(fm);
        mCategoryList = categoryList;
    }


    @Override
    public Fragment getItem(int position) {
        TvChannelFragment tvChannelFragment = new TvChannelFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        tvChannelFragment.setArguments(bundle);
        return tvChannelFragment;
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
