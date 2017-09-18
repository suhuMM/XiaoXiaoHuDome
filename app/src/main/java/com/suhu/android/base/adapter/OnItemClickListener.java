package com.suhu.android.base.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @author suhu
 * @data 2017/9/18.
 * @description
 */

public interface OnItemClickListener {
    void onItemClick(View view, RecyclerView.ViewHolder holder, int position);
    boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position);
}
