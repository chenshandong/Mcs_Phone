package com.yzd.android.mcs_phone.presenter.impl;

import android.content.Context;
import android.view.animation.AnimationUtils;
import android.widget.ViewFlipper;

import com.github.obsessive.library.listener.BaseHttpListener;
import com.github.obsessive.library.utils.SPUtils;
import com.yzd.android.mcs_phone.R;
import com.yzd.android.mcs_phone.api.Constants;
import com.yzd.android.mcs_phone.bean.database.BoardRoom;
import com.yzd.android.mcs_phone.bean.database.BoardRoomMachineCode;
import com.yzd.android.mcs_phone.interator.impl.SplashInteratorImpl;
import com.yzd.android.mcs_phone.model.impl.DeleteDataBaseImpl;
import com.yzd.android.mcs_phone.model.impl.SaveDataBaseImpl;
import com.yzd.android.mcs_phone.presenter.Presenter;
import com.yzd.android.mcs_phone.udp.InitData.CRC16;
import com.yzd.android.mcs_phone.utils.NdkUtils;
import com.yzd.android.mcs_phone.utils.UriHelper;
import com.yzd.android.mcs_phone.view.SplashView;
import com.yzd.android.mcs_phone.widgets.LoginCommentButton;
import com.yzd.android.mcs_phone.widgets.OkCommentButton;

import dmax.dialog.SpotsDialog;

/**
 * Created by Administrator on 2015/10/9.
 */
public class SplashPresenterImpl implements Presenter, BaseHttpListener<Object>{

    private Context mContext;
    private SplashView mSplashView;
    private SplashInteratorImpl mSplashInteractor;
    private SpotsDialog mSpotsDialog;
    private int mHttpNum;

    public SplashPresenterImpl(Context context, SplashView splashView) {
        if (null == splashView) {
            throw new IllegalArgumentException("Constructor's parameters must not be Null");
        }

        mContext = context;
        mSplashView = splashView;
        mSplashInteractor = new SplashInteratorImpl(mContext, this);
    }

    @Override
    public void initialized() {
        mSplashView.initializeUmengConfig();
        mSplashView.initializeViews(mSplashInteractor.getVersionName(mContext),
                mSplashInteractor.getCopyright(mContext),
                mSplashInteractor.getBackgroundImageResID());
    }


    public void login(LoginCommentButton btnSendComment, boolean debugMode) {
        if (!debugMode) {
            String userName = mSplashView.getUserName();
            String passWord = mSplashView.getPassWord();
            if (userName == null || userName.equalsIgnoreCase("")
                    || passWord == null || passWord.equalsIgnoreCase("")) {
                btnSendComment.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.shake_error));
                return;
            }

            if (!(userName.equalsIgnoreCase("admin") && passWord.equalsIgnoreCase("88888888"))) {
                btnSendComment.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.shake_error));
                return;
            }
        }

        mSplashInteractor.getBaseData(UriHelper.getInstance().getBoardRoomUrl(mContext, Constants.URL.INIT_DATA));
        mSplashInteractor.getBaseData(UriHelper.getInstance().getBoardRoomUrl(mContext, Constants.URL.INIT_CODE));
        mSpotsDialog = new SpotsDialog(mContext, R.style.login_custom);
        mSpotsDialog.show();

    }

    public void setIP(OkCommentButton button, ViewFlipper viewFlipper) {
        String ip = mSplashView.getIP();
        String port = mSplashView.getPort();
        if (ip == null || ip.equalsIgnoreCase("")
                || port == null || port.equalsIgnoreCase("")) {
            button.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.shake_error));
            return;
        }

        SPUtils.put(mContext, Constants.SERVERIP, ip);
        SPUtils.put(mContext, Constants.SERVERPORT, port);

        viewFlipper.setInAnimation(mContext, R.anim.right_in);
        viewFlipper.setOutAnimation(mContext, R.anim.left_out);
        viewFlipper.showPrevious();
    }

    @Override
    public void onSuccess(int event_tag, Object data) {
        if (event_tag == 0) {
            int version = (int) SPUtils.get(mContext, Constants.SERVICES_DATA_VERSION, 0);
            BoardRoom boardRoom = (BoardRoom) data;
            if (boardRoom != null && boardRoom.getVersion() != version) {
                SPUtils.put(mContext, Constants.SERVICES_DATA_VERSION, version);
                DeleteDataBaseImpl.getInstances().deleteAllBoardRoom();
                SaveDataBaseImpl.getInstances().saveBoardRoom(boardRoom);
            }

        } else if (event_tag == 1) {
            DeleteDataBaseImpl.getInstances().deleteMachineCode();
            SaveDataBaseImpl.getInstances().saveMachineCode((BoardRoomMachineCode)data);
        }

        mHttpNum ++;
        if (mHttpNum == 2) {
            mSpotsDialog.dismiss();
            mSplashView.navigateToHomePage();
        }

    }

    @Override
    public void onError(String msg) {
        mHttpNum ++;
        if (mHttpNum == 2) {
            mSpotsDialog.dismiss();
            mSplashView.navigateToHomePage();
        }
    }

    @Override
    public void onException(String msg) {

    }
}
