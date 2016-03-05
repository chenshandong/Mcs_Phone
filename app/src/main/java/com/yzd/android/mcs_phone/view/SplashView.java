
package com.yzd.android.mcs_phone.view;

import android.view.animation.Animation;

public interface SplashView {

    void animateBackgroundImage(Animation animation);

    void initializeViews(String versionName, String copyright, int backgroundResId);

    void initializeUmengConfig();

    void navigateToHomePage();

    String getUserName();

    String getPassWord();

    String getIP();

    String getPort();

}
