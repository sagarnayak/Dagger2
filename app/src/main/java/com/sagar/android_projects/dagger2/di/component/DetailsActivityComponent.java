package com.sagar.android_projects.dagger2.di.component;

import com.sagar.android_projects.dagger2.DetailActivity;
import com.sagar.android_projects.dagger2.di.module.DetailsActivityModule;
import com.sagar.android_projects.dagger2.di.scope.DetailsActivityScope;

import dagger.Component;

/**
 * Created by sagar on 11/13/2017.
 */

@Component(modules = DetailsActivityModule.class, dependencies = ApplicationComponent.class)
@DetailsActivityScope
public interface DetailsActivityComponent {
    void injectDetilsActivity(DetailActivity detailActivity);
}
