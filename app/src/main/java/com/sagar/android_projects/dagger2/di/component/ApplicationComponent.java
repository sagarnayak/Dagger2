package com.sagar.android_projects.dagger2.di.component;

import com.sagar.android_projects.dagger2.di.module.NetworkModule;
import com.sagar.android_projects.dagger2.di.module.PicassoModule;
import com.sagar.android_projects.dagger2.di.scope.ApplicationScope;
import com.sagar.android_projects.dagger2.retrofit.APIInterface;
import com.sagar.android_projects.dagger2.singleton.AppSingleton;
import com.squareup.picasso.Picasso;

import dagger.Component;

/**
 * Created by sagar on 11/13/2017.
 */
@ApplicationScope
@Component(modules = {PicassoModule.class, NetworkModule.class})
public interface ApplicationComponent {

    public APIInterface getApiInterface();

    public Picasso getPicasso();

    public void injectApplication(AppSingleton appSingleton);
}
