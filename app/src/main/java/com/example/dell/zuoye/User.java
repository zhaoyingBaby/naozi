package com.example.dell.zuoye;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by DELL on 2019/5/27.
 */

public class User implements Parcelable {
    private String imgurl;
    private String desc;

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "imgurl='" + imgurl + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public User(String imgurl, String desc) {
        this.imgurl = imgurl;
        this.desc = desc;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.imgurl);
        dest.writeString(this.desc);
    }

    protected User(Parcel in) {
        this.imgurl = in.readString();
        this.desc = in.readString();
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
