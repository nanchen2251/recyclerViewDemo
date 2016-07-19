package com.example.nanchen.recyclerviewdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * 自定义RecyclerView的Adapter
 * Created by 南尘 on 16-7-15.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> implements View.OnClickListener {

    private Context context;
    private List<String> list;
    private OnRecyclerItemClickListener listener;
    private RecyclerView recyclerView;

    public void setOnRecyclerItemClickListener(OnRecyclerItemClickListener listener) {
        this.listener = listener;
    }

    public MyAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    //在为RecyclerView提供数据的时候调用
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        this.recyclerView = null;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.text.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onClick(View v) {
        if (recyclerView != null && listener != null){
            int position = recyclerView.getChildAdapterPosition(v);
            listener.OnRecyclerItemClick(recyclerView,v,position,list.get(position));
        }
    }

    /**
     * 删除指定数据
     * @param position 数据位置
     */
    public void remove(int position){
        list.remove(position);
//        notifyDataSetChanged();
        notifyItemRemoved(position);//这样就只会删除这一条数据，而不会一直刷

    }

    /**
     * 插入数据
     * @param position 插入位置
     * @param data 插入的数据
     */
    public void insert(int position,String data){
        list.add(position,data);
        notifyItemInserted(position);

    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView text;

        public ViewHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.item_text);
        }
    }


    /**
     * 自定义RecyclerView的点击事件
     */
    interface OnRecyclerItemClickListener{
        void OnRecyclerItemClick(RecyclerView parent,View view,int position,String data);
    }

}
