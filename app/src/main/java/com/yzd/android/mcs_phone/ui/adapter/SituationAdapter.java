package com.yzd.android.mcs_phone.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.obsessive.library.listener.IRecyclerItemClickListener;
import com.github.obsessive.library.utils.SPUtils;
import com.yzd.android.mcs_phone.R;
import com.yzd.android.mcs_phone.interator.impl.SituationInteratorImpl;
import com.yzd.android.mcs_phone.ui.adapter.viewholder.SituationViewHolder;

/**
 * Created by Administrator on 2015/10/14.
 */
public class SituationAdapter extends RecyclerView.Adapter<SituationViewHolder> implements IRecyclerItemClickListener {

    private Context mContext;

    private int[] headDefaultId = {
            R.mipmap.save_default,
            R.mipmap.meet_default,
            R.mipmap.projector_default,
            R.mipmap.show_default,
            R.mipmap.power_default
            };

    private int[] headHighLight = {
            R.mipmap.save_highlight,
            R.mipmap.meet_highlight,
            R.mipmap.projector_highlight,
            R.mipmap.show_highlight,
            R.mipmap.power_hightlight
    };
    private int length = 4;
    private int mPressPosition = -1;
    private final SituationInteratorImpl mSituationInterator;

    public SituationAdapter(Context context, SituationInteratorImpl situationInterator) {
        mContext = context;
        mSituationInterator = situationInterator;
    }



    @Override
    public SituationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_situation, parent, false);
        SituationViewHolder situationViewHolder = new SituationViewHolder(mContext, view);
        situationViewHolder.setOnRecyclerItemClikListener(this);
        return situationViewHolder;
    }

    @Override
    public void onBindViewHolder(SituationViewHolder holder, int position) {

        holder.runEnterAnimation(holder.getView(), position, true, length);

        holder.setTitle(mContext.getResources().getStringArray(R.array.situation_title_list)[position]);
        holder.setSubTitle(mContext.getResources().getStringArray(R.array.situation_subtitle_list)[position]);

        if (position == mPressPosition) {
            holder.setTextColor(mContext.getResources().getIntArray(R.array.situation_list_color)[position]);
            holder.setHeadImage(headHighLight[position]);
        } else {
            holder.setTextColor(mContext.getResources().getColor(R.color.text_color));
            holder.setHeadImage(headDefaultId[position]);
        }

    }

    @Override
    public int getItemCount() {
        return length;
    }


    @Override
    public void itemOnClick(View view, int position) {
        mPressPosition = position;
        SPUtils.put(mContext, "NOW_SITUATION", position);

        this.notifyDataSetChanged();
        String now_situation = mSituationInterator.getSituationOrder(mContext,
                position,
                (int) SPUtils.get(mContext, "NOW_MODE", 0));
        mSituationInterator.sendOrder(mContext,now_situation);
    }

    public void setPressPosition(int position) {
        mPressPosition = position;
        SPUtils.put(mContext, "NOW_SITUATION", position);
        this.notifyDataSetChanged();
    }
}
