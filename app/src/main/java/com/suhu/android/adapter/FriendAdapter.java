package com.suhu.android.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.suhu.android.R;
import com.suhu.android.db.FriendModel;

import java.util.List;

/**
 * Created by Administrator on 2017/9/15 0015.
 */

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.MyViewHolder>{

    private Context context;
    private List<FriendModel> models;
    private OnItemClickListener mOnItemClickLitener;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
        void onItemLongClick(View view , int position);
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickLitener = mOnItemClickListener;
    }

    public FriendAdapter(Context context, List<FriendModel> models) {
        this.context = context;
        this.models = models;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_friend,parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        FriendModel model = models.get(position);
        Glide.with(context).load(model.getUrl()).into(holder.imageView);
        holder.textView.setText(model.getName());
        addListener(holder);
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    private void addListener(final MyViewHolder holder) {
        if (mOnItemClickLitener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickLitener.onItemClick(holder.imageView,holder.getLayoutPosition());
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    mOnItemClickLitener.onItemLongClick(holder.imageView,holder.getLayoutPosition());
                    return false;
                }
            });
        }
    }

    public void addData(int position,FriendModel model) {
        models.add(position, model);
        notifyItemInserted(position);
    }

//    public void removeData(int position) {
//        models.remove(position);
//        notifyItemRemoved(position);
//    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;
        public MyViewHolder(View view){
            super(view);
            imageView = view.findViewById(R.id.image);
            textView = view.findViewById(R.id.name);
        }
    }

}
