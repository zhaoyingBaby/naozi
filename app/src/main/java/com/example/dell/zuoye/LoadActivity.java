package com.example.dell.zuoye;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoadActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 开始上传
     */
    private Button mStart;
    /**
     * Zuoye
     */
    private TextView mText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);
        initView();
    }

    private void initView() {
        mStart = (Button) findViewById(R.id.start);
        mStart.setOnClickListener(this);
        mText = (TextView) findViewById(R.id.text);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.start:
                loadFile();
                break;
        }
    }

    private static final String TAG = "LoadActivity";
    private void loadFile() {
         String url = "http://yun918.cn/study/public/file_upload.php";
                         String filepath = Environment.getExternalStorageDirectory() + File.separator;
                         File file = new File(filepath + "b.jpg");
                         final RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"), file);
                         MultipartBody buildpath = new MultipartBody.Builder()
                                 .setType(MultipartBody.FORM)
                                 .addFormDataPart("key", "H1809B")
                                 .addFormDataPart("file", file.getName(), requestBody)
                                 .build();
                         OkHttpClient okHttpClient = new OkHttpClient();
                         Request build = new Request.Builder()
                                 .post(buildpath)
                                 .url(url)
                                 .build();
        Call call = okHttpClient.newCall(build);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "onFailure: "+e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                Log.d(TAG, "onResponse: "+result);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mText.setText(result);
                    }
                });
            }
        });
    }
}
