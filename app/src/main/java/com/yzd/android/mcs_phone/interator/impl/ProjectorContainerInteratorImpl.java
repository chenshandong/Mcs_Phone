package com.yzd.android.mcs_phone.interator.impl;

import android.content.Context;

import com.github.obsessive.library.utils.SPUtils;
import com.yzd.android.mcs_phone.api.Constants;
import com.yzd.android.mcs_phone.bean.database.BoardRoomMachineCode;
import com.yzd.android.mcs_phone.bean.database.ProjectorEntity;
import com.yzd.android.mcs_phone.bean.other.BaseEntity;
import com.yzd.android.mcs_phone.interator.IBaseContainInterator;
import com.yzd.android.mcs_phone.model.impl.SelectDataBaseImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Clearlove on 15/10/16.
 */
public class ProjectorContainerInteratorImpl implements IBaseContainInterator {

    @Override
    public List<BaseEntity> getCommonCategoryList(Context context) {
        List<ProjectorEntity> projectorEntities = null;
        BoardRoomMachineCode boardRoomMachineCode = SelectDataBaseImpl.getInstances().selectAllMachineCode();
        if (boardRoomMachineCode != null) {
            int position = (int) SPUtils.get(context, Constants.SELECTORROOM, 0);
            int typeId = boardRoomMachineCode.getMachineCode().get(position).getTypeId();
            projectorEntities = SelectDataBaseImpl.getInstances().selectProjects(typeId);

        } else {
            projectorEntities = new ArrayList<>();
        }
        List<BaseEntity> resultData = new ArrayList<>();

        int length = projectorEntities.size();
        for (int i = 0 ;i < length; i++) {
            resultData.add(new BaseEntity("projector", projectorEntities.get(i).getName()));
        }
        return resultData;
    }
}
