package com.yzd.android.mcs_phone.presenter.impl;

import android.content.Context;

import com.yzd.android.mcs_phone.interator.IBaseContainInterator;
import com.yzd.android.mcs_phone.interator.impl.ProjectorContainerInteratorImpl;
import com.yzd.android.mcs_phone.presenter.Presenter;
import com.yzd.android.mcs_phone.view.ICommonContainerView;

/**
 * Created by Clearlove on 15/10/16.
 */
public class ProjectorContainerPresenterImpl implements Presenter {

    private ICommonContainerView mCommonContainerView;
    private Context mContext;
    private IBaseContainInterator mCommonContainerInterator;

    public ProjectorContainerPresenterImpl(Context context, ICommonContainerView commonContainerView) {

        mContext = context;
        mCommonContainerView = commonContainerView;
        mCommonContainerInterator = new ProjectorContainerInteratorImpl();
    }

    @Override
    public void initialized() {
        mCommonContainerView.initializePagerViews(mCommonContainerInterator.getCommonCategoryList(mContext));

    }
}
