package com.yzd.android.mcs_phone.presenter.impl;

import android.content.Context;

import com.yzd.android.mcs_phone.R;
import com.yzd.android.mcs_phone.bean.other.BaseEntity;
import com.yzd.android.mcs_phone.presenter.Presenter;
import com.yzd.android.mcs_phone.view.ICommonContainerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/11/6.
 */
public class TvChannelContainerPresenterImpl implements Presenter {

    private Context mContext;
    private ICommonContainerView mCommonContainerView;

    public TvChannelContainerPresenterImpl(Context context, ICommonContainerView commonContainerView) {
        mContext = context;
        mCommonContainerView = commonContainerView;
    }

    @Override
    public void initialized() {
        List<BaseEntity> categoryList = new ArrayList<>();
        String[] stringArray = mContext.getResources().getStringArray(R.array.channel_list);
        for (int i = 0; i < stringArray.length; i ++) {
            categoryList.add(new BaseEntity("", stringArray[i]));
        }
        mCommonContainerView.initializePagerViews(categoryList);
    }
}
