package com.yzd.android.mcs_phone.utils;

/**
 * Created by Administrator on 2015/11/6.
 */
public class NdkUtils {

    public native int[] auchCRCLo();
    public native int[] auchCRCHi();
    public native String getCrc16(String string, int leng);

    static{
        System.loadLibrary("txPark_CRC");
    }
}
