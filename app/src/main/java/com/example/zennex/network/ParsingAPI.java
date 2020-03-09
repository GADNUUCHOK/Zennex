package com.example.zennex.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ParsingAPI {

    @GET("quotes")
    Call<ObjectResponse> getData (
            @Query("sort") String sort);
}
