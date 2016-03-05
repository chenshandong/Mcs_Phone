package com.yzd.android.mcs_phone.interator;

import android.content.Context;

/**
 * Created by Administrator on 2015/10/14.
 */
public interface ISituationInterator {

    void sendOrder(Context context, String order);

    String getSituationOrder(Context context, int situation, int mode);
}
