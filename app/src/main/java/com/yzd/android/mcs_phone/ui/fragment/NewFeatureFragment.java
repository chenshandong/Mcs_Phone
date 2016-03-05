package com.yzd.android.mcs_phone.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.obsessive.library.utils.SPUtils;
import com.yzd.android.mcs_phone.R;
import com.yzd.android.mcs_phone.ui.activity.SetActivity;
import com.yzd.android.mcs_phone.ui.activity.SplashActivity;
import com.yzd.android.mcs_phone.widgets.Button;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewFeatureFragment extends Fragment implements Button.OnSendClickListener {

    private int[] imageId = {R.mipmap.pic1, R.mipmap.pic2, R.mipmap.pic3, R.mipmap.pic4};
    private String[] title = {"设置设备IP和端口号", "远程控制多种智能设备", "房间选择", "会议控制系统"};
    private String[] subTitle = {"初次登陆需设置IP和端口号", "通过移动端即可远程控制灯光、空调、电视、投影仪各种设备", "控制不同房间无需更换多台设备", "享受指尖上的舞蹈"};
    private int position;

    public NewFeatureFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_nre_feature, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageView image = (ImageView) view.findViewById(R.id.iv_show);
        TextView tvHead = (TextView) view.findViewById(R.id.tv_head);
        TextView tvContent = (TextView) view.findViewById(R.id.tv_content);
        Button okBtn = (Button) view.findViewById(R.id.btn_ok);
        okBtn.setOnSendClickListener(this);
        position = getArguments().getInt("position");
        tvHead.setText(title[position]);
        tvContent.setText(subTitle[position]);
        image.setBackground(getResources().getDrawable(imageId[position]));
        if (position == 3 || position == 0) {
            if (position == 0) {
                okBtn.setTvSendText("立即设置");
            } else {
                okBtn.setTvSendText("立即进入");
            }
            okBtn.setVisibility(View.VISIBLE);
        } else {
            okBtn.setVisibility(View.INVISIBLE);
        }
    }


    @Override
    public void onSendClickListener(View v) {
        if (position == 0) {
            Intent intent = new Intent(getActivity(), SetActivity.class);
            intent.setAction("newFeatureFragment");
            startActivityForResult(intent, 0);
        } else if (position == 3) {
            Intent intent = new Intent(getActivity(), SplashActivity.class);
            startActivity(intent);
            SPUtils.put(getActivity(), "isNewFeature", false);
            getActivity().finish();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 0 && data != null) {

            boolean setSuccess = data.getBooleanExtra("setSuccess", false);
            if (setSuccess) {
                SPUtils.put(getActivity(), "setSuccess", true);
            }
        }
    }
}
