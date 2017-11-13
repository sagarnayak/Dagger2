package com.sagar.android_projects.dagger2.di.module;

import android.content.Context;

import com.sagar.android_projects.dagger2.di.scope.ApplicationScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by sagar on 11/13/2017.
 */
@Module
public class ContextModule {
    private Context context;

    public ContextModule(Context context) {
        this.context = context;
    }

    @Provides
    @ApplicationScope
    public Context getContext() {
        return context;
    }
}
