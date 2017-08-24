package com.example.dongdong.dongdong.recylerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.example.dongdong.dongdong.R;

import java.util.ArrayList;
import java.util.List;


/**
 * ***********************************************************
 * author: Randy
 * time: 上午9:23
 * name: RecyclerView 的基本使用
 * desc: 就是代替ListView的控件,比ListView更强大,更灵活。 增加动画效果,专注于View的复用
 * step:
 * 1, bulid.gradle 导包:
 * 2. 在布局文件中引入: android.support.v7.widget.RecyclerView
 * 3. 数据集
 * 4. 适配器
 * 5. 初始化布局管理器LayoutManager
 * <p>
 * *************************************************************
 */
public class LikeListActivity extends AppCompatActivity {

    private List<ItemModel> mDatas = new ArrayList<>();

    private RecyclerView mRecyclerView;
    private Button btn_add;
    private Button btn_remove;
    private LikeListAdapter mAdapter;
    private ArrayList<Integer> list = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_likelist);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        btn_add = (Button) findViewById(R.id.btn_add);
        btn_remove = (Button) findViewById(R.id.btn_remove);


        //1.准备数据集
        for (int i = 0; i < 50; i++) {
            mDatas.add(new ItemModel());
        }

        //2.初始化Adapter
        mAdapter = new LikeListAdapter(this, mDatas);

        mRecyclerView.setAdapter(mAdapter);

        //3.创建出来LayoutManager

        //3.1 线性布局: 第二个参数是 列表展现的方向,有竖直的LinearLayoutManager.VERTICAL 和 水平的LinearLayoutManager.HORIZONTAL
        // 第三个参数: 是否 从最后展示
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        //3.2 网格布局:  参数2是 表示这个网格布局的列数 或者行数
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        //3.3 瀑布流布局
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        //给RecyclerView增加一些动画效果
//        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        //1. build.gradle中引入 : compile 'jp.wasabeef:recyclerview-animators:2.2.5'
        //2. 添加动画效果 :  setItemAnimator(动画效果的类例如: SlideInLeftAnimator, SlideInRightAnimator);
//        mRecyclerView.setItemAnimator(new LandingAnimator());
        //3. 改变动画的时长:  有四个:
        // recyclerView.getItemAnimator().setAddDuration(1000);
        //recyclerView.getItemAnimator().setRemoveDuration(1000);
        //recyclerView.getItemAnimator().setMoveDuration(1000);
        //recyclerView.getItemAnimator().setChangeDuration(1000);

        mRecyclerView.getItemAnimator().setAddDuration(3000);
        //增加一条数据
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.insertData(1, "新增加的数据");
            }
        });
        //删除一条数据
        btn_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.removeData(1);
            }
        });


//        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));


        mAdapter.setOnItemClickListener(new LikeListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos, View view) {
                Toast.makeText(LikeListActivity.this, "pos::" + pos, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCheckChecked(int pos) {
                Toast.makeText(LikeListActivity.this, "pos::" + pos, Toast.LENGTH_SHORT).show();
//                notifyAll();
                if (list.contains(pos)){
                    list.remove(pos);
                }else {
                    list.add(pos);
                }

                if (list.size()==0)
                {

                }
            }
        });
    }


    public void addOrRemove(int postion){

    }
}
