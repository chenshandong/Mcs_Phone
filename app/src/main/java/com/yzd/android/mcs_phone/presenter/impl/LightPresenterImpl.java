package com.yzd.android.mcs_phone.presenter.impl;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;

import com.github.obsessive.library.eventbus.EventCenter;
import com.github.obsessive.library.utils.SPUtils;
import com.yzd.android.mcs_phone.api.Constants;
import com.yzd.android.mcs_phone.api.EvenBusConstants;
import com.yzd.android.mcs_phone.bean.database.BoardRoomMachineCode;
import com.yzd.android.mcs_phone.bean.database.LightEntity;
import com.yzd.android.mcs_phone.model.impl.SelectDataBaseImpl;
import com.yzd.android.mcs_phone.presenter.ILightPresenter;
import com.yzd.android.mcs_phone.udp.InitData.ByteMerge;
import com.yzd.android.mcs_phone.ui.adapter.LightsAdapter;
import com.yzd.android.mcs_phone.view.ILightView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Clearlove on 15/10/29.
 */
public class LightPresenterImpl implements ILightPresenter{

    private final Context mContext;
    private final ILightView mLightView;
    private LightsAdapter mLightsAdapter;

    public LightPresenterImpl(Context context, ILightView lightView) {
        mContext = context;
        mLightView = lightView;
    }

    @Override
    public void initialized() {

        BoardRoomMachineCode boardRoomMachineCode = SelectDataBaseImpl.getInstances().selectAllMachineCode();
        List<LightEntity> lightEntities = null;
        if (boardRoomMachineCode != null) {
            int position = (int) SPUtils.get(mContext, Constants.SELECTORROOM, 0);
            int typeId = boardRoomMachineCode.getMachineCode().get(position).getTypeId();
            lightEntities = SelectDataBaseImpl.getInstances().selectLight(typeId);

        }

        mLightsAdapter = new LightsAdapter(mContext, lightEntities);
        mLightView.initRecycler(new GridLayoutManager(mContext, 3), mLightsAdapter);
    }

    @Override
    public void evenComming(EventCenter<String> eventCenter) {
        if (eventCenter.getEventCode() == EvenBusConstants.EVEN_UDP_RECEIVE_ALL_EQUIP_STATE) {

            String data = eventCenter.getData();
            String substring = data.substring(75, 89).replaceAll(" ", "");
            String array[] = new String[5];
            ArrayList<Boolean> arrayList = new ArrayList<>();

            for (int j = 0; j < 5; j++) {

                array[j] = substring.substring(2 * j, 2 * j + 2);
                byte[] bytes = array[j].getBytes();

                for (int i = array[j].length() - 1; i >= 0; i--) {

                    arrayList.addAll(ByteMerge.parseByteToBit(bytes[i]));
                }

            }
            if (mLightsAdapter != null) {

                mLightsAdapter.updateLightState(arrayList);
            }
        }
    }
}
