package com.yzd.android.mcs_phone.ui.fragment;


import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.afollestad.materialdialogs.AlertDialogWrapper;
import com.yzd.android.mcs_phone.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SetIPDialogFragment extends DialogFragment {

    private EditText mIpEdt;
    private EditText mPortEdt;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_set_ipdialog, null);
        mIpEdt = (EditText) view.findViewById(R.id.ipEdt);
        mPortEdt = (EditText) view.findViewById(R.id.portEdt);
        return new AlertDialogWrapper.Builder(getActivity())
                .setView(view)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String ip = mIpEdt.getText().toString().trim();
                        String port = mPortEdt.getText().toString().trim();
                        if (ip.equals("") || null == ip) {

                        }

                        if (port.equals("") || null == port) {

                        }
                    }
                })
                .create();
    }


}
