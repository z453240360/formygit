package com.example.administrator.teser.model;


import com.example.administrator.teser.Constant;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HEAD;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2017/7/24.
 */

public interface ModelFromIntent {

    @GET(Constant.ZIDIAN)
    Call<ResponseBody> getZidian(@Query("word") String word, @Query("key") String key);


    @FormUrlEncoded
    @Headers("Content-Type:application/x-www-form-urlencoded")
    @POST(Constant.OCT)
    Call<ResponseBody> getOcr(@Field("image") String word,@Field("access_token")String token);
}
