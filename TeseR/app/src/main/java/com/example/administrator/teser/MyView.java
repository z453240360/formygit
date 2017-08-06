package com.example.administrator.teser;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SynthesizerListener;

/**
 * Created by dongdong on 2017/8/6.
 */

public class MyView extends LinearLayout{
    private View view;
    private TextView mTxt_2,mTxt_3,mTxt_4,mTxt_1;
    private ImageView mBtn_txtshow,img1,img2;
    private SpeechSynthesizer ss;

    public MyView(Context context) {
        super(context);
        init(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void init(Context context)
    {
        view = LayoutInflater.from(context).inflate(R.layout.txtfragment, null);
        addView(view);
        initView();
        ss = SpeechSynthesizer.createSynthesizer(context,null);
    }

    private void initView() {

        mTxt_1 = (TextView) view.findViewById(R.id.mTxt_1);
        mTxt_2 = (TextView) view.findViewById(R.id.mTxt_2);
        mTxt_3 = (TextView) view.findViewById(R.id.mTxt_3);
        mTxt_4 = (TextView) view.findViewById(R.id.mTxt_4);

        mBtn_txtshow = (ImageView) view.findViewById(R.id.mBtn_txtshow);
        img1 = (ImageView) view.findViewById(R.id.img1);
        img2 = (ImageView) view.findViewById(R.id.img2);


    }


    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.mBtn_txtshow:

                textToVoice("怒号啊");
                Log.i("dd", "onClick: "+"ssssssssss");

                break;

            case R.id.img1:
                setTxt1("sss");
                Log.i("dd", "onClick: ");
                break;

            case R.id.img2:
                mTxt_2.setText("我是下一页");
                break;
        }
    }

    //语音合成
    private void textToVoice(String text) {
        ss.setParameter(SpeechConstant.SPEED,"5");
        ss.setParameter(SpeechConstant.VOLUME,"100");
        ss.startSpeaking(text, new SynthesizerListener() {
            @Override
            public void onSpeakBegin() {
            }

            @Override
            public void onBufferProgress(int i, int i1, int i2, String s) {

            }

            @Override
            public void onSpeakPaused() {

            }

            @Override
            public void onSpeakResumed() {

            }

            @Override
            public void onSpeakProgress(int i, int i1, int i2) {

            }

            @Override
            public void onCompleted(SpeechError speechError) {

            }

            @Override
            public void onEvent(int i, int i1, int i2, Bundle bundle) {

            }
        });
    }

    public void setTxt1(String s){
        mTxt_1.setText("woshi上一页");

    }

}
