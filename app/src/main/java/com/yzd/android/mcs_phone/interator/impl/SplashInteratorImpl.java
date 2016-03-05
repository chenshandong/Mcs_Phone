package com.yzd.android.mcs_phone.interator.impl;

import android.content.Context;
import android.content.pm.PackageManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.yzd.android.mcs_phone.api.Constants;
import com.yzd.android.mcs_phone.bean.database.BoardRoomMachineCode;
import com.yzd.android.mcs_phone.interator.BaseHttpInterator;
import com.github.obsessive.library.listener.BaseHttpListener;
import com.github.obsessive.library.utils.volley.GsonRequest;
import com.github.obsessive.library.utils.volley.VolleyHelper;
import com.google.gson.reflect.TypeToken;
import com.yzd.android.mcs_phone.R;
import com.yzd.android.mcs_phone.bean.database.BoardRoom;
import com.yzd.android.mcs_phone.interator.ISplashInteractor;

import java.util.Calendar;

/**
 * Created by Administrator on 2015/10/9.
 */
public class SplashInteratorImpl implements ISplashInteractor, BaseHttpInterator {

    private BaseHttpListener<Object> boardRoomBaseHttpListener = null;
    private BaseHttpListener<Object> boardRoomMachineCodeBaseHttpListener = null;
    private Context mContext;
    private int MY_SOCKET_TIMEOUT_MS = 5000;

    public SplashInteratorImpl(Context context,
                               BaseHttpListener<Object> baseHttpListener) {
        mContext = context;
        this.boardRoomBaseHttpListener = baseHttpListener;
        this.boardRoomMachineCodeBaseHttpListener = baseHttpListener;
    }

    @Override
    public int getBackgroundImageResID() {
        int resId;
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(calendar.HOUR_OF_DAY);
        if (hour >= 6 && hour <= 12) {
            resId = R.mipmap.ic_launcher;
        } else if (hour > 12 && hour <= 18) {
            resId = R.mipmap.ic_launcher;
        } else {
            resId = R.mipmap.ic_launcher;
        }
        return resId;
    }

    @Override
    public String getCopyright(Context context) {
        return context.getResources().getString(R.string.splash_copyright);
    }

    @Override
    public String getVersionName(Context context) {
        String versionName = null;
        try {
            versionName = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }

    @Override
    public Animation getBackgroundImageAnimation(Context context) {
        return AnimationUtils.loadAnimation(context, R.anim.splash);
    }

    @Override
    public void getBaseData(final String requestURL) {

        VolleyHelper.getInstance().init(mContext);
        if (requestURL.contains(Constants.URL.INIT_DATA)) {
            GsonRequest<BoardRoom> boardRoomGsonRequest = new GsonRequest<BoardRoom>(requestURL,
                    null,
                    new TypeToken<BoardRoom>() {
                    }.getType(),
                    new Response.Listener<BoardRoom>() {
                        @Override
                        public void onResponse(BoardRoom response) {
                            boardRoomBaseHttpListener.onSuccess(0, response);
                    }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    boardRoomBaseHttpListener.onError(error.getMessage());
                }
            });
            boardRoomGsonRequest.setShouldCache(true);
            boardRoomGsonRequest.setRetryPolicy(new DefaultRetryPolicy(MY_SOCKET_TIMEOUT_MS, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            VolleyHelper.getInstance().getRequestQueue().add(boardRoomGsonRequest);
        } else if (requestURL.contains(Constants.URL.INIT_CODE)) {
            GsonRequest<BoardRoomMachineCode> boardRoomGsonRequest = new GsonRequest<BoardRoomMachineCode>(requestURL,
                    null,
                    new TypeToken<BoardRoomMachineCode>() {
                    }.getType(),
                    new Response.Listener<BoardRoomMachineCode>() {
                        @Override
                        public void onResponse(BoardRoomMachineCode response) {
                            boardRoomMachineCodeBaseHttpListener.onSuccess(1, response);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    boardRoomMachineCodeBaseHttpListener.onError(error.getMessage());
                }
            });
            boardRoomGsonRequest.setShouldCache(true);
            boardRoomGsonRequest.setRetryPolicy(new DefaultRetryPolicy(MY_SOCKET_TIMEOUT_MS, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            VolleyHelper.getInstance().getRequestQueue().add(boardRoomGsonRequest);
        }

    }
}
