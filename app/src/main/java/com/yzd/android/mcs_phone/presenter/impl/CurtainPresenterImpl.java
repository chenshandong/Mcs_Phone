package com.yzd.android.mcs_phone.presenter.impl;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import com.github.obsessive.library.utils.SPUtils;
import com.yzd.android.mcs_phone.api.Constants;
import com.yzd.android.mcs_phone.bean.database.BoardRoomMachineCode;
import com.yzd.android.mcs_phone.bean.database.CurtainEntity;
import com.yzd.android.mcs_phone.model.impl.SelectDataBaseImpl;
import com.yzd.android.mcs_phone.presenter.Presenter;
import com.yzd.android.mcs_phone.ui.adapter.CurtainAdapter;
import com.yzd.android.mcs_phone.view.ICurtainView;

import java.util.List;

/**
 * Created by Clearlove on 15/10/29.
 */
public class CurtainPresenterImpl implements Presenter {

    private ICurtainView mCurtainView;
    private Context mContext;
    private CurtainAdapter mCurtainAdapter;

    public CurtainPresenterImpl(Context context, ICurtainView curtainView) {
        mContext = context;
        mCurtainView = curtainView;
    }

    @Override
    public void initialized() {
        BoardRoomMachineCode boardRoomMachineCode = SelectDataBaseImpl.getInstances().selectAllMachineCode();
        List<CurtainEntity> curtainEntities = null;
        if (boardRoomMachineCode != null) {

            int position = (int) SPUtils.get(mContext, Constants.SELECTORROOM, 0);
            int typeId = boardRoomMachineCode.getMachineCode().get(position).getTypeId();
            curtainEntities = SelectDataBaseImpl.getInstances().selectCurtains(typeId);
        }

        mCurtainAdapter = new CurtainAdapter(mContext, curtainEntities);
        mCurtainView.initRecycler(new LinearLayoutManager(mContext), mCurtainAdapter);
    }
}
