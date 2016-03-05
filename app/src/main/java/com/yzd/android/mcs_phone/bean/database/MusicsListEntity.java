package com.yzd.android.mcs_phone.bean.database;

import android.os.Parcel;
import android.os.Parcelable;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "musicLists")
public class MusicsListEntity extends Model implements Parcelable {

    @Column
    private String album;
    @Column
    private String picture = "http://img.hb.aicdn.com/0a067e459b62467c50579d0831330fb6eed23dcd84585-GmNZdy_fw658";
    private String ssid;
    @Column
    private String artist;
    @Column
    private String url;
    private String company;
    @Column
    private String title;
    private float rating_avg;
    @Column
    private String length;
    private String subType;
    @Column
    private String public_time;
    @Column
    private int songlists_count;
    private String sssid;
    private String aid;
    private String sha256;
    private String kbps;
    private String albumtitle;
    @Column
    private int like;
    @Column
    private long dulation;
    @Column
    private String musicList;

    public long getDulation() {
        return dulation;
    }

    public void setDulation(long dulation) {
        this.dulation = dulation;
    }


    public MusicsListEntity(String title, String url,String artist, String album, long dulation){
        this.title = title;
        this.url = url;
        this.artist = artist;
        this.album = album;
        this.dulation = dulation;
    }

    public String getMusicList() {
        return musicList;
    }

    public void setMusicList(String musicList) {
        this.musicList = musicList;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getRating_avg() {
        return rating_avg;
    }

    public void setRating_avg(float rating_avg) {
        this.rating_avg = rating_avg;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public String getPublic_time() {
        return public_time;
    }

    public void setPublic_time(String public_time) {
        this.public_time = public_time;
    }

    public int getSonglists_count() {
        return songlists_count;
    }

    public void setSonglists_count(int songlists_count) {
        this.songlists_count = songlists_count;
    }

    public String getSssid() {
        return sssid;
    }

    public void setSssid(String sssid) {
        this.sssid = sssid;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getSha256() {
        return sha256;
    }

    public void setSha256(String sha256) {
        this.sha256 = sha256;
    }

    public String getKbps() {
        return kbps;
    }

    public void setKbps(String kbps) {
        this.kbps = kbps;
    }

    public String getAlbumtitle() {
        return albumtitle;
    }

    public void setAlbumtitle(String albumtitle) {
        this.albumtitle = albumtitle;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.album);
        dest.writeString(this.picture);
        dest.writeString(this.ssid);
        dest.writeString(this.artist);
        dest.writeString(this.url);
        dest.writeString(this.company);
        dest.writeString(this.title);
        dest.writeFloat(this.rating_avg);
        dest.writeString(this.length);
        dest.writeString(this.subType);
        dest.writeString(this.public_time);
        dest.writeInt(this.songlists_count);
        dest.writeString(this.sssid);
        dest.writeString(this.aid);
        dest.writeString(this.sha256);
        dest.writeString(this.kbps);
        dest.writeString(this.albumtitle);
        dest.writeInt(this.like);
        dest.writeLong(this.dulation);
        dest.writeString(this.musicList);
    }

    public MusicsListEntity() {
    }

    private MusicsListEntity(Parcel in) {
        this.album = in.readString();
        this.picture = in.readString();
        this.ssid = in.readString();
        this.artist = in.readString();
        this.url = in.readString();
        this.company = in.readString();
        this.title = in.readString();
        this.rating_avg = in.readFloat();
        this.length = in.readString();
        this.subType = in.readString();
        this.public_time = in.readString();
        this.songlists_count = in.readInt();
        this.sssid = in.readString();
        this.aid = in.readString();
        this.sha256 = in.readString();
        this.kbps = in.readString();
        this.albumtitle = in.readString();
        this.like = in.readInt();
        this.dulation = in.readLong();
        this.musicList = in.readString();
    }

    public static final Creator<MusicsListEntity> CREATOR = new Creator<MusicsListEntity>() {
        public MusicsListEntity createFromParcel(Parcel source) {
            return new MusicsListEntity(source);
        }

        public MusicsListEntity[] newArray(int size) {
            return new MusicsListEntity[size];
        }
    };
}
