package com.yzd.android.mcs_phone.presenter;

import com.github.obsessive.library.eventbus.EventCenter;
import com.mingle.sweetpick.SweetSheet;

/**
 * Created by Clearlove on 15/10/28.
 */
public interface IIndextPresenter extends Presenter {


    void initMenu();

    SweetSheet initSweetSheet();

    void onSweetSheetClick();

    void evenComming(EventCenter<String> eventCenter);


}
