package com.yzd.android.mcs_phone.interator.impl;

import android.content.Context;

import com.github.obsessive.library.base.BaseLazyFragment;
import com.github.obsessive.library.utils.SPUtils;
import com.yzd.android.mcs_phone.R;
import com.yzd.android.mcs_phone.api.Constants;
import com.yzd.android.mcs_phone.bean.database.AirEntity;
import com.yzd.android.mcs_phone.bean.database.BoardRoomEntity;
import com.yzd.android.mcs_phone.bean.database.BoardRoomMachineCode;
import com.yzd.android.mcs_phone.bean.database.CurtainEntity;
import com.yzd.android.mcs_phone.bean.database.LightEntity;
import com.yzd.android.mcs_phone.bean.database.MachineCode;
import com.yzd.android.mcs_phone.bean.database.ProjectorEntity;
import com.yzd.android.mcs_phone.bean.database.TvEntity;
import com.yzd.android.mcs_phone.bean.other.NavigationEntity;
import com.yzd.android.mcs_phone.interator.IndexInteractor;
import com.yzd.android.mcs_phone.model.impl.SelectDataBaseImpl;
import com.yzd.android.mcs_phone.ui.fragment.AirContainerFragment;
import com.yzd.android.mcs_phone.ui.fragment.CurtainFragment;
import com.yzd.android.mcs_phone.ui.fragment.LightsFragment;
import com.yzd.android.mcs_phone.ui.fragment.MusicContainerFragment;
import com.yzd.android.mcs_phone.ui.fragment.ProjectorContainerFragment;
import com.yzd.android.mcs_phone.ui.fragment.SituationFragment;
import com.yzd.android.mcs_phone.ui.fragment.TVFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/10/10.
 */
public class IndexInteratorImpl implements IndexInteractor {

    private int[] navigationHeadArrays = {
            R.mipmap.navigation_situation_highlight,
            R.mipmap.navigation_light_highlight,
            R.mipmap.navigation_curtain_highlight,
            R.mipmap.navigation_air_highlight,
            R.mipmap.navigation_projector_highlight,
            R.mipmap.navigation_tv_highlight,
            R.mipmap.navigation_music_highlight
    };
    private Context mContext;
    private List<NavigationEntity> navigationEntities;

    public IndexInteratorImpl(Context context) {
        mContext = context;
    }

    @Override
    public List<BaseLazyFragment> getPagerFragments(int position) {
        BoardRoomMachineCode boardRoomMachineCode = SelectDataBaseImpl.getInstances().selectAllMachineCode();
        List<BaseLazyFragment> fragments = null;

        if (boardRoomMachineCode != null) {

            List<MachineCode> machineCode = boardRoomMachineCode.getMachineCode();
            if (machineCode != null && machineCode.size() > 0) {

                if (position > machineCode.size()) {

                    fragments = setMode(0, machineCode);
                    SPUtils.put(mContext, Constants.SELECTORROOM, 0);

                    SPUtils.put(mContext, Constants.IP, machineCode.get(0).getIp());
                    SPUtils.put(mContext, Constants.SENDPORT, Constants.DEFAULT_SENDPORT);
                } else {
                    fragments = setMode(position, machineCode);
                    SPUtils.put(mContext, Constants.SELECTORROOM, position);

                    SPUtils.put(mContext, Constants.IP, machineCode.get(position).getIp());
                    SPUtils.put(mContext, Constants.SENDPORT, Constants.DEFAULT_SENDPORT);
                }
            } else {
                fragments = defaultMode();
            }
        } else {
            fragments = defaultMode();
        }


        return fragments;
    }

    private List<BaseLazyFragment> setMode(int position, List<MachineCode> machineCode) {

        List<BaseLazyFragment> fragments = new ArrayList<>();
        navigationEntities = new ArrayList<>();

        BoardRoomEntity boardRoomEntity = SelectDataBaseImpl.getInstances().selectOneRoom(machineCode.get(position).getTypeId());
        String[] navigationArrays = mContext.getResources().getStringArray(R.array.navigation_list);
        String[] navigationSubArrays = mContext.getResources().getStringArray(R.array.navigation_subtitle_list);

        fragments.add(new SituationFragment());
        navigationEntities.add(new NavigationEntity("", navigationArrays[0], navigationSubArrays[0], navigationHeadArrays[0]));

        List<LightEntity> light = boardRoomEntity.getLight();
        if (light != null && light.size() > 0) {
            LightsFragment lightsFragment = new LightsFragment();
            fragments.add(lightsFragment);
            navigationEntities.add(new NavigationEntity("", navigationArrays[1], navigationSubArrays[1], navigationHeadArrays[1]));
        }

        List<CurtainEntity> curtain = boardRoomEntity.getCurtain();
        if (curtain != null && curtain.size() > 0) {
            fragments.add(new CurtainFragment());
            navigationEntities.add(new NavigationEntity("", navigationArrays[2], navigationSubArrays[2], navigationHeadArrays[2]));
        }

        List<AirEntity> air = boardRoomEntity.getAir();
        if (air != null && air.size() > 0) {
            fragments.add(new AirContainerFragment());
            navigationEntities.add(new NavigationEntity("", navigationArrays[3], navigationSubArrays[3], navigationHeadArrays[3]));
        }

        List<ProjectorEntity> projector = boardRoomEntity.getProjector();
        if (projector != null && projector.size() > 0) {
            fragments.add(new ProjectorContainerFragment());
            navigationEntities.add(new NavigationEntity("", navigationArrays[4], navigationSubArrays[4], navigationHeadArrays[4]));
        }

        List<TvEntity> tv = boardRoomEntity.getTv();
        if (tv != null && tv.size() > 0) {
            fragments.add(new TVFragment());
            navigationEntities.add(new NavigationEntity("", navigationArrays[5], navigationSubArrays[5], navigationHeadArrays[5]));
        }


        fragments.add(new MusicContainerFragment());
        navigationEntities.add(new NavigationEntity("", navigationArrays[6], navigationSubArrays[6], navigationHeadArrays[6]));
        return fragments;
    }

    private List<BaseLazyFragment> defaultMode() {

        List<BaseLazyFragment> fragments = new ArrayList<>();
        navigationEntities = new ArrayList<>();
        String[] navigationArrays = mContext.getResources().getStringArray(R.array.navigation_list);
        String[] navigationSubArrays = mContext.getResources().getStringArray(R.array.navigation_subtitle_list);

        fragments.add(new SituationFragment());
        fragments.add(new LightsFragment());
        fragments.add(new CurtainFragment());
        fragments.add(new AirContainerFragment());
        fragments.add(new ProjectorContainerFragment());
        fragments.add(new TVFragment());
        fragments.add(new MusicContainerFragment());

        for (int i = 0; i < navigationArrays.length; i++) {
            navigationEntities.add(new NavigationEntity("", navigationArrays[i], navigationSubArrays[i], navigationHeadArrays[i]));
        }
        return fragments;
    }

    @Override
    public List<NavigationEntity> getNavigationListData(Context context) {
        return navigationEntities;
    }
}
