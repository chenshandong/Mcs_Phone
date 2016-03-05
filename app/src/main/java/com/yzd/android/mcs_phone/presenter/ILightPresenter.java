package com.yzd.android.mcs_phone.presenter;

import com.github.obsessive.library.eventbus.EventCenter;

/**
 * Created by Clearlove on 15/10/29.
 */
public interface ILightPresenter extends Presenter {

    void evenComming(EventCenter<String> eventCenter);

}
