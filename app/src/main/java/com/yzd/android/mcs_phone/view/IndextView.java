package com.yzd.android.mcs_phone.view;

import android.widget.RelativeLayout;

import com.github.obsessive.library.base.BaseLazyFragment;
import com.yzd.android.mcs_phone.bean.other.NavigationEntity;
import com.yzd.android.mcs_phone.ui.adapter.VPFragmentAdapter;
import com.yzd.android.mcs_phone.widgets.RevealBackgroundView;

import java.util.List;

public interface IndextView {

    void initializeViews(List<BaseLazyFragment> fragments, List<NavigationEntity> navigationList);

    void showReveal();
    void hideReveal();

    void showSweetSheet();
    void dismissSweetSheet();

    RelativeLayout getRelativeLayout();

    RevealBackgroundView getRevealBackgroundView();

    void killNoAnimation();

    VPFragmentAdapter getVPFragmentAdapter();

}
