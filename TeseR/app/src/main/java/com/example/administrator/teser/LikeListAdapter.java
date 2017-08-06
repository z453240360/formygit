package com.example.administrator.teser;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * ***********************************************************
 * author: Randy
 * time: 上午9:31
 * name: RecyclerView的Adapter的写法
 * desc:
 * step:
 * 1. 继承RecyclerView.Adapter<VH extends ViewHolder>
 * 2. 在这个Adapter中写一个类继承RecyclerView.ViewHolder,并且传入Adapter的泛型
 * 3. 实现 三个抽象方法
 * getItemCount
 * onCreateViewHolder
 * onBindViewHolder
 * 4. 重写构造方法,参数传入 上下文Context 数据集
 * *************************************************************
 */
public class LikeListAdapter extends RecyclerView.Adapter<LikeListAdapter.MyViewHolder> {
    private List<String> mDatas = new ArrayList<>();
    private Context mContext;
    private LayoutInflater mInflater;

    public LikeListAdapter(Context context, List<String> datas) {
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
        //1. 初始化item的布局
        View view = mInflater.inflate(R.layout.item_likelist, parent, false);
        //2. 创建出来ViewHolder对象
        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    //绑定ViewHolder中的控件的数据
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        String str = mDatas.get(position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(mContext, "pos:" + position, Toast.LENGTH_SHORT).show();
                if (mListener != null) {
                    mListener.onItemClick(position, holder.itemView);
                }
            }
        });

        //瀑布流展示
//        Random random = new Random();
//        int height = random.nextInt(100) + 100;
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height);
//        ViewGroup.LayoutParams layoutParams = holder.tv_test.getLayoutParams();
//        layoutParams.height = height;
//        holder.tv_test.setLayoutParams(layoutParams);

        holder.tv_test.setText(str);
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_test;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_test = (TextView) itemView.findViewById(R.id.tv_test);
        }
    }

    //增加一条数据
    public void insertData(int pos, String data) {
        mDatas.add(pos, data);

        notifyItemInserted(pos);
    }

    //删除一条数据
    public void removeData(int pos) {
        mDatas.remove(pos);
        notifyItemRemoved(pos);
    }


    public interface OnItemClickListener {
        void onItemClick(int pos, View view);
    }

    private OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

}
