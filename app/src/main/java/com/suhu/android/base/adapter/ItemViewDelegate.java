package com.suhu.android.base.adapter;

/**
 * @author suhu
 * @data 2017/9/18.
 * @description
 */

public interface ItemViewDelegate<T> {
    int getItemViewLayoutId();
    boolean isForViewType(T item , int position);
    void convert(ViewHolder holder,T t,int position);

}

