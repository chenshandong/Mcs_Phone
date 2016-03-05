package com.github.obsessive.library.listener;

import android.view.View;

/**
 * Created by Clearlove on 15/10/29.
 */
public interface ICustomSwitchStateChangeListener {

    void onStateChange(View view, int position, boolean checked);
}
