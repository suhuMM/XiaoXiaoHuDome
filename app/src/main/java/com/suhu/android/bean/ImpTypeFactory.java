package com.suhu.android.bean;

import android.view.View;

import com.suhu.android.R;
import com.suhu.android.adapter.HeadViewHolder;
import com.suhu.android.adapter.OneViewHolder;
import com.suhu.android.adapter.ThreeViewHolder;
import com.suhu.android.adapter.TwoViewHolder;
import com.suhu.android.adapter.holder.BaseViewHolder;

/**
 * @author suhu
 * @data 2017/12/7.
 * @description
 */

public class ImpTypeFactory implements TypeFactory{
    private final int TYPE_ONE = R.layout.layout_item_one;
    private final int TYPE_TWO = R.layout.layout_item_two;
    private final int TYPE_THREE = R.layout.layout_item_three;
    private final int TYPE_HEAD = R.layout.layout_item_head;

    @Override
    public int type(Visitable visitable) {
        if (visitable instanceof One){
            return TYPE_ONE;
        }

        if (visitable instanceof Two){
            return TYPE_TWO;
        }

        if (visitable instanceof Three){
            return TYPE_THREE;
        }

        if (visitable instanceof Head){
            return TYPE_HEAD;
        }

        return 0;
    }

    @Override
    public BaseViewHolder createViewHolder(int type, View itemView) {
        if (TYPE_ONE == type){
            return new OneViewHolder(itemView);
        }
        if (TYPE_TWO == type){
            return new TwoViewHolder(itemView);
        }

        if (TYPE_THREE == type){
            return new ThreeViewHolder(itemView);
        }

        if (TYPE_HEAD == type){
            return new HeadViewHolder(itemView);
        }

        return null;
    }
}
