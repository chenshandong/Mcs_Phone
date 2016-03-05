package com.yzd.android.mcs_phone.other;

import android.support.v4.view.ViewPager;
import android.view.View;

import com.nineoldandroids.view.ViewHelper;
import com.yzd.android.mcs_phone.R;

public class CrossfadePageTransformer implements ViewPager.PageTransformer {

        @Override
        public void transformPage(View page, float position) {
            int pageWidth = page.getWidth();
 
            View image = page.findViewById(R.id.iv_show);
            View text_head= page.findViewById(R.id.tv_head);
            View text_content = page.findViewById(R.id.tv_content);
            View backgroundView = page.findViewById(R.id.background);

            if(0 < position && position < 1){
                ViewHelper.setTranslationX(page,pageWidth * -position);
            }
            if(-1 < position && position < 0){
                ViewHelper.setTranslationX(page,pageWidth * -position);
            }
 
            if(position <= -1.0f || position >= 1.0f) {
            } else if( position == 0.0f ) {
            } else {
                if(backgroundView != null) {
                    ViewHelper.setAlpha(backgroundView,1.0f - Math.abs(position));

                }
 
                if (text_head != null) {
                    ViewHelper.setTranslationX(text_head,pageWidth * position);
                    ViewHelper.setAlpha(text_head,1.0f - Math.abs(position));
                }
                
                if (text_content != null) {
                    ViewHelper.setTranslationX(text_content,pageWidth * position);
                    ViewHelper.setAlpha(text_content,1.0f - Math.abs(position));
                }

                if (backgroundView != null) {
                    ViewHelper.setTranslationX(text_content,(float)(pageWidth/2 * position));
                    ViewHelper.setAlpha(text_content,1.0f - Math.abs(position));
                }

                if (image != null) {
                    ViewHelper.setTranslationX(text_content,(float)(pageWidth/2 * position));
                    ViewHelper.setAlpha(text_content,1.0f - Math.abs(position));
                }

            }
        }
    }