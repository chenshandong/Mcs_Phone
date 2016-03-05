package com.yzd.android.mcs_phone.interator;

import android.content.Context;

import com.yzd.android.mcs_phone.bean.other.BaseEntity;

import java.util.List;

/**
 * Created by Clearlove on 15/10/15.
 */
public interface IBaseContainInterator {

    List<BaseEntity> getCommonCategoryList(Context context);

}
