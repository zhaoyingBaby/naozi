package com.example.dell.zuoye;

import android.content.Context;
import android.content.Intent;
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
 * Created by DELL on 2019/5/27.
 */

public class XreAdapter extends XRecyclerView.Adapter {

    private Context context;
    private ArrayList<User>mlist;
    private OnClickLostener listener;
    private OnLongClickLostener listeners;

    public XreAdapter(Context context, ArrayList<User> mlist) {
        this.context = context;
        this.mlist = mlist;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType==1){
            View inflate = LayoutInflater.from(context).inflate(R.layout.item2, null, false);
            return new TwoViewHolder(inflate);


        }else {
            View inflate = LayoutInflater.from(context).inflate(R.layout.item1, null, false);
            return new OneViewHolder(inflate);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        User user = mlist.get(position);
        if(holder instanceof TwoViewHolder){
            TwoViewHolder twoViewHolder= (TwoViewHolder) holder;
            Glide.with(context).load(user.getImgurl()).into(twoViewHolder.item2_img);

            twoViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null){
                        listener.ClickItem(position);
                    }
                }
            });

        }else {
            OneViewHolder oneViewHolder= (OneViewHolder) holder;
            oneViewHolder.item1_tv.setText(user.getDesc());
            Glide.with(context).load(user.getImgurl()).into(oneViewHolder.item1_img);
            oneViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if(listeners!=null){
                        listeners.ClickItem(position);
                    }
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(position==3){
            return 1;
        }else if(position==10){
            return 1;
        }else if(position==18){
            return 1;
        }else {
            return 2;
        }
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }
    class OneViewHolder extends XRecyclerView.ViewHolder {
        private ImageView item1_img;
        private TextView item1_tv;
        public OneViewHolder(View itemView) {
            super(itemView);
            item1_img=itemView.findViewById(R.id.item1_img);
            item1_tv= itemView.findViewById(R.id.item1_tv);
        }
    }
    class TwoViewHolder extends XRecyclerView.ViewHolder {
        private ImageView item2_img;
        public TwoViewHolder(View itemView) {
            super(itemView);
            item2_img=itemView.findViewById(R.id.item2_img);
        }
    }
    public interface OnClickLostener{
        void ClickItem(int position);
    }
    public void setOnClickListener(OnClickLostener listener){

        this.listener = listener;
    }
    public interface OnLongClickLostener{
        void ClickItem(int position);
    }
    public void setOnLongClickListener(OnLongClickLostener listeners){

        this.listeners = listeners;
    }
}
