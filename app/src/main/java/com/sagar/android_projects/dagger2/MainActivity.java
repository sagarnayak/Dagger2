package com.sagar.android_projects.dagger2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.sagar.android_projects.dagger2.adapter.AdapterRepoList;
import com.sagar.android_projects.dagger2.di.component.ApplicationComponent;
import com.sagar.android_projects.dagger2.di.component.DaggerMainActivityComponent;
import com.sagar.android_projects.dagger2.di.component.MainActivityComponent;
import com.sagar.android_projects.dagger2.di.module.MainActivityContextModule;
import com.sagar.android_projects.dagger2.pojo.RepoResponse;
import com.sagar.android_projects.dagger2.retrofit.APIInterface;
import com.sagar.android_projects.dagger2.singleton.AppSingleton;

import java.util.ArrayList;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements AdapterRepoList.CallBack {

    @SuppressWarnings("FieldCanBeLocal")
    private RecyclerView recyclerView;
    public static final String TAG = "OkHttp";

    MainActivityComponent mainActivityComponent;

    @Inject
    public AdapterRepoList adapterRepoList;

    @Inject
    public APIInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        ApplicationComponent applicationComponent = ((AppSingleton) getApplicationContext()).getApplicationComponent();
        mainActivityComponent = DaggerMainActivityComponent.builder()
                .mainActivityContextModule(new MainActivityContextModule(this))
                .applicationComponent(applicationComponent)
                .build();

        mainActivityComponent.injectMainActivity(this);

        recyclerView.setAdapter(adapterRepoList);

        apiInterface.getRepoList().enqueue(new Callback<ArrayList<RepoResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<RepoResponse>> call, Response<ArrayList<RepoResponse>> response) {
                setDataToRecyclerview(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<RepoResponse>> call, Throwable t) {

            }
        });
    }

    private void setDataToRecyclerview(ArrayList<RepoResponse> response) {
        adapterRepoList.setData(response);
    }

    @Override
    public void startDetailActivity(String userName) {
        startActivity(new Intent(this, DetailActivity.class).putExtra(DetailActivity.USER_NAME, userName));
    }
}
