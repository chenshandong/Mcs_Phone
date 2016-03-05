
package com.yzd.android.mcs_phone.interator;

import android.content.Context;
import android.view.animation.Animation;

public interface ISplashInteractor {

    int getBackgroundImageResID();

    String getCopyright(Context context);

    String getVersionName(Context context);

    Animation getBackgroundImageAnimation(Context context);


}
