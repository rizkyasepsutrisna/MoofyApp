package com.example.moofyapp.utils.api;

public class UtilsApi {
    // Ganti API sesuai ip address Masing"
    public static final String BASE_URL_API = "http://182.158.1.108/Moofy/api/";

    // Mendeklarasikan Interface BaseApiService
    public static BaseApiServices getAPIService(){
        return RetrofitClient.getClient(BASE_URL_API).create(BaseApiServices.class);
    }
}
