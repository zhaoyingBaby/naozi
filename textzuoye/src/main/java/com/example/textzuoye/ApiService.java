package com.example.textzuoye;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;

/**
 * Created by DELL on 2019/5/28.
 */

public interface ApiService {
    public String url="http://c.m.163.com/";
    @GET("nc/article/list/T1348654060988/0-20.html")
    Observable<ResponseBody>geturl();
}
