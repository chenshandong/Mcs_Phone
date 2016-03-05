package com.yzd.android.mcs_phone.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.obsessive.library.listener.IRecyclerItemClickListener;
import com.yzd.android.mcs_phone.R;
import com.yzd.android.mcs_phone.bean.other.NavigationEntity;
import com.yzd.android.mcs_phone.ui.adapter.viewholder.NavigationViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2015/10/15.
 */
public class NavigationAdapter extends RecyclerView.Adapter<NavigationViewHolder> implements IRecyclerItemClickListener {

    private List<NavigationEntity> mNavigationList;
    private Context mContext;
    private int mPressPosition = 0;

    private IRecyclerItemClickListener mRecyclerItemClickListener = null;

    public NavigationAdapter(Context context, List<NavigationEntity> navigationList) {
        mContext = context;
        mNavigationList = navigationList;
    }

    public void setOnRecyclerViewItemClickListener(IRecyclerItemClickListener recyclerViewItemClickListener) {
        mRecyclerItemClickListener = recyclerViewItemClickListener;
    }

    @Override
    public NavigationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item2_navigation, parent, false);
        NavigationViewHolder navigationViewHolder = new NavigationViewHolder(mContext, view);
        navigationViewHolder.setOnRecyclerItemClikListener(this);
        return navigationViewHolder;
    }

    public NavigationEntity getItem(int position) {
        return mNavigationList.get(position);
    }

    @Override
    public void onBindViewHolder(NavigationViewHolder holder, int position) {

        holder.setTitle(mNavigationList.get(position).getName());
        holder.setSubTitle(mNavigationList.get(position).getSubString());
        holder.setHeadImage(mNavigationList.get(position).getIconResId());

        if (position == mPressPosition) {
            holder.setTextColor(mContext.getResources().getIntArray(R.array.navigation_list_color)[position]);
        } else {
            holder.setTextColor(mContext.getResources().getColor(R.color.navigation_default_color));
        }
    }

    @Override
    public int getItemCount() {
        return mNavigationList == null ? 0 : mNavigationList.size();
    }

    public int getPressPosition() {
        return mPressPosition;
    }

    @Override
    public void itemOnClick(View view, int position) {
        mPressPosition = position;
        this.notifyDataSetChanged();
        mRecyclerItemClickListener.itemOnClick(view, position);
    }
}
