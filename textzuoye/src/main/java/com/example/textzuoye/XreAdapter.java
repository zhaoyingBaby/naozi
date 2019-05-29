package com.example.textzuoye;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jcodecraeer.xrecyclerview.XRecyclerView;


import java.util.ArrayList;

/**
 * Created by DELL on 2019/5/28.
 */

public class XreAdapter extends XRecyclerView.Adapter {

    private Context context;
    private ArrayList<UserBean> mlist;
    private OnLongclickListener listeners;

    public XreAdapter(Context context, ArrayList<UserBean> mlist) {
        this.context = context;
        this.mlist = mlist;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View inflate = LayoutInflater.from(context).inflate(R.layout.item2, null, false);
        return new TwoViewHolder(inflate);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {


        UserBean userBean = mlist.get(position);
        TwoViewHolder twoViewHolder = (TwoViewHolder) holder;
        twoViewHolder.item2_tv.setText(userBean.getDesc());
        Glide.with(context).load(userBean.getImgurl()).into(twoViewHolder.item2_img);

       holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
           @Override
           public boolean onLongClick(View v) {
               if(listeners!=null){
                   listeners.ClickItem(position);
               }
               return false;
           }
       });

    }


    @Override
    public int getItemCount() {
        return mlist.size();
    }

    class TwoViewHolder extends XRecyclerView.ViewHolder {
        private ImageView item2_img;
        private TextView item2_tv;

        public TwoViewHolder(View itemView) {
            super(itemView);
            item2_img = itemView.findViewById(R.id.item2_img);
            item2_tv = itemView.findViewById(R.id.item2_tv);
        }
    }
     public interface OnLongclickListener{
             void ClickItem(int position);
         }
         public void  setOnLongclickListener(OnLongclickListener listeners){
             this.listeners = listeners;
         }
}
