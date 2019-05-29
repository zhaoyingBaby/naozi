package com.example.textzuoye;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class OneFragment extends Fragment {


    private View view;
    private XRecyclerView mXre;
    private Banner mBanner;
    private ArrayList<UserBean> list;
    private XreAdapter xreAdapter;
    private int mposition;

    public OneFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_one, container, false);
        initBan();
        initData();
        initView(inflate);
        return inflate;
    }

    private static final String TAG = "OneFragment";

    private void initBan() {
        String banner = " http://www.wanandroid.com/banner/json";
        OkHttpClient okHttpClient = new OkHttpClient();
        Request build = new Request.Builder()
                .url(banner)
                .build();
        Call call = okHttpClient.newCall(build);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "onFailure: ");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                Log.d(TAG, "onResponse: " + result);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject jsonObject = new JSONObject(result);
                            JSONArray data = jsonObject.getJSONArray("data");
                            ArrayList<String> banners = new ArrayList<>();
                            for (int i = 0; i < data.length(); i++) {
                                JSONObject jsonObject1 = data.getJSONObject(i);
                                String imagePath = jsonObject1.optString("imagePath");
                                banners.add(imagePath);
                            }
                            mBanner.setImages(banners).setImageLoader(new ImageLoader() {
                                @Override
                                public void displayImage(Context context, Object path, ImageView imageView) {
                                    Glide.with(context).load(path).into(imageView);
                                }
                            }).start();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    private void initData() {
        Retrofit build = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(ApiService.url)
                .build();
        ApiService apiService = build.create(ApiService.class);
        apiService.geturl().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe: ");
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            String results = responseBody.string();
                            Log.d(TAG, "onNext: 数据" + results);
                            JSONObject jsonObject = new JSONObject(results);
                            JSONArray array = jsonObject.getJSONArray("T1348654060988");
                            ArrayList<UserBean> userBeans = new ArrayList<>();
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject jsonObject1 = array.getJSONObject(i);
                                String imgsrc = jsonObject1.optString("imgsrc");
                                String digest = jsonObject1.optString("digest");
                                UserBean userBean = new UserBean();
                                userBean.setDesc(digest);
                                userBean.setImgurl(imgsrc);
                                userBeans.add(userBean);
                            }
                            list.addAll(userBeans);
                            xreAdapter.notifyDataSetChanged();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: ");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: ");
                    }
                });
    }

    private void initView(View inflate) {
        mXre = (XRecyclerView) inflate.findViewById(R.id.xre);
        mBanner = (Banner) inflate.findViewById(R.id.banner);
        mXre.setLayoutManager(new LinearLayoutManager(getContext()));
        list = new ArrayList<>();
        xreAdapter = new XreAdapter(getContext(), list);
        mXre.setAdapter(xreAdapter);
        registerForContextMenu(mXre);
        xreAdapter.setOnLongclickListener(new XreAdapter.OnLongclickListener() {
            @Override
            public void ClickItem(int position) {
                mposition = position;
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.add(1,1,1,"收藏");
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                UserBean userBean = list.get(mposition);
                DBUtils.insert(userBean);
                break;
        }
        return super.onContextItemSelected(item);
    }
}
