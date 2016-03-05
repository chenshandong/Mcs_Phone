package com.yzd.android.mcs_phone.presenter;

import android.graphics.Typeface;
import android.view.View;

import com.github.obsessive.library.eventbus.EventCenter;
import com.yzd.android.mcs_phone.ui.fragment.AirConditionFragment;

/**
 * Created by Administrator on 2015/10/27.
 */
public interface IAirConditionPresenter extends Presenter {

    Typeface initTempFonts();

    void OnItemShotClick(View view);

    int plusTemp();
    int descTemp();

    void getBundle(AirConditionFragment airConditionFragment);

    void evenComming(EventCenter<String> eventCenter);

}
