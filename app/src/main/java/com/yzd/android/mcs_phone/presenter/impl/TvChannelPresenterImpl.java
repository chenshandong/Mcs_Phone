package com.yzd.android.mcs_phone.presenter.impl;

import android.content.Context;

import com.yzd.android.mcs_phone.presenter.Presenter;
import com.yzd.android.mcs_phone.view.ITvChannelView;

/**
 * Created by Administrator on 2015/11/6.
 */
public class TvChannelPresenterImpl implements Presenter {

    private Context mContext;
    private ITvChannelView mTvChannelView;

    public TvChannelPresenterImpl(Context context, ITvChannelView tvChannelView) {
        mContext = context;
        mTvChannelView = tvChannelView;
    }

    @Override
    public void initialized() {

    }
}
