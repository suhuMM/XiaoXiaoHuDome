package com.suhu.android.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.suhu.android.R;
import com.suhu.android.adapter.holder.BaseViewHolder;
import com.suhu.android.bean.One;
import com.suhu.android.listener.OnMixClickListener;

/**
 * @author suhu
 * @data 2017/12/7.
 * @description
 */

public class OneViewHolder extends BaseViewHolder<One>{

    public OneViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void setUpView(final One model, final int position, Context context, final OnMixClickListener clickListener) {
        TextView age = (TextView) getView(R.id.age);
        TextView name = (TextView) getView(R.id.name);
        age.setText(model.getAge()+"");
        name.setText(model.getName());

        if (clickListener!=null){
            name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickListener.onClick(model,position,view);
                }
            });
        }

    }
}
