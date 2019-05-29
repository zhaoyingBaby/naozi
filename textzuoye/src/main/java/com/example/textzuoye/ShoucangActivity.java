package com.example.textzuoye;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ShoucangActivity extends AppCompatActivity {

    private XRecyclerView mXrecycle;
    private ArrayList<UserBean> list;
    private XreAdapter xreAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoucang);
        initView();
    }

    private void initView() {
        mXrecycle = (XRecyclerView) findViewById(R.id.xrecycle);
        mXrecycle.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        List<UserBean> userBeans = DBUtils.queryAll();
        list.addAll(userBeans);
        xreAdapter = new XreAdapter(this, list);
        mXrecycle.setAdapter(xreAdapter);
    }
}
