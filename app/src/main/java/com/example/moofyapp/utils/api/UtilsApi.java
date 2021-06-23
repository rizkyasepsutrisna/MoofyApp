package com.example.moofyapp.utils.api;

public class UtilsApi {
    // 10.0.2.2 ini adalah localhost.
    public static final String BASE_URL_API = "http://182.158.1.108/Moofy/api/";

    // Mendeklarasikan Interface BaseApiService
    public static BaseApiServices getAPIService(){
        return RetrofitClient.getClient(BASE_URL_API).create(BaseApiServices.class);
    }
}
