package com.sagar.android_projects.dagger2.di.module;

import com.sagar.android_projects.dagger2.activity.MainActivity;
import com.sagar.android_projects.dagger2.di.scope.MainActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by sagar on 11/13/2017.
 */
@Module
public class MainActivityContextModule {
    private MainActivity mainActivity;

    public MainActivityContextModule(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Provides
    @MainActivityScope
    public MainActivity getMainActivity() {
        return mainActivity;
    }
}
