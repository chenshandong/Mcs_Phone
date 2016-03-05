package com.yzd.android.mcs_phone.ui.adapter.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.github.obsessive.library.listener.IRecyclerItemClickListener;
import com.yzd.android.mcs_phone.R;

/**
 * Created by Administrator on 2015/10/15.
 */
public class CurtainViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private  TextView mCurtainName;
    private Context mContext;
    private View mView;

    private IRecyclerItemClickListener mRecyclerItemClickListener = null;
    public void setOnCurtainClickListener(IRecyclerItemClickListener recyclerItemClickListener) {
        mRecyclerItemClickListener = recyclerItemClickListener;
    }

    public CurtainViewHolder(Context context, View itemView) {
        super(itemView);
        mContext = context;
        mView = itemView;
        mCurtainName = (TextView) itemView.findViewById(R.id.tv_name);
        itemView.findViewById(R.id.btn_open).setOnClickListener(this);
        itemView.findViewById(R.id.btn_pause).setOnClickListener(this);
        itemView.findViewById(R.id.btn_close).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        mRecyclerItemClickListener.itemOnClick(view, getPosition());
    }

    public void setCurtainName(String name) {
        mCurtainName.setText(name);
    }

    public View getView() {
        return mView;
    }
}
