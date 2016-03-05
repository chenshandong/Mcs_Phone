package com.yzd.android.mcs_phone.interator.impl;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;

import com.yzd.android.mcs_phone.bean.database.MusicsListEntity;
import com.yzd.android.mcs_phone.interator.IMusicSearchInterator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/10/19.
 */
public class MusicSearchInteratorImpl implements IMusicSearchInterator {

    String[] mMusicReadInfo = {
            MediaStore.Audio.Media.TITLE , 	// 歌名
            MediaStore.Audio.Media.DATA ,   	// 路径
            MediaStore.Audio.Media.ARTIST , 	// 演唱者
            MediaStore.Audio.Media.ALBUM	,	// 专辑名
            MediaStore.Audio.Media.DURATION , 	// 音频文件长度，毫秒
            MediaStore.Audio.Media.ALBUM_KEY  	// 用来读取专辑图片时使用
    };


    @Override
    public List<MusicsListEntity> readMusic(Context context) {
        ContentResolver contentResolver = context.getContentResolver();
        Uri audioUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI ;

        List<MusicsListEntity> data = null;

        Cursor cursor = contentResolver.query(
                audioUri,
                mMusicReadInfo,
                //null, null,
                MediaStore.Audio.Media.DATA + " like ? or " + MediaStore.Audio.Media.DATA + " like ?" , new String[]{"%.mp3" , "%.wma"} ,
                null) ;
        if(null != cursor && cursor.getCount() > 0) {
            data = new ArrayList<MusicsListEntity>() ;
            while(cursor.moveToNext()) {
                // 读到一个音频
                /////////////////// 读取该音频的专辑图片信息
//                Bitmap bmp = null ;
//                String albumKey = cursor.getString(5) ;
//                Uri albumUri = MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI ;
//                Cursor albumCursor = contentResolver.query(albumUri, new String[]{MediaStore.Audio.Albums.ALBUM_ART}, MediaStore.Audio.Albums.ALBUM_KEY +"=?", new String[]{albumKey}, null) ;
//                if(albumCursor != null && albumCursor.getCount() > 0) {
//                    albumCursor.moveToNext() ;
//                    String albumPath = albumCursor.getString(0);
//                    bmp = BitmapFactory.decodeFile(albumPath) ;
//                    albumCursor.close() ;
//                }
                //////////////////
                data.add(new MusicsListEntity(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3) , cursor.getLong(4))) ;
            }
        }
        if(null != cursor) {
            cursor.close() ;
        }
        return data;
    }
}
