package com.yzd.android.mcs_phone.api;

import java.security.PublicKey;

/**
 * Created by Clearlove on 15/10/18.
 */
public class MusicConstants {

    public static final String KEY_MUSIC_PARCELABLE_DATA = "KEY_MUSIC_PARCELABLE_DATA";
    public static final String KEY_MUSIC_TOTAL_DURATION = "KEY_MUSIC_TOTAL_DURATION";
    public final static String KEY_MUSIC_CURRENT_DUTATION = "KEY_MUSIC_CURRENT_DUTATION";
    public final static String KEY_MUSIC_SECOND_PROGRESS = "KEY_MUSIC_SECOND_PROGRESS";
    public final static String KEY_PLAYER_SEEK_TO_PROGRESS = "KEY_PLAYER_SEEK_TO_PROGRESS";

    public static final String ACTION_MUSIC_BUNDLE_BROADCAST = "ACTION_MUSIC_BUNDLE_BROADCAST";
    public final static String ACTION_MUSIC_CURRENT_PROGRESS_BROADCAST = "ACTION_MUSIC_CURRENT_PROGRESS_BROADCAST";
    public final static String ACTION_MUSIC_SECOND_PROGRESS_BROADCAST = "ACTION_MUSIC_SECOND_PROGRESS_BROADCAST";

    public static final String MUSIC_SEARCH_RESULT = "MUSIC_SEARCH_RESULT";

    public static final int EVENT_BEGIN = 0X100;
    public static final int EVENT_REFRESH_DATA = EVENT_BEGIN + 10;
    public static final int EVENT_LOAD_MORE_DATA = EVENT_BEGIN + 20;
    public static final int EVENT_START_PLAY_MUSIC = EVENT_BEGIN + 30;
    public static final int EVENT_STOP_PLAY_MUSIC = EVENT_BEGIN + 40;

    public static final String MEET_BEFORE = "MEET_BEFORE";
    public static final String MEET_ING = "MEET_ING";
    public static final String RELAX = "RELAX";
    public static final String ACTIVE = "ACTIVE";
    public static final String PRIZE = "PRIZE";

}
