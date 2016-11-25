package com.example.ivleshch.listview_recyclerviev.google.data;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GoogleService {
    @GET("/plus/v1/people/{username}")
    Call<GoogleModel> getUser(@Path("username") String username, @Query("key") String key);
}