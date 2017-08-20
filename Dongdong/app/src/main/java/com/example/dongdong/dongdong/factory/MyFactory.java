package com.example.dongdong.dongdong.factory;

/**
 * Created by dongdong on 2017/8/19.
 */

public class MyFactory {


//    public IFactoryModel getProduct(String s){
//        return new FirstFactory();
//    }

    public static FirstFactory getFirstFactory(){
        return new FirstFactory();
    }

    public static SecondFactory getSecondFactory(){
        return new SecondFactory();
    }
}
