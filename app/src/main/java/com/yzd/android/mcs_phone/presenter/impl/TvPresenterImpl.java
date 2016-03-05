package com.yzd.android.mcs_phone.presenter.impl;

import android.content.Context;
import android.view.View;

import com.yzd.android.mcs_phone.R;
import com.yzd.android.mcs_phone.interator.impl.TvInteratorImpl;
import com.yzd.android.mcs_phone.presenter.Presenter;
import com.yzd.android.mcs_phone.ui.activity.TvModeContainerActivity;
import com.yzd.android.mcs_phone.view.ITvView;

/**
 * Created by Administrator on 2015/11/6.
 */
public class TvPresenterImpl implements Presenter {

    private Context mContext;
    private ITvView mTvView;
    private TvInteratorImpl mTvInterator;

    private boolean power;

    public TvPresenterImpl(Context context, ITvView tvView) {
        mContext = context;
        mTvView = tvView;
        mTvInterator = new TvInteratorImpl(context);
    }

    @Override
    public void initialized() {

    }

    public void onClick(View view) {
        if (isPower()) {

            switch (view.getId()) {
                case R.id.btn_mode:
                    mTvView.readGo(TvModeContainerActivity.class);
                    break;
                case R.id.btn_source:
                    mTvInterator.keySource();
                    break;
                case R.id.btn_key_1:
                    mTvInterator.keyNum(1);
                    break;
                case R.id.btn_key_2:
                    mTvInterator.keyNum(2);
                    break;
                case R.id.btn_key_3:
                    mTvInterator.keyNum(3);
                    break;
                case R.id.btn_key_4:
                    mTvInterator.keyNum(4);
                    break;
                case R.id.btn_key_5:
                    mTvInterator.keyNum(5);
                    break;
                case R.id.btn_key_6:
                    mTvInterator.keyNum(6);
                    break;
                case R.id.btn_key_7:
                    mTvInterator.keyNum(7);
                    break;
                case R.id.btn_key_8:
                    mTvInterator.keyNum(8);
                    break;
                case R.id.btn_key_9:
                    mTvInterator.keyNum(9);
                    break;
                case R.id.btn_key_0:
                    mTvInterator.keyNum(0);
                    break;
                case R.id.btn_key_back:
                    mTvInterator.keyBack();
                    break;
                case R.id.btn_key_delete:
                    mTvInterator.keyDelete();
                    break;
                case R.id.btn_channel_plus:
                    mTvInterator.channelUp();
                    break;
                case R.id.btn_channel_desc:
                    mTvInterator.channelDown();
                    break;
                case R.id.btn_project_ok:
                    mTvInterator.keyBoardOk();
                    break;
                case R.id.btn_project_left:
                    mTvInterator.keyBoardLeft();
                    break;
                case R.id.btn_project_right:
                    mTvInterator.keyBoardRight();
                    break;
                case R.id.btn_project_up:
                    mTvInterator.keyBoardUp();
                    break;
                case R.id.btn_project_down:
                    mTvInterator.keyBoardDown();
                    break;
                case R.id.btn_vol_plus:
                    mTvInterator.volUp();
                    break;
                case R.id.btn_vol_desc:
                    mTvInterator.volDown();
                    break;
                case R.id.btn_menu:
                    mTvInterator.powerOff();
                    setPower(false);
                    break;

            }
        } else {
            if (R.id.btn_menu == view.getId()) {
                setPower(true);
                mTvInterator.powerOn();
            }
        }


    }

    public boolean isPower() {
        return power;
    }

    public void setPower(boolean power) {
        this.power = power;
    }
}
