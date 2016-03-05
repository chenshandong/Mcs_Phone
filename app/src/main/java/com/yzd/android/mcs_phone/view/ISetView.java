package com.yzd.android.mcs_phone.view;

import android.widget.EditText;

import com.yzd.android.mcs_phone.widgets.OkCommentButton;
import com.yzd.android.mcs_phone.widgets.RevealBackgroundView;

/**
 * Created by Clearlove on 15/10/28.
 */
public interface ISetView {

    RevealBackgroundView getReveal();

    String getIp();

    String getPort();

    OkCommentButton getOkBtn();

    void killNoAnimation();

    void clearEdt();

    EditText getEdt();
}
