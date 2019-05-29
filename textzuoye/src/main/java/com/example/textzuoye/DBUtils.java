package com.example.textzuoye;

import com.example.textzuoye.dao.DaoSession;
import com.example.textzuoye.dao.UserBeanDao;

import java.util.List;

/**
 * Created by DELL on 2019/5/28.
 */

public class DBUtils {
      public static UserBean queryItem(UserBean bean){
              DaoSession daoSession = MyApplication.getDaoSession();
          UserBean unique = daoSession.queryBuilder(UserBean.class)
                      .where(UserBeanDao.Properties.Desc.eq(bean.getDesc()))
                      .build().unique();
              return unique;
          }
          public static long insert(UserBean bean){
              DaoSession daoSession = MyApplication.getDaoSession();
              if(queryItem(bean)==null){
                  long insert = daoSession.insert(bean);
                  return insert;
              }
              return 0;
          }
          public static void delete(UserBean bean){
              DaoSession daoSession = MyApplication.getDaoSession();
              if(queryItem(bean)!=null){
                  daoSession.delete(bean);
              }
          }

          public static List<UserBean> queryAll(){
              DaoSession daoSession = MyApplication.getDaoSession();
              return daoSession.loadAll(UserBean.class);
          }
}
