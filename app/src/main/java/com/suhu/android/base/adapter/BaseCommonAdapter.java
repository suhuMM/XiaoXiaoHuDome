package com.suhu.android.base.adapter;

import android.content.Context;
import android.view.LayoutInflater;

import java.util.List;

/**
 *@author suhu
 *@time 2017/4/11 11:00
 *
*/
public abstract class BaseCommonAdapter<T> extends MultiItemTypeAdapter<T> {

    protected Context mContext;
    protected int mLayoutId;
    protected List<T> mDatas;
    protected LayoutInflater mInflater;

    @Override
    public void setDatas(List<T> mDatas) {
        super.setDatas(mDatas);
        this.mDatas = mDatas;
        notifyDataSetChanged();
    }

    public BaseCommonAdapter(final Context context, final int layoutId) {
        super(context);
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mLayoutId = layoutId;

        addItemViewDelegate(new ItemViewDelegate<T>() {
            @Override
            public int getItemViewLayoutId() {
                return layoutId;
            }

            @Override
            public boolean isForViewType(T item, int position) {
                return true;
            }

            @Override
            public void convert(ViewHolder holder, T t, int position) {
                BaseCommonAdapter.this.convert(holder, t, position);
            }
        });
    }

    protected abstract void convert(ViewHolder holder, T t, int position);


}
