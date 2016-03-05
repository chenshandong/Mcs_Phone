package com.yzd.android.mcs_phone.ui.adapter.viewholder;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.yzd.android.mcs_phone.bean.other.BaseEntity;
import com.yzd.android.mcs_phone.ui.fragment.ProjectorFragment;

import java.util.List;

/**
 * Created by Clearlove on 15/10/16.
 */
public class ProjectorContainerAdapter extends FragmentPagerAdapter {

    private List<BaseEntity> mCategoryList = null;

    public ProjectorContainerAdapter(FragmentManager fm, List<BaseEntity> categoryList) {
        super(fm);
        mCategoryList = categoryList;
    }


    @Override
    public Fragment getItem(int position) {
        ProjectorFragment projectorFragment = new ProjectorFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        projectorFragment.setArguments(bundle);
        return projectorFragment;
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
