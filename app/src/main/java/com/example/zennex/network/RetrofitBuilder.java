package com.example.zennex.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {

    private ParsingAPI sParsingAPI;
    private Retrofit mRetrofit;

    public RetrofitBuilder() {

        mRetrofit = new Retrofit.Builder()
                .baseUrl("http://quotes.zennex.ru/api/v3/bash/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        sParsingAPI = mRetrofit.create(ParsingAPI.class);
    }

    public ParsingAPI getAPI() {
        return sParsingAPI;
    }
}
