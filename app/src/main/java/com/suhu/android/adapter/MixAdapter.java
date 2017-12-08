package com.suhu.android.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.suhu.android.adapter.holder.BaseViewHolder;
import com.suhu.android.bean.ImpTypeFactory;
import com.suhu.android.bean.TypeFactory;
import com.suhu.android.bean.Visitable;
import com.suhu.android.listener.OnMixClickListener;

import java.util.List;

/**
 * @author suhu
 * @data 2017/12/7.
 * @description https://www.2cto.com/kf/201610/558078.html
 */

public class MixAdapter extends RecyclerView.Adapter<BaseViewHolder>{
    private List<Visitable> list;
    private TypeFactory typeFactory;
    private Context context;
    private LayoutInflater inflater;

    private OnMixClickListener clickListener;

    public MixAdapter(List<Visitable> list,Context context) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
        typeFactory = new ImpTypeFactory();
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(viewType,parent,false);
        return typeFactory.createViewHolder(viewType,itemView);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.setUpView(list.get(position),position,context,clickListener);
    }


    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
        if (payloads.isEmpty()){
            onBindViewHolder(holder,position);
        }else{
            holder.setUpView(list.get(position),position,context,clickListener);
        }

    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).type(typeFactory);
    }

    @Override
    public int getItemCount() {
        if (null == list){
            return 0;
        }
        return list.size();
    }

    public void setList(List<Visitable> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void setClickListener(OnMixClickListener clickListener) {
        this.clickListener = clickListener;
    }

    /**
     * 局部刷新
     *
     * @param position
     */
    public void portionNotify(int position,Visitable visitable){
        if (list!= null){
            list.remove(position);
            list.add(position,visitable);
        }
        notifyItemChanged(position,visitable);
    }
}
