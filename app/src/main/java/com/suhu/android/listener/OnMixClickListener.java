package com.suhu.android.listener;

import android.view.View;

import com.suhu.android.bean.Visitable;

/**
 * @author suhu
 * @data 2017/12/8.
 * @description
 */

public interface OnMixClickListener {

    /**
     * 监听
     *
     * @param visitable
     * @param position
     * @param view
     */

    void onClick(Visitable visitable , int position , View view);
}
