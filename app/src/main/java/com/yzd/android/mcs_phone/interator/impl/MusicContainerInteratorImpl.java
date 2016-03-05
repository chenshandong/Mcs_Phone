package com.yzd.android.mcs_phone.interator.impl;

import android.content.Context;

import com.yzd.android.mcs_phone.R;
import com.yzd.android.mcs_phone.bean.other.BaseEntity;
import com.yzd.android.mcs_phone.interator.IBaseContainInterator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/10/16.
 */
public class MusicContainerInteratorImpl implements IBaseContainInterator {
    @Override
    public List<BaseEntity> getCommonCategoryList(Context context) {
        List<BaseEntity> resultData = new ArrayList<>();
        String[] imagesCategoryArray = context.getResources().getStringArray(R.array.music_list);
        int length = imagesCategoryArray.length;
        for (int i = 0 ;i < length; i++) {
            resultData.add(new BaseEntity("music", imagesCategoryArray[i]));
        }
        return resultData;
    }
}
