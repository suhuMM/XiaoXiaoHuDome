package com.suhu.android.base.adapter;


/**
 *@author suhu
 *@time 2017/4/11 11:00
 *
*/
public interface ItemViewDelegate<T> {

    int getItemViewLayoutId();

    boolean isForViewType(T item, int position);

    void convert(ViewHolder holder, T t, int position);

}
