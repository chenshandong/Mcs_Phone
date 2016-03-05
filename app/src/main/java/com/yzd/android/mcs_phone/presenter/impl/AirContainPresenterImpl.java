package com.yzd.android.mcs_phone.presenter.impl;

import android.content.Context;

import com.yzd.android.mcs_phone.interator.IBaseContainInterator;
import com.yzd.android.mcs_phone.interator.impl.AirContainerInteratorImpl;
import com.yzd.android.mcs_phone.presenter.Presenter;
import com.yzd.android.mcs_phone.view.ICommonContainerView;

/**
 * Created by Clearlove on 15/10/15.
 */
public class AirContainPresenterImpl implements Presenter {


    private ICommonContainerView mCommonContainerView;
    private Context mContext;
    private IBaseContainInterator mCommonContainerInterator;

    public AirContainPresenterImpl(Context context, ICommonContainerView commonContainerView) {

        mContext = context;
        mCommonContainerView = commonContainerView;
        mCommonContainerInterator = new AirContainerInteratorImpl();
    }

    @Override
    public void initialized() {
        mCommonContainerView.initializePagerViews(mCommonContainerInterator.getCommonCategoryList(mContext));

    }
}
