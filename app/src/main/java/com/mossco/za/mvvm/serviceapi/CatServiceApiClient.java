package com.mossco.za.mvvm.serviceapi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CatServiceApiClient {
    private static final String BASE_URL = "https://api.thecatapi.com/api/";
    public static CatServiceApi catServiceApi;

    private CatServiceApiClient() {

    }

    public static CatServiceApi getInstance() {
        Retrofit retrofit;
        if (catServiceApi == null) {
            Gson gson = new GsonBuilder().create();
            retrofit = new Retrofit.Builder().baseUrl(CatServiceApiClient.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson)).build();
            catServiceApi = retrofit.create(CatServiceApi.class);
        }
        return catServiceApi;
    }
}
