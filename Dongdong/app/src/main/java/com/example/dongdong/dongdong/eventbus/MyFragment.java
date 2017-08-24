package com.example.dongdong.dongdong.eventbus;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.dongdong.dongdong.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by dongdong on 2017/8/22.
 */

public class MyFragment extends Fragment {

    private Button button1;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onStart() {
        super.onStart();
//        EventBus.getDefault().register(this);
    }

    //创建视图
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_event1,container,false);
    }
    //
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        button1=(Button) view.findViewById(R.id.button8_1);
        Log.e("dd", "onViewCreated: "+Thread.currentThread());
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new EventBean("消息发送/////"));
            }
        });
    }
    //EventBus 接受消息
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getEvent(EventBean eventBean){
        String msg = eventBean.getMsg();
        Log.i("dd", "getEvent: "+msg);
        button1.setText(msg);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
