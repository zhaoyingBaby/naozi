package com.example.textzuoye;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ViewPager mVp;
    private TabLayout mTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mVp = (ViewPager) findViewById(R.id.vp);
        mTab = (TabLayout) findViewById(R.id.tab);

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new OneFragment());
        fragments.add(new TwoFragment());
        FrAdapter frAdapter = new FrAdapter(getSupportFragmentManager(), fragments);
        mTab.setupWithViewPager(mVp);
        mVp.setAdapter(frAdapter);
        mTab.getTabAt(0).setText("资讯").setIcon(R.mipmap.ic_launcher);
        mTab.getTabAt(1).setText("项目").setIcon(R.mipmap.ic_launcher);
    }
}
