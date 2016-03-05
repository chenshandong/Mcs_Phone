package com.yzd.android.mcs_phone.ui.fragment;


import android.app.Fragment;
import android.graphics.Typeface;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.andexert.library.RippleView;
import com.github.obsessive.library.eventbus.EventCenter;
import com.yzd.android.mcs_phone.R;
import com.yzd.android.mcs_phone.base.fragment.BaseFragment;
import com.yzd.android.mcs_phone.presenter.impl.AirConditionPresenterImpl;
import com.yzd.android.mcs_phone.presenter.impl.UdpSendOrder;
import com.yzd.android.mcs_phone.view.IAirConditionView;

import java.util.List;

import butterknife.InjectView;

/**
 * A simple {@link Fragment} subclass.
 */
public class AirConditionFragment extends BaseFragment implements IAirConditionView, View.OnClickListener {

    @InjectView(R.id.btn_power)
    RippleView mPowerBtn;

    @InjectView(R.id.btn_useAll)
    RippleView mUseAllBtn;

    @InjectView(R.id.tv_temp)
    TextView mTemp;

    @InjectView(R.id.tv_temp_du)
    TextView mTempDu;

    @InjectView(R.id.btn_cold)
    ImageView mBtnCold;

    @InjectView(R.id.btn_hot)
    ImageView mBtnHot;

    @InjectView(R.id.btn_wind)
    ImageView mBtnWind;

    @InjectView(R.id.btn_small)
    ImageView mBtnSmall;

    @InjectView(R.id.btn_mid)
    ImageView mBtnMid;

    @InjectView(R.id.btn_large)
    ImageView mBtnLarge;

    @InjectView(R.id.btn_up)
    ImageView mBtnUp;

    @InjectView(R.id.btn_down)
    ImageView mBtnDown;

    private AirConditionPresenterImpl mAirConditionPresenter;

    @Override
    protected void onFirstUserVisible() {
        Typeface typeface = mAirConditionPresenter.initTempFonts();
        mTemp.setTypeface(typeface);
        mTempDu.setTypeface(typeface);

        UdpSendOrder.getInstances().getAllEquipStatus(mContext);
    }

    @Override
    protected void onUserVisible() {
        UdpSendOrder.getInstances().getAllEquipStatus(mContext);
    }

    @Override
    protected void onUserInvisible() {

    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    @Override
    protected void initViewsAndEvents() {

        mAirConditionPresenter = new AirConditionPresenterImpl(getActivity(), this);
        mAirConditionPresenter.getBundle(this);
        initListener();
    }

    private void initListener() {
        mBtnCold.setOnClickListener(this);
        mBtnHot.setOnClickListener(this);
        mBtnWind.setOnClickListener(this);

        mBtnSmall.setOnClickListener(this);
        mBtnMid.setOnClickListener(this);
        mBtnLarge.setOnClickListener(this);

        mPowerBtn.setOnClickListener(this);
        mUseAllBtn.setOnClickListener(this);

        mBtnUp.setOnClickListener(this);
        mBtnDown.setOnClickListener(this);

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_air_condition;
    }

    @Override
    protected void onEventComming(EventCenter eventCenter) {

    }

    @Override
    protected void onEventComming(List<EventCenter> eventCenter) {

    }

    @Override
    protected boolean isBindEventBusHere() {
        return false;
    }

    @Override
    public void showFanSpeed(int position) {
        if (position == 0) {
            mBtnSmall.setBackground(getResources().getDrawable(R.mipmap.air_speed_mid_press));
            mBtnMid.setBackground(getResources().getDrawable(R.mipmap.air_speed_mid_default));
            mBtnLarge.setBackground(getResources().getDrawable(R.mipmap.air_speed_large_default));
        } else if (position == 1) {
            mBtnSmall.setBackground(getResources().getDrawable(R.mipmap.air_speed_mid_default));
            mBtnMid.setBackground(getResources().getDrawable(R.mipmap.air_speed_mid_press));
            mBtnLarge.setBackground(getResources().getDrawable(R.mipmap.air_speed_large_default));
        } else if (position == 2) {
            mBtnSmall.setBackground(getResources().getDrawable(R.mipmap.air_speed_mid_default));
            mBtnMid.setBackground(getResources().getDrawable(R.mipmap.air_speed_mid_default));
            mBtnLarge.setBackground(getResources().getDrawable(R.mipmap.air_speed_large_press));
        } else {
            mBtnSmall.setBackground(getResources().getDrawable(R.mipmap.air_speed_mid_default));
            mBtnMid.setBackground(getResources().getDrawable(R.mipmap.air_speed_mid_default));
            mBtnLarge.setBackground(getResources().getDrawable(R.mipmap.air_speed_large_default));
        }
    }

    @Override
    public void showMode(int position) {
        if (position == 0) {
            mBtnCold.setBackground(getResources().getDrawable(R.mipmap.air_mode_cold_press));
            mBtnHot.setBackground(getResources().getDrawable(R.mipmap.air_mode_hot_default));
            mBtnWind.setBackground(getResources().getDrawable(R.mipmap.air_mode_wind_default));
        } else if (position == 1) {
            mBtnCold.setBackground(getResources().getDrawable(R.mipmap.air_mode_cold_default));
            mBtnHot.setBackground(getResources().getDrawable(R.mipmap.air_mode_hot_press));
            mBtnWind.setBackground(getResources().getDrawable(R.mipmap.air_mode_wind_default));
        } else if (position == 2) {
            mBtnCold.setBackground(getResources().getDrawable(R.mipmap.air_mode_cold_default));
            mBtnHot.setBackground(getResources().getDrawable(R.mipmap.air_mode_hot_default));
            mBtnWind.setBackground(getResources().getDrawable(R.mipmap.air_mode_wind_press));
        } else {
            mBtnCold.setBackground(getResources().getDrawable(R.mipmap.air_mode_cold_default));
            mBtnHot.setBackground(getResources().getDrawable(R.mipmap.air_mode_hot_default));
            mBtnWind.setBackground(getResources().getDrawable(R.mipmap.air_mode_wind_default));
        }
    }

    @Override
    public void showTemp(int temp) {
        mTemp.setText("" + temp + "°");
    }

    @Override
    public void powerOff() {
        mTemp.setVisibility(View.INVISIBLE);
        showMode(-1);
        showFanSpeed(-1);
    }

    @Override
    public void powerOn(int temp, int mode, int speed) {
        mTemp.setVisibility(View.VISIBLE);
        showTemp(temp);
        showMode(mode);
        showFanSpeed(speed);
    }

    @Override
    public void onClick(View view) {
        mAirConditionPresenter.OnItemShotClick(view);
    }
}
