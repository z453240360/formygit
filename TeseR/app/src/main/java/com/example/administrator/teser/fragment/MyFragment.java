package com.example.administrator.teser.fragment;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.method.MovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.teser.R;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SynthesizerListener;

/**
 * Created by Administrator on 2017/7/27.
 */

public class MyFragment extends Fragment {

    private TextView mTxt1,mTxt2,mTxt3,mTxt4;

    private ImageView mBtn_second,img1,img2;

    private StringBuffer new2;

    private SpeechSynthesizer ss;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.txtfragment,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ss = SpeechSynthesizer.createSynthesizer(getActivity(),null);

        new2 = new StringBuffer();

        final String string1 = getArguments().getString("zi");
        String string2 = getArguments().getString("pinYin");
        String string3 = getArguments().getString("xiangJie");

        String[] split = string2.split(",");
        for (int i = 0; i < split.length; i++) {
            if (i==split.length) {
                new2.append(split[i]);
            }
            else {
                new2.append(split[i] + "\n");
            }
        }


        mTxt1 = (TextView)view.findViewById(R.id.mTxt_1);
        mTxt2 = (TextView)view.findViewById(R.id.mTxt_2);
        mTxt3 = (TextView)view.findViewById(R.id.mTxt_3);
        mTxt4 = (TextView)view.findViewById(R.id.mTxt_4);

        mTxt2.setMovementMethod(ScrollingMovementMethod.getInstance());
        mTxt4.setMovementMethod(ScrollingMovementMethod.getInstance());

        mTxt1.setText(string1);
        mTxt2.setText(new2.toString());
        mTxt4.setText(string3);


        img1 = (ImageView) view.findViewById(R.id.img1);
        img2 = (ImageView) view.findViewById(R.id.img1);
        mBtn_second = (ImageView) view.findViewById(R.id.mBtn_txtshow);
        mBtn_second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textToVoice(string1);
                SystemClock.sleep(1000);
                textToVoice(string1);
                SystemClock.sleep(1000);
                textToVoice(string1);
            }
        });

        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.getText("上一页");
            }
        });

        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.getText("下一页");
            }
        });


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


    @Override
    public void onDestroy() {
        super.onDestroy();
        ss.stopSpeaking();
    }


    //1. 创建一个接口以及抽象方法
    public interface TextCallback {
        //将想要传递出去的数据作为抽象方法的参数列表
        void getText (String str);
    }

    //2. 定义一个接口类型的变量，并给该变量进行初始化操作
    private TextCallback callback;
    public void setCallback(TextCallback callback) {
        this.callback = callback;
    }
}
