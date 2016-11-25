package com.example.ivleshch.listview_recyclerviev.git.data;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubService {
    @GET("/users/{username}")
    Call<GitModel> getUser(@Path("username") String username);
}
