package com.example.dell.zuoye;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class XiazaiActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 开始下载
     */
    private Button mStart;
    private ProgressBar mProgress;
    /**
     * 下载状态
     */
    private TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiazai);
        initView();
    }

    private void initView() {
        mStart = (Button) findViewById(R.id.start);
        mStart.setOnClickListener(this);
        mProgress = (ProgressBar) findViewById(R.id.progress);
        mTv = (TextView) findViewById(R.id.tv);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.start:
                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        xiazai();
                    }
                }.start();
                break;
        }
    }

    private static final String TAG = "XiazaiActivity";
    private void xiazai() {
        String apk_url="https://wanandroid.com/blogimgs/c3615a24-79ef-45c9-9ae6-5adfe5437d32.jpeg";
        String s = Environment.getExternalStorageDirectory() + File.separator + "zy.jpg";
        File file = new File(s);
        try {
            if(!file.exists()){
                file.createNewFile();
            }
            URL url = new URL(apk_url);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            InputStream inputStream = con.getInputStream();
            FileOutputStream outputStream = new FileOutputStream(file);
            final int contentLength = con.getContentLength();
            byte[]bytes=new byte[1024*4];
            int readlength=0;
            int currlength=0;
            while ((readlength=inputStream.read(bytes))!=-1){
                outputStream.write(bytes,0,readlength);
                currlength+=readlength;
                mProgress.setProgress((currlength*100)/contentLength);
                Log.d(TAG, "xiazai: "+(currlength*100)/contentLength+"%");
                final int finalCurrlength = currlength;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mTv.setText((finalCurrlength *100)/contentLength+"%");
                    }
                });
            }
            inputStream.close();
            outputStream.close();
            con.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
