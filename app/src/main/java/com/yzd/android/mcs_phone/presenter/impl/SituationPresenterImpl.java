package com.yzd.android.mcs_phone.presenter.impl;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.github.obsessive.library.eventbus.EventCenter;
import com.github.obsessive.library.utils.SPUtils;
import com.yzd.android.mcs_phone.R;
import com.yzd.android.mcs_phone.api.Constants;
import com.yzd.android.mcs_phone.api.EvenBusConstants;
import com.yzd.android.mcs_phone.interator.impl.SituationInteratorImpl;
import com.yzd.android.mcs_phone.presenter.ISituationPresenter;
import com.yzd.android.mcs_phone.ui.adapter.SituationAdapter;
import com.yzd.android.mcs_phone.view.ISituationView;

import java.util.Calendar;

import me.drakeet.materialdialog.MaterialDialog;


/**
 * Created by Administrator on 2015/10/14.
 */
public class SituationPresenterImpl implements ISituationPresenter {


    private Context mContext;
    private ISituationView mSituationView;
    private SituationAdapter mSituationAdapter;
    private SituationInteratorImpl mSituationInterator;
    private MaterialDialog mMaterialDialog;
    private String now_situation;

    public SituationPresenterImpl(Context context, ISituationView situationView) {
        mContext = context;
        mSituationView = situationView;
        mSituationInterator = new SituationInteratorImpl();
    }


    @Override
    public void initialized() {
        mSituationAdapter = new SituationAdapter(mContext, mSituationInterator);
        mSituationView.initRecyclerView(new LinearLayoutManager(mContext),mSituationAdapter);
        getNowHour();
    }

    @Override
    public void getOnClickViewId(int id) {
        now_situation = null;
        if (id == R.id.btn_day) {
            mSituationView.showDayMode();
            SPUtils.put(mContext, "NOW_MODE", 0);
            now_situation = mSituationInterator.getSituationOrder(mContext,
                    (int) SPUtils.get(mContext, "NOW_SITUATION", 0),
                    0);
            mSituationInterator.sendOrder(mContext, now_situation);

        } else if (id == R.id.btn_night) {
            mSituationView.showNightMode();
            SPUtils.put(mContext, "NOW_MODE", 1);
            now_situation = mSituationInterator.getSituationOrder(mContext,
                    (int) SPUtils.get(mContext, "NOW_SITUATION", 0),
                    1);
            mSituationInterator.sendOrder(mContext, now_situation);

        } else if (id == R.id.fab) {

            mMaterialDialog = new MaterialDialog(mContext)
                    .setTitle("提示")
                    .setMessage("将关闭所有正在运行的设备！！!")
                    .setPositiveButton("确定", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mMaterialDialog.dismiss();
                            now_situation = mSituationInterator.getSituationOrder(mContext,
                                    Constants.OFFMODE,
                                    0);
                            mSituationInterator.sendOrder(mContext, now_situation);
                        }
                    })
                    .setNegativeButton("取消", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mMaterialDialog.dismiss();
                        }
                    });

            mMaterialDialog.show();
        }
    }

    @Override
    public int getNowHour() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(calendar.HOUR_OF_DAY);
        if (hour > 6 && hour < 18) {
            mSituationView.showDayMode();
            SPUtils.put(mContext, "NOW_MODE", 0);
        } else {
            mSituationView.showNightMode();
            SPUtils.put(mContext, "NOW_MODE", 1);
        }
        return hour;
    }

    @Override
    public void evenComming(EventCenter<String> eventCenter) {

        if (eventCenter.getEventCode() == EvenBusConstants.EVEN_UDP_RECEIVE_ALL_EQUIP_STATE) {
            String data = eventCenter.getData();
            String substring = null;
            substring = data.substring(186, 187);
            if (substring.equals("0")) {
                mSituationView.showDayMode();
                SPUtils.put(mContext, "NOW_MODE", 0);
            } else if (substring.equals("8")) {
                mSituationView.showNightMode();
                SPUtils.put(mContext, "NOW_MODE", 1);
            }


            substring = data.substring(187, 188);
            if (substring.equals("0")) {
                mSituationAdapter.setPressPosition(0);

            } else if (substring.equals("1")) {
                mSituationAdapter.setPressPosition(1);

            } else if (substring.equals("2")) {
                mSituationAdapter.setPressPosition(2);

            } else if (substring.equals("3")) {
                mSituationAdapter.setPressPosition(3);

            } else if (substring.equals("4")) {
                mSituationAdapter.setPressPosition(-1);

            }
        }
    }

}
