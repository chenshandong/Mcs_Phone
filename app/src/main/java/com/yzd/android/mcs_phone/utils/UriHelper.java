package com.yzd.android.mcs_phone.utils;

import android.content.Context;

import com.github.obsessive.library.utils.SPUtils;
import com.yzd.android.mcs_phone.api.Constants;

/**
 * Created by Administrator on 2015/10/26.
 */
public class UriHelper {

    private static UriHelper instance;
    private UriHelper() {
    }

    public static UriHelper getInstance() {
        if (null == instance) {
            synchronized (UriHelper.class) {
                if (null == instance) {
                    instance = new UriHelper();
                }
            }
        }
        return instance;
    }

    public String getBoardRoomUrl(Context context, String string) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("http://");
        stringBuffer.append(SPUtils.get(context, Constants.SERVERIP, "192.168.1.1"));
        stringBuffer.append(":");
        stringBuffer.append(SPUtils.get(context, Constants.SERVERPORT, "8888"));
        return stringBuffer.append(string).toString();
    }

}