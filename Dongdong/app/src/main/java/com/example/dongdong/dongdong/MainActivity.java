package com.example.dongdong.dongdong;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private TextView mTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mTxt = (TextView) findViewById(R.id.mTxt);


        Drawable ss = getResources().getDrawable(R.mipmap.ic_launcher);
        ss.setBounds(0,0,50,50);


        mTxt.setCompoundDrawables(ss,null,null,null);
    }
}
