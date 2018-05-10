package com.suhu.android.adapter.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

import com.suhu.android.listener.OnMixClickListener;

/**
 * @author suhu
 * @data 2017/12/6.
 * @description
 */

public abstract class   BaseViewHolder <T> extends RecyclerView.ViewHolder{

    private SparseArray<View> views;
    private View itemView;

    public BaseViewHolder(View itemView) {
        super(itemView);
        views = new SparseArray<>();
        this.itemView = itemView;
    }

    public View getView(int resID){
        View view = views.get(resID);
        if (view==null){
            view = itemView.findViewById(resID);
            views.put(resID,view);
        }
        return view;
    }


    /**
     *
     * 设置值
     *
     * @param model
     * @param position
     * @param context
     * @param clickListener
     *
    */
    public abstract void setUpView(T model, int position, Context context , OnMixClickListener clickListener);

}
