
package com.yzd.android.mcs_phone.interator;

import android.content.Context;


import com.github.obsessive.library.base.BaseLazyFragment;
import com.yzd.android.mcs_phone.bean.other.NavigationEntity;

import java.util.List;


public interface IndexInteractor {

    List<BaseLazyFragment> getPagerFragments(int position);

    List<NavigationEntity> getNavigationListData(Context context);


}
