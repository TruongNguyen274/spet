package com.example.spetsmobile.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {

//    public static String HOST_URL = "http://192.168.31.179:8888";
    public static String HOST_URL = "http://192.168.1.4:8888";
    private static Retrofit retrofit = null;

    public static Retrofit getClient(String baseUrl) {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static APIService getAPIService() {
        return getClient(HOST_URL).create(APIService.class);
    }

}
