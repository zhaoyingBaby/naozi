package com.example.textzuoye;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by DELL on 2019/5/28.
 */
@Entity
public class UserBean {

    @Id
    private Long lid;
    
    private String imgurl;
    private String desc;

    public UserBean() {
    }

    @Generated(hash = 554293598)
    public UserBean(Long lid, String imgurl, String desc) {
        this.lid = lid;
        this.imgurl = imgurl;
        this.desc = desc;
    }

    public Long getLid() {
        return this.lid;
    }

    public void setLid(Long lid) {
        this.lid = lid;
    }

    public String getImgurl() {
        return this.imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
