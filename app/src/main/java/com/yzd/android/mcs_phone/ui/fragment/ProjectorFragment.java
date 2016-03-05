package com.yzd.android.mcs_phone.ui.fragment;


import android.app.Fragment;
import android.view.View;
import android.widget.ImageButton;

import com.andexert.library.RippleView;
import com.github.obsessive.library.eventbus.EventCenter;
import com.yzd.android.mcs_phone.R;
import com.yzd.android.mcs_phone.base.fragment.BaseFragment;
import com.yzd.android.mcs_phone.presenter.impl.ProjectorPresenterImpl;

import java.util.List;

import butterknife.InjectView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProjectorFragment extends BaseFragment implements View.OnClickListener, View.OnLongClickListener {

    @InjectView(R.id.btn_power)
    RippleView mPowerBtn;
    @InjectView(R.id.btn_mode)
    RippleView mModeBtn;
    @InjectView(R.id.btn_source)
    RippleView mSourceBtn;
    @InjectView(R.id.btn_project_ok)
    ImageButton mOkBtn;
    @InjectView(R.id.btn_project_up)
    ImageButton btnProjectUp;
    @InjectView(R.id.btn_project_left)
    ImageButton btnProjectLeft;
    @InjectView(R.id.btn_project_down)
    ImageButton btnProjectDown;
    @InjectView(R.id.btn_project_right)
    ImageButton btnProjectRight;
    @InjectView(R.id.btn_vol_down)
    ImageButton btnVolDown;
    @InjectView(R.id.btn_vol_up)
    ImageButton btnVolUp;
    @InjectView(R.id.btn_screen_up)
    ImageButton btnScreenUp;
    @InjectView(R.id.btn_screen_down)
    ImageButton btnScreenDown;
    private ProjectorPresenterImpl mProjectorPresenter;


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

        mPowerBtn.setOnClickListener(this);
        mModeBtn.setOnClickListener(this);
        mSourceBtn.setOnClickListener(this);
        mOkBtn.setOnClickListener(this);
        btnProjectUp.setOnClickListener(this);
        btnProjectLeft.setOnClickListener(this);
        btnProjectDown.setOnClickListener(this);
        btnProjectRight.setOnClickListener(this);
        btnVolDown.setOnClickListener(this);
        btnVolUp.setOnClickListener(this);
        btnScreenUp.setOnClickListener(this);
        btnScreenDown.setOnClickListener(this);

        btnScreenUp.setOnLongClickListener(this);
        btnScreenDown.setOnLongClickListener(this);

        mProjectorPresenter = new ProjectorPresenterImpl(getActivity());
        mProjectorPresenter.getPosition(this);
    }


    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_projector;
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
        mProjectorPresenter.onBtnShotClick(view);
    }

    @Override
    public boolean onLongClick(View view) {
        mProjectorPresenter.onBtnLongClick(view);
        return true;
    }
}
