package com.yzd.android.mcs_phone.presenter;

import android.view.View;

/**
 * Created by Clearlove on 15/10/28.
 */
public interface ISetPresenter extends Presenter {

    void viewOnClick(View view);

    boolean customViewOnClick(View view);

}
