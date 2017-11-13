package com.sagar.android_projects.dagger2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import com.sagar.android_projects.dagger2.di.component.DaggerDetailsActivityComponent;
import com.sagar.android_projects.dagger2.di.component.DetailsActivityComponent;
import com.sagar.android_projects.dagger2.pojo.DetailProfile;
import com.sagar.android_projects.dagger2.retrofit.APIInterface;
import com.sagar.android_projects.dagger2.singleton.AppSingleton;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    private AppCompatImageView appCompatImageViewAvatar;
    private TextView textViewName;
    @Inject
    public APIInterface apiIinterface;
    @Inject
    public Picasso picasso;

    public static final String USER_NAME = "USER_NAME";

    DetailsActivityComponent detailsActivityComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        appCompatImageViewAvatar = findViewById(R.id.appcompatimageview_avatar);
        textViewName = findViewById(R.id.textview_name_of_user);

        detailsActivityComponent = DaggerDetailsActivityComponent.builder()
                .applicationComponent(((AppSingleton) getApplicationContext()).getApplicationComponent())
                .build();

        detailsActivityComponent.injectDetilsActivity(this);

        apiIinterface.getUserDetail(getIntent().getStringExtra(USER_NAME)).enqueue(new Callback<DetailProfile>() {
            @Override
            public void onResponse(Call<DetailProfile> call, Response<DetailProfile> response) {
                if (response.isSuccessful()) {
                    setDataToUI(response.body());
                    return;
                }
                Toast.makeText(DetailActivity.this, "error " + response.raw().code(), Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<DetailProfile> call, Throwable t) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private void setDataToUI(DetailProfile detailProfile) {
        picasso.load(detailProfile.getAvatarUrl()).into(appCompatImageViewAvatar);
        textViewName.setText(detailProfile.getLogin());
    }

}
