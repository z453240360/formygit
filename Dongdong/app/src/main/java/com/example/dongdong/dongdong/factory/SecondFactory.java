package com.example.dongdong.dongdong.factory;

import android.util.Log;

/**
 * Created by dongdong on 2017/8/19.
 */

public class SecondFactory implements IFactoryModel {
    @Override
    public void factoryMethed(String s) {
        Log.i("dd", "factoryMethed: "+s);
    }
}
