package com.yzd.android.mcs_phone.presenter.impl;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.animation.AnimationUtils;

import com.github.obsessive.library.utils.SPUtils;
import com.yzd.android.mcs_phone.R;
import com.yzd.android.mcs_phone.api.Constants;
import com.yzd.android.mcs_phone.presenter.ISetPresenter;
import com.yzd.android.mcs_phone.utils.KeyBoardUtils;
import com.yzd.android.mcs_phone.view.ISetView;
import com.yzd.android.mcs_phone.widgets.RevealBackgroundView;

/**
 * Created by Clearlove on 15/10/28.
 */
public class SetPresenterImpl implements ISetPresenter, RevealBackgroundView.OnStateChangeListener {


    private Context mContext;
    private ISetView mSetView;
    private RevealBackgroundView mReveal;

    public SetPresenterImpl(Context context, ISetView setView) {
        mContext = context;
        mSetView = setView;
    }

    @Override
    public void viewOnClick(View view) {
        final int[] start = new int[2];
        view.getLocationOnScreen(start);
        start[0] += view.getWidth() / 2;

        switch (view.getId()) {
            case R.id.view1:
                mReveal.setFillPaintColor(Color.rgb(62, 128, 47));
                SPUtils.put(mContext, "AUTO_AET_THEME", R.style.AppTheme_Green);
                break;

            case R.id.view2:
                mReveal.setFillPaintColor(Color.rgb(244, 180, 0));
                SPUtils.put(mContext, "AUTO_AET_THEME", R.style.AppTheme_Yellow);

                break;

            case R.id.view3:
                mReveal.setFillPaintColor(Color.rgb(66, 127, 237));
                SPUtils.put(mContext, "AUTO_AET_THEME", R.style.AppTheme_Blue);

                break;

            case R.id.view4:
                mReveal.setFillPaintColor(Color.rgb(178, 52, 36));
                SPUtils.put(mContext, "AUTO_AET_THEME", R.style.AppTheme_Red);

                break;

            case R.id.view5:
                mReveal.setFillPaintColor(Color.rgb(63, 79, 93));
                SPUtils.put(mContext, "AUTO_AET_THEME", R.style.AppTheme);

                break;
        }
        mReveal.startFromLocation(start);

    }

    @Override
    public boolean customViewOnClick(View view) {
        String ip = mSetView.getIp();
        String port = mSetView.getPort();

        if (ip == null || ip.equals("") || port == null || port.equals("")) {
            Snackbar.make(view,"IP或者port不能为空！",Snackbar.LENGTH_SHORT).show();
            mSetView.getOkBtn().startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.shake_error));
            return false;
        }

        SPUtils.put(mContext, Constants.SERVERIP, ip);
        SPUtils.put(mContext, Constants.SERVERPORT, port);

        mSetView.clearEdt();
        KeyBoardUtils.closeKeybord(mSetView.getEdt(), mContext);
        Snackbar.make(view,"设置成功",Snackbar.LENGTH_SHORT).show();

        return  true;
    }

    @Override
    public void initialized() {
        mReveal = mSetView.getReveal();
        mReveal.setOnStateChangeListener(this);
    }

    @Override
    public void onStateChange(int state) {
        if (state == RevealBackgroundView.STATE_FINISHED) {
            mReveal.setToFinishedFrame();
            mSetView.killNoAnimation();
        }
    }
}
