package com.yzd.android.mcs_phone.ui.fragment;


import android.app.Fragment;
import android.view.View;
import android.widget.ImageButton;

import com.andexert.library.RippleView;
import com.github.obsessive.library.eventbus.EventCenter;
import com.yzd.android.mcs_phone.R;
import com.yzd.android.mcs_phone.base.fragment.BaseFragment;
import com.yzd.android.mcs_phone.presenter.impl.TvPresenterImpl;
import com.yzd.android.mcs_phone.view.ITvView;

import java.util.List;

import butterknife.InjectView;

/**
 * A simple {@link Fragment} subclass.
 */
public class TVFragment extends BaseFragment implements View.OnClickListener, ITvView {

    @InjectView(R.id.btn_mode)
    RippleView mTvMode;
    @InjectView(R.id.btn_menu)
    RippleView btnPower;
    @InjectView(R.id.btn_source)
    RippleView btnSource;
    @InjectView(R.id.btn_key_1)
    ImageButton btnKey1;
    @InjectView(R.id.btn_key_2)
    ImageButton btnKey2;
    @InjectView(R.id.btn_key_3)
    ImageButton btnKey3;
    @InjectView(R.id.btn_key_4)
    ImageButton btnKey4;
    @InjectView(R.id.btn_key_5)
    ImageButton btnKey5;
    @InjectView(R.id.btn_key_6)
    ImageButton btnKey6;
    @InjectView(R.id.btn_key_7)
    ImageButton btnKey7;
    @InjectView(R.id.btn_key_8)
    ImageButton btnKey8;
    @InjectView(R.id.btn_key_9)
    ImageButton btnKey9;
    @InjectView(R.id.btn_key_back)
    ImageButton btnKeyBack;
    @InjectView(R.id.btn_key_0)
    ImageButton btnKey0;
    @InjectView(R.id.btn_key_delete)
    ImageButton btnKeyDelete;
    @InjectView(R.id.btn_channel_plus)
    ImageButton btnChannelPlus;
    @InjectView(R.id.btn_channel_desc)
    ImageButton btnChannelDesc;
    @InjectView(R.id.btn_project_ok)
    ImageButton btnProjectOk;
    @InjectView(R.id.btn_project_left)
    ImageButton btnProjectLeft;
    @InjectView(R.id.btn_project_right)
    ImageButton btnProjectRight;
    @InjectView(R.id.btn_project_up)
    ImageButton btnProjectUp;
    @InjectView(R.id.btn_project_down)
    ImageButton btnProjectDown;
    @InjectView(R.id.btn_vol_plus)
    ImageButton btnVolPlus;
    @InjectView(R.id.btn_vol_desc)
    ImageButton btnVolDesc;
    private TvPresenterImpl mTvPresenter;

    private void setClickListener() {
        mTvMode.setOnClickListener(this);
        btnPower.setOnClickListener(this);
        btnSource.setOnClickListener(this);
        btnKey1.setOnClickListener(this);
        btnKey2.setOnClickListener(this);
        btnKey3.setOnClickListener(this);
        btnKey4.setOnClickListener(this);
        btnKey5.setOnClickListener(this);
        btnKey6.setOnClickListener(this);
        btnKey7.setOnClickListener(this);
        btnKey8.setOnClickListener(this);
        btnKey9.setOnClickListener(this);
        btnKey0.setOnClickListener(this);
        btnKeyDelete.setOnClickListener(this);
        btnKeyBack.setOnClickListener(this);
        btnChannelPlus.setOnClickListener(this);
        btnChannelDesc.setOnClickListener(this);
        btnProjectOk.setOnClickListener(this);
        btnProjectLeft.setOnClickListener(this);
        btnProjectUp.setOnClickListener(this);
        btnProjectDown.setOnClickListener(this);
        btnVolPlus.setOnClickListener(this);
        btnVolDesc.setOnClickListener(this);
        btnProjectRight.setOnClickListener(this);
    }
    @Override
    protected void onFirstUserVisible() {

    }

    @Override
    protected void onUserVisible() {

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
        setClickListener();
        mTvPresenter = new TvPresenterImpl(getActivity(), this);
    }



    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_tv;
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
    public void onClick(View view) {
        mTvPresenter.onClick(view);
    }


    @Override
    public void readGo(Class<?> clazz) {
        readyGo(clazz);
    }
}
