package com.suhu.android.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.suhu.android.R;
import com.suhu.android.bean.Head;

import java.util.List;

/**
 * @author suhu
 * @data 2017/12/8.
 * @description
 */

public class HeadBeanAdapter extends RecyclerView.Adapter<HeadBeanAdapter.HeadHolder>{
    private List<Head.Bean> list;
    private Context context;
    private LayoutInflater inflater;
    private HeadHolder headHolder;

    public HeadBeanAdapter(List<Head.Bean> list, Context context) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public HeadHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        headHolder = new HeadHolder(inflater.inflate(R.layout.item_head,parent,false));
        return headHolder;
    }

    @Override
    public void onBindViewHolder(HeadHolder holder, int position) {
        holder.age.setText(list.get(position).getAge()+"");
        holder.name.setText(list.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return list!=null?list.size():0;
    }


    public void setList(List<Head.Bean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public static class HeadHolder extends RecyclerView.ViewHolder{
        private TextView age,name;
        public HeadHolder(View itemView) {
            super(itemView);
            age = itemView.findViewById(R.id.item_age);
            name = itemView.findViewById(R.id.item_name);
        }
    }


}
