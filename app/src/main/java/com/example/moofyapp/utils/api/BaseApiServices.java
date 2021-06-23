package com.example.moofyapp.utils.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface BaseApiServices {

    @FormUrlEncoded
    @POST("auth_api")
    Call<ResponseBody> authapimoofy(@Field("email") String email,
                               @Field("password") String password);

    @FormUrlEncoded
    @POST("register_api")
    Call<ResponseBody> addRegisterData(@Field("name") String name,
                                       @Field("email") String email,
                                       @Field("password") String password);

}
