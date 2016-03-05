package com.yzd.android.mcs_phone.presenter.impl;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;

import com.github.obsessive.library.eventbus.EventCenter;
import com.yzd.android.mcs_phone.R;
import com.yzd.android.mcs_phone.api.EvenBusConstants;
import com.yzd.android.mcs_phone.bean.other.AirCondition;
import com.yzd.android.mcs_phone.interator.impl.AirConditionInteratorImpl;
import com.yzd.android.mcs_phone.presenter.IAirConditionPresenter;
import com.yzd.android.mcs_phone.ui.fragment.AirConditionFragment;
import com.yzd.android.mcs_phone.utils.ParseUtil;
import com.yzd.android.mcs_phone.view.IAirConditionView;

/**
 * Created by Clearlove on 15/10/30.
 */
public class AirConditionPresenterImpl implements IAirConditionPresenter {


    private Context mContext;
    private IAirConditionView mAirConditionView;
    private int mNowTemp = 16;
    private boolean mPowering;
    private int mPosition;
    private int mAirConditionNum;

    private AirCondition mAirCondition;
    private final AirConditionInteratorImpl mAirConditionInterator;

    public AirConditionPresenterImpl(Context context, IAirConditionView airConditionView) {
        mContext = context;
        mAirConditionView = airConditionView;
        mAirCondition = new AirCondition();
        mAirConditionInterator = new AirConditionInteratorImpl(context);
    }


    @Override
    public Typeface initTempFonts() {
        return Typeface.createFromAsset(mContext.getAssets(), "fonts/Sadelle.ttf");
    }

    @Override
    public void OnItemShotClick(View view) {
        int id = view.getId();
        if (ismPowering()) {
            if (id != R.id.btn_useAll) {
                switch (id) {
                    case R.id.btn_cold:
                        mAirCondition.setMode(0);
                        mAirConditionView.showMode(0);
                        break;
                    case R.id.btn_hot:
                        mAirCondition.setMode(1);
                        mAirConditionView.showMode(1);
                        break;
                    case R.id.btn_wind:
                        mAirCondition.setMode(2);
                        mAirConditionView.showMode(2);
                        break;
                    case R.id.btn_small:
                        mAirCondition.setFanRate(0);
                        mAirConditionView.showFanSpeed(0);
                        break;
                    case R.id.btn_mid:
                        mAirCondition.setFanRate(1);
                        mAirConditionView.showFanSpeed(1);

                        break;
                    case R.id.btn_large:
                        mAirCondition.setFanRate(2);
                        mAirConditionView.showFanSpeed(2);

                        break;
                    case R.id.btn_up:
                        int i = plusTemp();
                        mAirCondition.setTemperature(i);
                        mAirConditionView.showTemp(i);
                        break;
                    case R.id.btn_down:
                        int j = descTemp();
                        mAirCondition.setTemperature(j);
                        mAirConditionView.showTemp(j);
                        break;

                }
                mAirConditionInterator.sendOneOrder(mAirCondition);

            } else {
                mAirConditionInterator.sendAllOrder(mAirCondition, mAirConditionNum);
            }

        }

        if (id == R.id.btn_power) {
            if (ismPowering()) {
                setmPowering(false);
                mAirCondition.setStatus(0);
                mAirConditionView.powerOff();
            } else {
                setmPowering(true);
                mAirCondition.setStatus(1);
                mAirConditionView.powerOn(mAirCondition.getTemperature(), mAirCondition.getMode(), mAirCondition.getFanRate());
            }
            mAirConditionInterator.sendOneOrder(mAirCondition);
        }
    }

    @Override
    public int plusTemp() {
        if (mNowTemp >= 30) {
            mNowTemp = 30;
        } else {
            mNowTemp++;
        }
        return mNowTemp;
    }

    @Override
    public int descTemp() {
        if (mNowTemp <= 16) {
            mNowTemp = 16;
        } else {
            mNowTemp--;
        }
        return mNowTemp;
    }

    @Override
    public void getBundle(AirConditionFragment airConditionFragment) {
        Bundle arguments = airConditionFragment.getArguments();
        mPosition = arguments.getInt("position") + 1;
        mAirConditionNum = arguments.getInt("airConditionNum");

        mAirCondition.setPosition(mPosition);
        mAirConditionInterator.setPosition(mPosition);
    }

    @Override
    public void evenComming(EventCenter<String> eventCenter) {
        if (eventCenter.getEventCode() == EvenBusConstants.EVEN_UDP_RECEIVE_ALL_EQUIP_STATE) {
            String data = eventCenter.getData();
            String sub = null;
            if (mPosition == 1) {
                sub = data.substring(90, 107).replaceAll(" ", "");
            } else if (mPosition == 2) {
                sub = data.substring(108, 125).replaceAll(" ", "");
            } else if (mPosition == 3) {
                sub = data.substring(126, 143).replaceAll(" ", "");
            } else if (mPosition == 4) {
                sub = data.substring(144, 162).replaceAll(" ", "");
            }

            if (sub != null) {

                String s = sub.substring(4, 6);
                if (s.equals("00")) {
                    mAirCondition.setMode(0);
                } else if (s.equals("01")) {
                    mAirCondition.setMode(1);
                } else if (s.equals("02")) {
                    mAirCondition.setMode(2);
                }

                String mode = sub.substring(6, 8);
                if (mode.equals("00")) {
                    mAirCondition.setFanRate(0);
                } else if (mode.equals("01")) {
                    mAirCondition.setFanRate(0);
                } else if (mode.equals("02")) {
                    mAirCondition.setFanRate(1);
                } else if (mode.equals("03")) {
                    mAirCondition.setFanRate(2);
                }


                int shiWei = ParseUtil.getStringToInt(sub.substring(10, 11)) * 16;
                int geWei = ParseUtil.getStringToInt(sub.substring(11, 12));
                mAirCondition.setTemperature(shiWei + geWei);


                String substring = sub.substring(0, 2);
                if (substring.equals("00")) {
                    mAirCondition.setStatus(0);
                    mAirConditionView.powerOff();
                    setmPowering(false);
                } else {
                    setmPowering(true);
                    mAirCondition.setStatus(1);
                    mAirConditionView.powerOn(mAirCondition.getTemperature(), mAirCondition.getMode(), mAirCondition.getFanRate());
                }

            }
        }
    }

    @Override
    public void initialized() {

    }

    public boolean ismPowering() {
        return mPowering;
    }

    public void setmPowering(boolean mPowering) {
        this.mPowering = mPowering;
    }
}
