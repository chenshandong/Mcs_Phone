package com.yzd.android.mcs_phone.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.ViewAnimator;

import com.yzd.android.mcs_phone.R;


/**
 */
public class Button extends ViewAnimator implements View.OnClickListener {
    public static final int STATE_SEND = 0;
    public static final int STATE_DONE = 1;

    private static final long RESET_STATE_DELAY_MILLIS = 2000;

    private int currentState;

    private int backGroundColor;// 按钮的背景
    private String textContent;// 按钮的文本
    private String subTextContent;// 按钮的富文本
    private final static int BACKGROUND_COLOR_DEFAULT = 0XFFFFFF;

    private OnSendClickListener onSendClickListener;

    private Runnable revertStateRunnable = new Runnable() {
        @Override
        public void run() {
            setCurrentState(STATE_SEND);
        }
    };
    private OnClickListener onClickListener;
    private TextView tvSend;
    private TextView tvDone;

    public Button(Context context) {
        super(context);
    }

    public Button(Context context, AttributeSet attrs) {
        super(context, attrs);

        if (attrs != null) {

            initAttr(context, attrs);
        }

        init();
    }

    private void initAttr(Context context, AttributeSet attrs) {

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.SendButton);
//        backGroundColor = ta.getColor(R.attr.sb_background, BACKGROUND_COLOR_DEFAULT);
//        textContent = ta.getString(R.attr.sb_main_text);
//        subTextContent = ta.getString(R.attr.sb_sub_text);

    }

    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_ok_comment_button, this, true);
        tvSend = (TextView) view.findViewById(R.id.tvSend);
        tvDone = (TextView) view.findViewById(R.id.tvDone);

//        setTvSendText(textContent);
//        setTvSendSubText(subTextContent);
    }

    /**
     * 设置按钮文本
     * @param msg
     */
    public void setTvSendText(String msg) {
        if (msg != null || !msg.equals("")) {

            tvSend.setText(msg);
        } else {
            tvSend.setText("确定");
        }
    }

    /**
     * 设置按钮副文本
     * @param msg
     */
    public void setTvSendSubText(String msg) {
        if (msg != null) {

            tvDone.setText(msg);
        } else {
            tvDone.setText("");
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        currentState = STATE_SEND;
        super.setOnClickListener(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        removeCallbacks(revertStateRunnable);
        super.onDetachedFromWindow();
    }

    public void setCurrentState(int state) {
        if (state == currentState) {
            return;
        }

        currentState = state;
        if (state == STATE_DONE) {
            setEnabled(false);
            postDelayed(revertStateRunnable, RESET_STATE_DELAY_MILLIS);
            setInAnimation(getContext(), R.anim.slide_in_done);
            setOutAnimation(getContext(), R.anim.slide_out_send);
        } else if (state == STATE_SEND) {
            setEnabled(true);
            setInAnimation(getContext(), R.anim.slide_in_send);
            setOutAnimation(getContext(), R.anim.slide_out_done);
        }
        showNext();
    }

    @Override
    public void onClick(View v) {
        if (onSendClickListener != null) {
            onSendClickListener.onSendClickListener(this);
        }

    }

    public void setOnSendClickListener(OnSendClickListener onSendClickListener) {
        this.onSendClickListener = onSendClickListener;
    }

    @Override
    public void setOnClickListener(OnClickListener l) {
        //Do nothing, you have you own onClickListener implementation (OnSendClickListener)
        onClickListener = l;
    }

    public interface OnSendClickListener {
        public void onSendClickListener(View v);
    }
}