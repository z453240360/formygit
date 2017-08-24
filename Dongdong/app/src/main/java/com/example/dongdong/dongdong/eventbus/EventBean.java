package com.example.dongdong.dongdong.eventbus;

/**
 * Created by dongdong on 2017/8/22.
 */

public class EventBean {
    private String msg;

    public EventBean(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
