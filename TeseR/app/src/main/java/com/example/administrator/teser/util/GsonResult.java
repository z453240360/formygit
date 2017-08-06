package com.example.administrator.teser.util;

import com.example.administrator.teser.bean.ZiDianBean;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by Administrator on 2017/7/27.
 */

public class GsonResult {

    public static ZiDianBean getBean(String s) {

        Gson gson = new Gson();

        ZiDianBean ziDianBean = gson.fromJson(s, ZiDianBean.class);
        return ziDianBean;
    }

    public static ZiDianBean.ResultBean getWords(String s) {
        ZiDianBean bean = getBean(s);
        ZiDianBean.ResultBean result = bean.getResult();
        return result;
    }

    public static String getZi(String s) {
        ZiDianBean.ResultBean words = getWords(s);
        String zi = words.getZi();
        return zi;
    }

    public static String getPinYin(String s) {
        ZiDianBean.ResultBean words = getWords(s);
        String pinyin = words.getPinyin();
        return pinyin;
    }

    public static String getXiangJie(String s) {
        StringBuffer sp = new StringBuffer();
        ZiDianBean.ResultBean words = getWords(s);
        List<String> xiangjie = words.getXiangjie();
        for (int i = 0; i < xiangjie.size(); i++) {
            String s1 = xiangjie.get(i);
            sp.append(s1+"\n");
        }
        String s1 = sp.toString();
        return s1;
    }


}
