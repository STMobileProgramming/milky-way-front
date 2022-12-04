package com.example.milkyway.api;

import com.example.milkyway.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    public static RetrofitService getApiService() {
        return getInstance().create(RetrofitService.class);
    }

    private static Retrofit getInstance() {
        Gson gson = new GsonBuilder().setLenient().create();
        return new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/api/v1/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

}
