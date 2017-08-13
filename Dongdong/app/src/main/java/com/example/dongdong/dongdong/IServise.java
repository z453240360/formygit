package com.example.dongdong.dongdong;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by dongdong on 2017/8/12.
 */

public interface IServise {


    @FormUrlEncoded
    @POST("rest/2.0/ocr/v1/general_basic")
    Call<ResponseBody> getImg(@Field("image")String image);
}
