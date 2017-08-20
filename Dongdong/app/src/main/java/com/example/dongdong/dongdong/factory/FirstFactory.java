package com.example.dongdong.dongdong.factory;

import android.util.Log;

import com.google.gson.Gson;

/**
 * Created by dongdong on 2017/8/19.
 */

public class FirstFactory implements IFactoryModel {
    @Override
    public void factoryMethed(String s) {
        Log.i("dd", "FirstFactory: "+s);

        Gson g = new Gson();

        MyBean myBean = g.fromJson(s, MyBean.class);

        String id = myBean.getId();
    }
}
