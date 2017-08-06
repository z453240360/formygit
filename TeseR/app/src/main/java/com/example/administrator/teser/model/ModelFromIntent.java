package com.example.administrator.teser.model;


import com.example.administrator.teser.Constant;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2017/7/24.
 */

public interface ModelFromIntent {

    @GET(Constant.ZIDIAN)
    Call<ResponseBody> getZidian(@Query("word") String word, @Query("key") String key);

}
