package com.sagar.android_projects.dagger2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.sagar.android_projects.dagger2.R;
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

/**
 * created by SAGAR NAYAK on 13 NOV 2017.
 * this is the launcher activity of the app.
 * this activity get all the public repo list from the remote url and show it in a list.
 * on clicking the item the details of the user will be shown in {@link DetailActivity}.
 * the interface {@link APIInterface} is used for calling the apis along with the {@link retrofit2.Retrofit}
 * for REST API call.
 */
public class MainActivity extends AppCompatActivity implements AdapterRepoList.CallBack {

    /*
    recyclerview for showing the data
     */
    @SuppressWarnings("FieldCanBeLocal")
    private RecyclerView recyclerView;
    public static final String TAG = "OkHttp";

    /*
    DI component.
     */
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
