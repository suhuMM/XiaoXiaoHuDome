package com.suhu.android.adapter.holder;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @author suhu
 * @data 2017/12/8.
 * @description
 */

public class ItemDecoration extends RecyclerView.ItemDecoration{

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.set(30,15,30,15);
    }
}
