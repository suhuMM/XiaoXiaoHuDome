package com.suhu.android.bean;

import android.view.View;

import com.suhu.android.adapter.holder.BaseViewHolder;

/**
 * @author suhu
 * @data 2017/12/7.
 * @description
 */

public interface TypeFactory {
    /**
     * 类型
     *
     * @param visitable
     * @return int
     *
    */
    int type(Visitable visitable);


    /**
     * 创建viewHolder
     *
     * @param type
     * @param itemView
     * @return BaseViewHolder
     *
     * */
    BaseViewHolder createViewHolder(int type , View itemView);

}
