package com.example.dongdong.dongdong.eventbus;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.dongdong.dongdong.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MeEventBuss extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me_event_buss);
        EventBus.getDefault().register(this);
        EventBus.getDefault().post(new EventBean("消息发送"));
        Log.i("dd", "onCreate: "+ Thread.currentThread());


    }
    //EventBus 接受消息
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getEvent(EventBean eventBean){
        String msg = eventBean.getMsg();
        Log.i("dd", "onCreategetEvent: "+msg);
    }

    public void onClick(View view) {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().add(R.id.frame1,new MyFragment()).commit();
    }
}
