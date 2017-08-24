package com.example.dongdong.dongdong.SingGetInstance;

/**
 * Created by dongdong on 2017/8/19.
 */

public class MyIntence {

//      完美写法
//    //私有化构造方法
//    private MyIntence(){
//
//    }
//
//    //创建单利对象
//    public static MyIntence getInstance(){
//        return SingFactory.intence;
//    }
//    //内部类控制创建实例
//    private static class SingFactory{
//        private static MyIntence intence = new MyIntence();
//    }
//
//    //保证被序列化的前后保持一致
//    public Object readR(){
//        return getInstance();
//    }

    private MyIntence(){};

    private static MyIntence intence=null;

    public static MyIntence getInstance(){
        if (intence==null)
        {
            intence=new MyIntence();
        }
        return intence;
    }

    public String getMyInstanceMethed(){
        return "我是单利模式";
    }


}
