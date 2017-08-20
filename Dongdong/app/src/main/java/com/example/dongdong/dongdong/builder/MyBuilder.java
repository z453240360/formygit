package com.example.dongdong.dongdong.builder;

import com.example.dongdong.dongdong.factory.FirstFactory;
import com.example.dongdong.dongdong.factory.IFactoryModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dongdong on 2017/8/19.
 */

public class MyBuilder {

    private List<IFactoryModel> list= new ArrayList<>();


    public void produceFirst(){
        for (int i = 0; i < list.size(); i++) {
            list.add(new FirstFactory());
        }
    }

    public void getSe(){

    }


}
