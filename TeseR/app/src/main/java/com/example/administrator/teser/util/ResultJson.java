package com.example.administrator.teser.util;

import com.example.administrator.teser.bean.ZiDianBean;
import com.google.gson.Gson;

/**
 * Created by Administrator on 2017/7/24.
 */

public class ResultJson {

    public static ZiDianBean.ResultBean getBean(String s){
        Gson gson = new Gson();
        ZiDianBean ziDianBean = gson.fromJson(s, ZiDianBean.class);
        ZiDianBean.ResultBean result = ziDianBean.getResult();
        return result;
    }

}
