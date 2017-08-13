package com.example.dongdong.dongdong;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit r= new Retrofit.Builder()
                .baseUrl("https://aip.baidubce.com/")
                .build();

        IServise iServise = r.create(IServise.class);






        Call<ResponseBody> img = iServise.getImg(Constant.image);


    }
}
