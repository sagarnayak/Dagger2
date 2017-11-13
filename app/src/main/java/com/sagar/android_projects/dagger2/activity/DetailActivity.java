package com.sagar.android_projects.dagger2.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import com.sagar.android_projects.dagger2.R;
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

/**
 * created by SAGAR NAYAK on 13 NOV 2017.
 * this is the activity to show the details of the git hub repo after user has selected a repo from
 * the list provided in the {@link MainActivity}.
 * this uses the DI for getting the {@link Picasso} and {@link APIInterface} for retrofit and showing
 * the user avatar.
 */
public class DetailActivity extends AppCompatActivity {

    /*
    appcompatimageview to show avatar
     */
    private AppCompatImageView appCompatImageViewAvatar;
    /*
    textview to show the user name
     */
    private TextView textViewName;
    /*
    api interface for rest api
     */
    @Inject
    public APIInterface apiIinterface;
    /*
    picasso for image loading
     */
    @Inject
    public Picasso picasso;

    /*
    const for sending the user name from the main activity.
     */
    public static final String USER_NAME = "USER_NAME";
    /*
    details activity component for DI
     */
    DetailsActivityComponent detailsActivityComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ////////////////////////////////////////////////////////////////////////////////////////////
        //view binding
        appCompatImageViewAvatar = findViewById(R.id.appcompatimageview_avatar);
        textViewName = findViewById(R.id.textview_name_of_user);
        ////////////////////////////////////////////////////////////////////////////////////////////

        /*
        get the dagger component and initialise the DetailsActivityComponent.
         */
        detailsActivityComponent = DaggerDetailsActivityComponent.builder()
                .applicationComponent(((AppSingleton) getApplicationContext()).getApplicationComponent())
                .build();

        /*
        start injecting the dependencies.
         */
        detailsActivityComponent.injectDetilsActivity(this);

        /*
        start the retrofit REST api call to the remote server for the user details.
        on successful reply from the server set the data to the UI.
         */
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

    /**
     * method to set the data from the serer to the UI.
     * @param detailProfile POJO of {@link DetailProfile} that have the data of user from server.
     */
    private void setDataToUI(DetailProfile detailProfile) {
        picasso.load(detailProfile.getAvatarUrl()).into(appCompatImageViewAvatar);
        textViewName.setText(detailProfile.getLogin());
    }

}
