package com.sagar.android_projects.dagger2.di.module;

import android.content.Context;

import com.sagar.android_projects.dagger2.di.scope.ApplicationScope;
import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;

/**
 * Created by sagar on 11/13/2017.
 */
@Module(includes = {ContextModule.class})
public class PicassoModule {
    @Provides
    @ApplicationScope
     Picasso getPicasso(Context context) {
        return Picasso.with(context.getApplicationContext());
    }
}
