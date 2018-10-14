package com.betterup.codingexercise.dimodules;

import android.app.Activity;
import android.content.Context;

import com.betterup.codingexercise.activities.MainActivity;
import com.betterup.codingexercise.application.BetterUpApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {
    @Singleton
    @Provides
    public Context provideApplicationContext() {
        return BetterUpApplication.getInsance();
    }

    @Singleton
    @Provides
    public Activity provideActivity() {
        return MainActivity.getInstance();
    }
}
