package com.sagar.android_projects.dagger2.singleton;

import android.app.Application;

import com.sagar.android_projects.dagger2.di.component.ApplicationComponent;
import com.sagar.android_projects.dagger2.di.component.DaggerApplicationComponent;
import com.sagar.android_projects.dagger2.di.module.ContextModule;

/**
 * Created by sagar on 11/13/2017.
 */
public class AppSingleton extends Application {

    ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent.builder().contextModule(new ContextModule(this)).build();
        applicationComponent.injectApplication(this);

    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
