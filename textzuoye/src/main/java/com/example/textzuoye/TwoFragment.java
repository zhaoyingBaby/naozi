package com.example.textzuoye;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class TwoFragment extends Fragment implements View.OnClickListener {


    private View view;
    /**
     * 我的收藏
     */
    private TextView mShoucang;
    /**
     * 我的下载
     */
    private TextView mXiazai;

    public TwoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_two, container, false);
        initView(inflate);
        return inflate;
    }

    private void initView(View inflate) {
        mShoucang = (TextView) inflate.findViewById(R.id.shoucang);
        mXiazai = (TextView) inflate.findViewById(R.id.xiazai);
        mShoucang.setOnClickListener(this);
        mXiazai.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.shoucang:
                Intent intent = new Intent(getContext(), ShoucangActivity.class);
                startActivity(intent);
                break;
            case R.id.xiazai:
                Intent intent1 = new Intent(getContext(), XiazaiActivity.class);
                startActivity(intent1);
                break;
        }
    }
}
