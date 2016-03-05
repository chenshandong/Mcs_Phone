package com.yzd.android.mcs_phone.presenter;

import com.github.obsessive.library.eventbus.EventCenter;

/**
 * Created by Administrator on 2015/10/28.
 */
public interface ISituationPresenter extends Presenter {

    void getOnClickViewId(int id);

    int getNowHour();

    void evenComming(EventCenter<String> eventCenter);
}
