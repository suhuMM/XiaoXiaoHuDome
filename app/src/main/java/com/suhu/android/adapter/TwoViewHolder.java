package com.suhu.android.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.suhu.android.R;
import com.suhu.android.adapter.holder.BaseViewHolder;
import com.suhu.android.bean.Two;
import com.suhu.android.listener.OnMixClickListener;

/**
 * @author suhu
 * @data 2017/12/7.
 * @description
 */

public class TwoViewHolder extends BaseViewHolder<Two>{

    private RequestOptions options;
    public TwoViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void setUpView(final Two model, final int position, Context context, final OnMixClickListener clickListener) {
        ImageView image1 = (ImageView) getView(R.id.image1);
        ImageView image2 = (ImageView) getView(R.id.image2);

        options = new RequestOptions();
        options.error(context.getDrawable(R.drawable.qq))
                .skipMemoryCache(false)
                .diskCacheStrategy(DiskCacheStrategy.NONE);

        Glide.with(context)
                .load(model.getUrl())
                .apply(options)
                .into(image1);

        Glide.with(context).load(model.getIcon()).into(image2);
        if (clickListener!=null){
            image1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickListener.onClick(model,position,view);
                }
            });
        }
    }
}
