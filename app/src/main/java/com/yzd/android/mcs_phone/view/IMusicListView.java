package com.yzd.android.mcs_phone.view;

import android.support.v7.widget.RecyclerView;

/**
 * Created by Clearlove on 15/11/2.
 */
public interface IMusicListView {

    void initRecyclerView(RecyclerView.LayoutManager layoutManager, RecyclerView.Adapter adapter);
    void showError();
    void hideError();
}
