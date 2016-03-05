package com.yzd.android.mcs_phone.interator;

import android.content.Context;

/**
 * Created by Clearlove on 15/10/30.
 */
public interface IProjectorInterator {

    void powerOn(Context context, int position);

    void powerClose(Context context, int position);

    void mode(Context context, int position);

    void source(Context context, int position);

    void ok(Context context, int position);

    void up(Context context, int position);

    void down(Context context, int position);

    void left(Context context, int position);

    void right(Context context, int position);

    void volUp(Context context, int position);

    void volDown(Context context, int position);

    void screenShotUp(Context context, int position);

    void screenShotDown(Context context, int position);

    void screenLongUp(Context context, int position);

    void screenLongDown(Context context, int position);

}
