package com.sagar.android_projects.dagger2.retrofit;

import com.sagar.android_projects.dagger2.pojo.DetailProfile;
import com.sagar.android_projects.dagger2.pojo.RepoResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by sagar on 11/13/2017.
 */
public interface APIInterface {

    @GET("/repositories")
    Call<ArrayList<RepoResponse>> getRepoList();

    @GET("/users/{userName}")
    Call<DetailProfile> getUserDetail(@Path("userName") String userName);
}
