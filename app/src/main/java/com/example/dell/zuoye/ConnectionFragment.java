package com.example.dell.zuoye;


import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class ConnectionFragment extends Fragment {


    private View view;
    private XRecyclerView mXre;
    private ArrayList<User> list;
    private XreAdapter xreAdapter;
    private Receiver receiver;


    public ConnectionFragment() {
        // Required empty public constructor
    }

    private static final String TAG = "ConnectionFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_connection, container, false);
        initData();
        initView(inflate);
        return inflate;
    }

    @Override
    public void onResume() {
        super.onResume();
        receiver = new Receiver();
        IntentFilter intentFilter = new IntentFilter("zyzyzy");
        getActivity().registerReceiver(receiver, intentFilter);
    }

    @Override
    public void onStop() {
        super.onStop();
        getActivity().unregisterReceiver(receiver);
    }

    private void initData() {
        final String url = "https://www.wanandroid.com/project/list/1/json?cid=294";
        OkHttpClient okHttpClient = new OkHttpClient();
        Request build = new Request.Builder()
                .url(url)
                .build();
        Call call = okHttpClient.newCall(build);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "onFailure: " + e.getMessage());
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
                            JSONObject data = jsonObject.getJSONObject("data");
                            JSONArray datas = data.getJSONArray("datas");
                            ArrayList<User> users = new ArrayList<>();
                            for (int i = 0; i < datas.length(); i++) {
                                JSONObject jsonObject1 = datas.getJSONObject(i);
                                String desc = jsonObject1.optString("desc");
                                String envelopePic = jsonObject1.optString("envelopePic");
                                User user = new User();
                                user.setDesc(desc);
                                user.setImgurl(envelopePic);
                                users.add(user);
                            }
                            list.addAll(users);
                            xreAdapter.notifyDataSetChanged();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    private void initView(final View inflate) {
        mXre = (XRecyclerView) inflate.findViewById(R.id.xre);
        mXre.setLayoutManager(new LinearLayoutManager(getContext()));
        list = new ArrayList<>();
        xreAdapter = new XreAdapter(getContext(), list);
        mXre.setAdapter(xreAdapter);
        xreAdapter.setOnLongClickListener(new XreAdapter.OnLongClickLostener() {
            @Override
            public void ClickItem(int position) {
                Intent intent = new Intent("zyzyzy");
                User user = list.get(position);
                intent.putExtra("user",user);
                getActivity().sendBroadcast(intent);
            }
        });
        xreAdapter.setOnClickListener(new XreAdapter.OnClickLostener() {
            @Override
            public void ClickItem(int position) {
                Intent intent = new Intent(getContext(), XiazaiActivity.class);
                startActivity(intent);
            }
        });
    }
}
