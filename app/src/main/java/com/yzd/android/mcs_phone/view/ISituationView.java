package com.yzd.android.mcs_phone.view;

import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

/**
 * Created by Administrator on 2015/10/15.
 */
public interface ISituationView {

    ImageView getDayImageView();
    ImageView getNightImageView();
    void showDayMode();
    void showNightMode();
    void initRecyclerView(RecyclerView.LayoutManager layoutManager, RecyclerView.Adapter adapter);
}
