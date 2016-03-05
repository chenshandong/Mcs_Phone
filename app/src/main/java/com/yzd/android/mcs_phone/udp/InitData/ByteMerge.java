package com.yzd.android.mcs_phone.udp.InitData;

import com.yzd.android.mcs_phone.bean.other.AirCondition;
import com.yzd.android.mcs_phone.utils.ByteToBitUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/8/12.
 */
public class ByteMerge {

    /**
     *
     * @param airCondition
     * @return
     */
    public static String AirConditionMerge(AirCondition airCondition){

        int l = airCondition.temperature - 16;
        int h = airCondition.status + airCondition.mode + airCondition.fanRate;

        String i = Integer.toHexString(l);
        String j = Integer.toHexString(h);

        return j + i;
    }

    public static String Curtain(String channel, String status) {

        int ch = Integer.parseInt(channel);
        int sta = Integer.parseInt(status);

        String s1 = Integer.toHexString(ch);
        String s2 = Integer.toHexString(sta);
        return s2 + s1;
    }


    public static List<Boolean> parseByteToBit(byte b) {
        byte j = 0;
        if (b >= 48 && b <= 57) {
            j = (byte) ( b - '0');
        } else if (b >= 97 && b <= 122) {
            j = (byte) (b - 'a' + 10);
        }

        byte[] bytes = ByteToBitUtils.getBooleanArray(j);
        ArrayList<Boolean> booleans = new ArrayList<Boolean>();
        int length = bytes.length;
        for (int i = length - 1; i >= 4; i--) {
            if (bytes[i] == 0) {
                booleans.add(false);
            } else {
                booleans.add(true);
            }
        }
        return booleans;
    }
}
