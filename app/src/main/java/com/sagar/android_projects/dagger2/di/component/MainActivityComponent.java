package com.sagar.android_projects.dagger2.di.component;

import com.sagar.android_projects.dagger2.activity.MainActivity;
import com.sagar.android_projects.dagger2.di.module.MainActivityModule;
import com.sagar.android_projects.dagger2.di.scope.MainActivityScope;

import dagger.Component;

/**
 * Created by sagar on 11/13/2017.
 */
@MainActivityScope
@Component(modules = MainActivityModule.class, dependencies = ApplicationComponent.class)
public interface MainActivityComponent {

    void injectMainActivity(MainActivity mainActivity);
}
