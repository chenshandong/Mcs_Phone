package com.yzd.android.mcs_phone.presenter.impl;

import android.content.Context;
import android.os.AsyncTask;

import com.github.obsessive.library.base.BaseLazyFragment;
import com.github.obsessive.library.eventbus.EventCenter;
import com.github.obsessive.library.utils.SPUtils;
import com.mingle.entity.MenuEntity;
import com.mingle.sweetpick.DimEffect;
import com.mingle.sweetpick.RecyclerViewDelegate;
import com.mingle.sweetpick.SweetSheet;
import com.yzd.android.mcs_phone.api.Constants;
import com.yzd.android.mcs_phone.api.EvenBusConstants;
import com.yzd.android.mcs_phone.bean.database.BoardRoomMachineCode;
import com.yzd.android.mcs_phone.bean.database.MachineCode;
import com.yzd.android.mcs_phone.interator.IndexInteractor;
import com.yzd.android.mcs_phone.interator.impl.IndexInteratorImpl;
import com.yzd.android.mcs_phone.model.impl.SelectDataBaseImpl;
import com.yzd.android.mcs_phone.presenter.IIndextPresenter;
import com.yzd.android.mcs_phone.ui.adapter.VPFragmentAdapter;
import com.yzd.android.mcs_phone.view.IndextView;
import com.yzd.android.mcs_phone.widgets.RevealBackgroundView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2015/10/10.
 */
public class IndexPresenterImpl implements IIndextPresenter, SweetSheet.OnMenuItemClickListener, RevealBackgroundView.OnStateChangeListener {

    private Context mContext;
    private IndextView mIndexView;
    private IndexInteractor mIndextInteratorImpl;
    private LoadDataAsyncTask loadDataAsyncTask;
    private List<BaseLazyFragment> mPagerFragments;

    public IndexPresenterImpl(Context context, IndextView indexView) {
        if (null == indexView) {
            throw new IllegalArgumentException("Constructor's parameters must not be Null");
        }

        mContext = context;
        mIndexView = indexView;
        mIndextInteratorImpl = new IndexInteratorImpl(mContext);
        loadDataAsyncTask = new LoadDataAsyncTask();
    }

    @Override
    public void initialized() {
        mPagerFragments = mIndextInteratorImpl.getPagerFragments((int) SPUtils.get(mContext, Constants.SELECTORROOM, 0));
        mIndexView.initializeViews(mPagerFragments, mIndextInteratorImpl.getNavigationListData(mContext));
        loadDataAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    public void initMenu() {

    }

    @Override
    public SweetSheet initSweetSheet() {
        SweetSheet sweetSheet = new SweetSheet(mIndexView.getRelativeLayout());

        BoardRoomMachineCode boardRoomMachineCode = SelectDataBaseImpl.getInstances().selectAllMachineCode();
        List<MenuEntity> list = new ArrayList<>();

        if (boardRoomMachineCode != null) {
            List<MachineCode> machineCode = boardRoomMachineCode.getMachineCode();
            //设置数据源 (数据源支持设置 list 数组,也支持从menu 资源中获取)

            if (machineCode != null && machineCode.size() > 0) {
                for (int i = 0; i < machineCode.size(); i++) {
                    MenuEntity menuEntity = new MenuEntity();
                    menuEntity.title = machineCode.get(i).getBoardRoomName();
                    list.add(menuEntity);
                }
            } else {
                list.addAll(defaultData());
            }
        } else {
            list.addAll(defaultData());
        }


        sweetSheet.setMenuList(list);
        //根据设置不同的 Delegate 来显示不同的风格.
        sweetSheet.setDelegate(new RecyclerViewDelegate(true));
        //根据设置不同Effect来设置背景效果:BlurEffect 模糊效果.DimEffect 变暗效果,NoneEffect 没有效果.
        sweetSheet.setBackgroundEffect(new DimEffect(1));
        //设置菜单点击事件
        sweetSheet.setOnMenuItemClickListener(this);
        return sweetSheet;
    }

    private List<MenuEntity> defaultData() {
        List<MenuEntity> list = new ArrayList<>();
        MenuEntity menuEntity = new MenuEntity();
        menuEntity.title = "天翔园会议室1";
        MenuEntity menuEntity1 = new MenuEntity();
        menuEntity1.title = "天翔园会议室2";
        MenuEntity menuEntity2 = new MenuEntity();
        menuEntity2.title = "天翔园会议室3";
        MenuEntity menuEntity3 = new MenuEntity();
        menuEntity3.title = "天翔园会议室4";
        MenuEntity menuEntity4 = new MenuEntity();
        menuEntity4.title = "天翔园会议室5";
        list.add(menuEntity);
        list.add(menuEntity1);
        list.add(menuEntity2);
        list.add(menuEntity3);
        list.add(menuEntity4);
        return list;
    }

    @Override
    public void onSweetSheetClick() {
        mIndexView.showSweetSheet();
    }

    @Override
    public void evenComming(EventCenter<String> eventCenter) {
    }

    @Override
    public boolean onItemClick(int position, MenuEntity menuEntity) {
        SPUtils.put(mContext, Constants.SELECTORROOM, position);
        mIndexView.killNoAnimation();
        return true;
    }

    @Override
    public void onStateChange(int state) {

    }


    private class LoadDataAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Thread.sleep(1900);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            VPFragmentAdapter vpFragmentAdapter = mIndexView.getVPFragmentAdapter();
            vpFragmentAdapter.setFragmentList(mPagerFragments);
            vpFragmentAdapter.notifyDataSetChanged();

            UdpSendOrder.getInstances().getAllEquipStatus(mContext);

        }
    }
}
