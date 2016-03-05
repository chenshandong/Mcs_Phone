package com.yzd.android.mcs_phone.ui.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;

import com.yzd.android.mcs_phone.ui.fragment.NewFeatureFragment;

/**
 * Created by Administrator on 2015/12/17.
 */
public class NewFeatureAdapter extends FragmentPagerAdapter {


    public NewFeatureAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        NewFeatureFragment newFeatureFragment = new NewFeatureFragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
        newFeatureFragment.setArguments(args);
        return newFeatureFragment;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
