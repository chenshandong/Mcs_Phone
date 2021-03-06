package com.yzd.android.mcs_phone.ui.adapter.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.obsessive.library.listener.IRecyclerItemClickListener;
import com.yzd.android.mcs_phone.R;
import com.yzd.android.mcs_phone.utils.ScreenUtils;


/**
 * Created by Administrator on 2015/10/14.
 */
public class SituationViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private Context mContext;
    private ImageView mHeadImage;
    private TextView mTitle;
    private TextView mSubTitle;
    private View mView;
    private int lastAnimatedPosition = -1;

    private IRecyclerItemClickListener mRecyclerItemClickListener = null;
    public void setOnRecyclerItemClikListener(IRecyclerItemClickListener recyclerItemClikListener) {
        mRecyclerItemClickListener = recyclerItemClikListener;
    }

    public SituationViewHolder(Context context,View itemView) {
        super(itemView);
        mView = itemView;
        mContext = context;
        mHeadImage = (ImageView) itemView.findViewById(R.id.head);
        mTitle = (TextView) itemView.findViewById(R.id.title);
        mSubTitle = (TextView) itemView.findViewById(R.id.subtitle);
        itemView.findViewById(R.id.more).setOnClickListener(this);
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

    public void runEnterAnimation(View view, int position, boolean animateItems, int ANIMATED_ITEMS_COUNT) {
        if (!animateItems || position >= ANIMATED_ITEMS_COUNT) {
            return;
        }

        if (position > lastAnimatedPosition) {
            lastAnimatedPosition = position;
            view.setTranslationY(ScreenUtils.getScreenHeight(mContext));
            view.animate()
                    .translationY(0)
                    .setStartDelay(200 * position)
                    .setInterpolator(new DecelerateInterpolator(3.f))
                    .setDuration(900)
                    .start();
        }
    }
}
