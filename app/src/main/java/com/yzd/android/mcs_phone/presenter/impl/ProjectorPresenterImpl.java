package com.yzd.android.mcs_phone.presenter.impl;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.yzd.android.mcs_phone.R;
import com.yzd.android.mcs_phone.interator.impl.ProjectorInteratorImpl;
import com.yzd.android.mcs_phone.presenter.IProjectorPresenter;
import com.yzd.android.mcs_phone.ui.fragment.ProjectorFragment;

/**
 * Created by Clearlove on 15/10/30.
 */
public class ProjectorPresenterImpl implements IProjectorPresenter {

    private Context mContext;
    private boolean mPower;
    private int mPosition;
    private final ProjectorInteratorImpl mProjectorInterator;

    public ProjectorPresenterImpl(Context context) {
        mContext = context;
        mProjectorInterator = new ProjectorInteratorImpl();
    }


    @Override
    public void onBtnShotClick(View view) {
        if (ismPower()) {

            switch (view.getId()) {

                case R.id.btn_mode:
                    mProjectorInterator.mode(mContext, mPosition);
                    break;
                case R.id.btn_source:
                    mProjectorInterator.source(mContext, mPosition);
                    break;
                case R.id.btn_project_ok:
                    mProjectorInterator.ok(mContext, mPosition);
                    break;
                case R.id.btn_project_up:
                    mProjectorInterator.up(mContext, mPosition);
                    break;
                case R.id.btn_project_left:
                    mProjectorInterator.left(mContext, mPosition);
                    break;
                case R.id.btn_project_down:
                    mProjectorInterator.down(mContext, mPosition);
                    break;
                case R.id.btn_project_right:
                    mProjectorInterator.right(mContext, mPosition);
                    break;
                case R.id.btn_vol_down:
                    mProjectorInterator.volDown(mContext, mPosition);
                    break;
                case R.id.btn_vol_up:
                    mProjectorInterator.volUp(mContext, mPosition);
                    break;


            }

        }

        if (view.getId() == R.id.btn_power) {
           if (ismPower()) {
               setmPower(false);
               mProjectorInterator.powerClose(mContext, mPosition);
           } else {
               setmPower(true);
               mProjectorInterator.powerOn(mContext, mPosition);
           }
        } else if (view.getId() == R.id.btn_screen_up) {
            mProjectorInterator.screenShotUp(mContext, mPosition);

        } else if (view.getId() == R.id.btn_screen_down) {

            mProjectorInterator.screenShotDown(mContext, mPosition);
        }
    }

    @Override
    public void getPosition(ProjectorFragment projectorFragment) {
        Bundle arguments = projectorFragment.getArguments();
        mPosition = arguments.getInt("position") + 1;
    }

    @Override
    public void onBtnLongClick(View view) {
        if (view.getId() == R.id.btn_screen_up) {
            mProjectorInterator.screenLongUp(mContext, mPosition);
        } else if (view.getId() == R.id.btn_screen_down){
            mProjectorInterator.screenLongDown(mContext, mPosition);
        }

    }

    @Override
    public void initialized() {

    }

    public boolean ismPower() {
        return mPower;
    }

    public void setmPower(boolean mPower) {
        this.mPower = mPower;
    }
}
