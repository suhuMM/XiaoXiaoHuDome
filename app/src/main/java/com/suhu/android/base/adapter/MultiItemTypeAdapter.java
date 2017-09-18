package com.suhu.android.base.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * @author suhu
 * @data 2017/9/18.
 * @description
 */

public class MultiItemTypeAdapter<T> extends RecyclerView.Adapter<ViewHolder> {

    protected Context context;
    protected List<T> mDataList;

    protected ItemViewDelegateManager itemViewDelegateManager;
    protected OnItemClickListener onItemClickListener;

    public MultiItemTypeAdapter(Context context) {
        this.context = context;
        itemViewDelegateManager = new ItemViewDelegateManager();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemViewDelegate itemViewDelegate = itemViewDelegateManager.getItemViewDelegate(viewType);
        int layoutId = itemViewDelegate.getItemViewLayoutId();
        ViewHolder holder = ViewHolder.createViewHolder(context,parent,layoutId);
        onViewHolderCreated(holder,holder.getConvertView());
        setListener(parent,holder,viewType);
        return holder;
    }
    public void onViewHolderCreated(ViewHolder holder, View itemView) {

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        convert(holder,mDataList.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataList == null ? 0 : mDataList.size();
    }

    public void convert(ViewHolder holder, T t) {
        itemViewDelegateManager.convert(holder, t, holder.getAdapterPosition());
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    protected boolean isEnabled(int viewType) {
        return true;
    }

    protected void setListener(final ViewGroup parent, final ViewHolder viewHolder, int viewType) {
        if (!isEnabled(viewType)) return;
        viewHolder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    int position = viewHolder.getAdapterPosition();
                    onItemClickListener.onItemClick(v, viewHolder, position);
                }
            }
        });

        viewHolder.getConvertView().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (onItemClickListener != null) {
                    int position = viewHolder.getAdapterPosition();
                    return onItemClickListener.onItemLongClick(v, viewHolder, position);
                }
                return false;
            }
        });
    }







}
