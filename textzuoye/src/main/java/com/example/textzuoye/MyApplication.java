package com.example.textzuoye;

import android.app.Application;

import com.example.textzuoye.dao.DaoMaster;
import com.example.textzuoye.dao.DaoSession;

/**
 * Created by DELL on 2019/5/28.
 */

public class MyApplication extends Application {

    private static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        initData();
    }

    private void initData() {
        DaoMaster.DevOpenHelper mmhs = new DaoMaster.DevOpenHelper(this, "mmhs");
        DaoMaster daoMaster = new DaoMaster(mmhs.getWritableDatabase());
        daoSession = daoMaster.newSession();
    }

    public static DaoSession getDaoSession() {
        return daoSession;
    }
}
