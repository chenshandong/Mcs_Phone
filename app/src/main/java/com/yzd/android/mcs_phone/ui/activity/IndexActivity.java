package com.yzd.android.mcs_phone.ui.activity;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.github.obsessive.library.base.BaseLazyFragment;
import com.github.obsessive.library.base.BaseWebActivity;
import com.github.obsessive.library.eventbus.EventCenter;
import com.github.obsessive.library.listener.IRecyclerItemClickListener;
import com.github.obsessive.library.netstatus.NetUtils;
import com.github.obsessive.library.widgets.XViewPager;
import com.mingle.sweetpick.SweetSheet;
import com.yzd.android.mcs_phone.R;
import com.yzd.android.mcs_phone.api.McsApplication;
import com.yzd.android.mcs_phone.base.activity.BaseActivity;
import com.yzd.android.mcs_phone.bean.other.NavigationEntity;
import com.yzd.android.mcs_phone.presenter.impl.IndexPresenterImpl;
import com.yzd.android.mcs_phone.services.ReceiverService;
import com.yzd.android.mcs_phone.ui.adapter.NavigationAdapter;
import com.yzd.android.mcs_phone.ui.adapter.VPFragmentAdapter;
import com.yzd.android.mcs_phone.view.IndextView;
import com.yzd.android.mcs_phone.widgets.RevealBackgroundView;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

public class IndexActivity extends BaseActivity implements IndextView{

    @InjectView(R.id.home_container)
    XViewPager mViewPager;

    @InjectView(R.id.home_navigation_list)
    RecyclerView mRecyclerView;

    @InjectView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @InjectView(R.id.rl)
    RelativeLayout rl;

    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private long DOUBLE_CLICK_TIME = 0L;
    private int mCurrentMenuCheckedPos = 0;
    private LinearLayoutManager mLayoutManager;
    private NavigationAdapter mNavigationAdapter;
    private SweetSheet mSweetSheet;
    private IndexPresenterImpl mIndexPresenter;
    private VPFragmentAdapter mVpFragmentAdapter;
    private Intent intent;
    private ServiceConnection conn;
    private ReceiverService mReceiveService;

    @Override
    protected boolean isApplyKitKatTranslucency() {
        return true;
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_index;
    }

    @Override
    protected void onEventComming(EventCenter eventCenter) {
    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    @Override
    protected void initViewsAndEvents() {

        mIndexPresenter = new IndexPresenterImpl(this, this);
        mIndexPresenter.initialized();

    }

    @Override
    protected void onResume() {
        super.onResume();
        intent = new Intent(this, ReceiverService.class);
        startService(intent);
        conn = new ReceiveConnection();
        bindService(intent, conn, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mReceiveService.stopTask();
        unbindService(conn);
    }

    @Override
    protected void onNetworkConnected(NetUtils.NetType type) {

    }

    @Override
    protected void onNetworkDisConnected() {

    }

    @Override
    protected boolean isApplyStatusBarTranslucency() {
        return true;
    }

    @Override
    protected boolean isBindEventBusHere() {
        return false;
    }

    @Override
    protected boolean toggleOverridePendingTransition() {
        return false;
    }

    @Override
    protected TransitionMode getOverridePendingTransitionMode() {
        return null;
    }

    @Override
    public void initializeViews(List<BaseLazyFragment> fragments, List<NavigationEntity> navigationList) {
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar,0, 0) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                if (null != mNavigationAdapter) {
                    setTitle(mNavigationAdapter.getItem(mCurrentMenuCheckedPos).getName());
                }
            }
        };

        mActionBarDrawerToggle.syncState();
        mActionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);

        if (null != fragments && !fragments.isEmpty()) {
            mViewPager.setEnableScroll(false);
            mViewPager.setOffscreenPageLimit(fragments.size());
            List<BaseLazyFragment> fragmentList = new ArrayList<>();
            fragmentList.add(fragments.get(0));
            mVpFragmentAdapter = new VPFragmentAdapter(getSupportFragmentManager(), fragmentList);
            mViewPager.setAdapter(mVpFragmentAdapter);
        }

        //创建默认的线性LayoutManager
        mLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mLayoutManager);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        mRecyclerView.setHasFixedSize(true);

        mNavigationAdapter = new NavigationAdapter(this, navigationList);
        mRecyclerView.setAdapter(mNavigationAdapter);
        setTitle(mNavigationAdapter.getItem(mCurrentMenuCheckedPos).getName());

        mNavigationAdapter.setOnRecyclerViewItemClickListener(new IRecyclerItemClickListener() {
            @Override
            public void itemOnClick(View view, int position) {
            mCurrentMenuCheckedPos = position;
            mDrawerLayout.closeDrawer(Gravity.LEFT);
            mViewPager.setCurrentItem(mCurrentMenuCheckedPos, false);
            }
        });

        // 第一次启动应用，打开drawer
        if (getMcsApplication().isFirstLaunch()) {
            mDrawerLayout.openDrawer(Gravity.LEFT);
            getMcsApplication().setFirstLaunch(false);
        }
    }

    @Override
    public void showReveal() {

    }

    @Override
    public void hideReveal() {

    }

    @Override
    public void showSweetSheet() {
        if (null != mSweetSheet) {
            mSweetSheet.toggle();
        }
    }

    @Override
    public void dismissSweetSheet() {
        mSweetSheet.dismiss();
    }

    //返回sweetsheet布局
    @Override
    public RelativeLayout getRelativeLayout() {
        return rl;
    }

    @Override
    public RevealBackgroundView getRevealBackgroundView() {
        return null;
    }

    @Override
    public void killNoAnimation() {
        readyGoNoAnimation(IndexActivity.class);
    }

    @Override
    public VPFragmentAdapter getVPFragmentAdapter() {
        return mVpFragmentAdapter;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_MENU) {
            if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
                mDrawerLayout.closeDrawer(Gravity.LEFT);
            } else {
                mDrawerLayout.openDrawer(Gravity.LEFT);
            }
            return true;
        } else if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
                mDrawerLayout.closeDrawer(Gravity.LEFT);


            } else if (mSweetSheet != null && mSweetSheet.isShow()) {
                dismissSweetSheet();
            } else {
                if ((System.currentTimeMillis() - DOUBLE_CLICK_TIME) > 2000) {
                    showToast("再按一次退出!");
                    DOUBLE_CLICK_TIME = System.currentTimeMillis();
                } else {
                    McsApplication application = (McsApplication) getApplication();
                    application.exitApp();
                }
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if (mActionBarDrawerToggle != null) {
            mActionBarDrawerToggle.syncState();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (mActionBarDrawerToggle != null) {
            mActionBarDrawerToggle.onConfigurationChanged(newConfig);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_index, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mActionBarDrawerToggle != null && mActionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        switch (item.getItemId()) {
            case R.id.action_feedback:

                if (mSweetSheet == null) {
                    mSweetSheet = mIndexPresenter.initSweetSheet();
                }
                mIndexPresenter.onSweetSheetClick();

                return true;
            case R.id.b:
                readyGo(SetActivity.class);
                return true;
            case R.id.a:
                Bundle extras = new Bundle();
                extras.putString(BaseWebActivity.BUNDLE_KEY_TITLE, "");
                extras.putBoolean(BaseWebActivity.BUNDLE_KEY_SHOW_BOTTOM_BAR, true);
                extras.putString(BaseWebActivity.BUNDLE_KEY_URL, "http://www.txpark.com");

                readyGo(BaseWebActivity.class, extras);
                return true;

        }

        return super.onOptionsItemSelected(item);
    }

    private class ReceiveConnection implements ServiceConnection {


        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            ReceiverService.MyBinder myBinder = (ReceiverService.MyBinder) iBinder;
            mReceiveService = myBinder.getReceiveService();
            mReceiveService.startTask();
            Log.d("------------", "binderService");
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    }


}
