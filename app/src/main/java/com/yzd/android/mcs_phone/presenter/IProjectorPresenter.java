package com.yzd.android.mcs_phone.presenter;

import android.view.View;

import com.yzd.android.mcs_phone.ui.fragment.ProjectorFragment;

/**
 * Created by Clearlove on 15/10/30.
 */
public interface IProjectorPresenter extends Presenter {

    void onBtnShotClick(View view);

    void getPosition(ProjectorFragment projectorFragment);

    void onBtnLongClick(View view);
}
