package com.example.dongdong.dongdong.recylerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;


import com.example.dongdong.dongdong.R;

import java.util.ArrayList;
import java.util.List;

public class LikeListAdapter extends RecyclerView.Adapter<LikeListAdapter.MyViewHolder> {
    private List<ItemModel> mDatas = new ArrayList<>();
    private Context mContext;
    private LayoutInflater mInflater;

    public LikeListAdapter(Context context, List<ItemModel> datas) {
        this.mInflater = LayoutInflater.from(context);
        mDatas = datas;
        this.mContext = context;
    }

    //返回RecyclerView总共有多少条
    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    //初始化布局,创建ViewHolder
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_likelist, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    //绑定ViewHolder中的控件的数据
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final ItemModel itemModel = mDatas.get(position);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(position, holder.itemView);
                }
            }
        });

        holder.checkBox.setChecked(itemModel.isSelect);

        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemModel.isSelect = holder.checkBox.isChecked();
                mListener.onCheckChecked(position);
            }
        });
        holder.tv_test.setText(holder.checkBox.isChecked()+"");




    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_test;
        private CheckBox checkBox;
        public MyViewHolder(View itemView) {
            super(itemView);
            tv_test = (TextView) itemView.findViewById(R.id.tv_test);
            checkBox=(CheckBox) itemView.findViewById(R.id.checkbox);
        }
    }

    //增加一条数据
    public void insertData(int pos, String data) {
//        mDatas.add(pos, data);
        notifyItemInserted(pos);
    }

    //删除一条数据
    public void removeData(int pos) {
        mDatas.remove(pos);
        notifyItemRemoved(pos);
    }

    public void getChecked(int pos) {
        mDatas.remove(pos);
        notifyItemRemoved(pos);
    }


    public interface OnItemClickListener {
        void onItemClick(int pos, View view);
        void onCheckChecked(int pos);
    }

    private OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }



}
