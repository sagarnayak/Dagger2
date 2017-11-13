package com.sagar.android_projects.dagger2.di.module;

import com.sagar.android_projects.dagger2.activity.MainActivity;
import com.sagar.android_projects.dagger2.adapter.AdapterRepoList;
import com.sagar.android_projects.dagger2.di.scope.MainActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by sagar on 11/13/2017.
 */
@Module(includes = {MainActivityContextModule.class})
public class MainActivityModule {

    @Provides
    @MainActivityScope
    public AdapterRepoList getAdapterRepoList(AdapterRepoList.CallBack callBack) {
        return new AdapterRepoList(callBack);
    }

    @Provides
    @MainActivityScope
    public AdapterRepoList.CallBack getCallback(MainActivity mainActivity) {
        return mainActivity;
    }
}
