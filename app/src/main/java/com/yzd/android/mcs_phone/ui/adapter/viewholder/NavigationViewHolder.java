package com.yzd.android.mcs_phone.ui.adapter.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.obsessive.library.listener.IRecyclerItemClickListener;
import com.yzd.android.mcs_phone.R;
import com.yzd.android.mcs_phone.bean.other.NavigationEntity;

/**
 * Created by Administrator on 2015/10/15.
 */
public class NavigationViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private ImageView mHeadImage;
    private  TextView mTitle;
    private  TextView mSubTitle;
    private View mView;

    public NavigationViewHolder(Context context, View itemView) {
        super(itemView);
        mView = itemView;
        mHeadImage = (ImageView) itemView.findViewById(R.id.head);
        mTitle = (TextView) itemView.findViewById(R.id.title);
        mSubTitle = (TextView) itemView.findViewById(R.id.subtitle);
        itemView.setOnClickListener(this);
    }

    private IRecyclerItemClickListener mRecyclerItemClickListener = null;
    public void setOnRecyclerItemClikListener(IRecyclerItemClickListener recyclerItemClikListener) {
        mRecyclerItemClickListener = recyclerItemClikListener;
    }

    public void setTitle(String string) {
        this.mTitle.setText(string);
    }

    public void setSubTitle(String string) {
        this.mSubTitle.setText(string);
    }

    public void setHeadImage(int id) {
        this.mHeadImage.setBackgroundResource(id);
    }

    public void setTextColor(int colorId){
        this.mTitle.setTextColor(colorId);
        this.mSubTitle.setTextColor(colorId);
    }

    public View getView() {
        return mView;
    }


    @Override
    public void onClick(View view) {
        mRecyclerItemClickListener.itemOnClick(view, getPosition());
    }
}
