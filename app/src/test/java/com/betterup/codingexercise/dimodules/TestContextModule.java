package com.betterup.codingexercise.dimodules;

import android.app.Activity;
import android.content.Context;

import com.betterup.codingexercise.activities.MainActivity;
import com.betterup.codingexercise.application.BetterUpApplication;

import org.mockito.Mockito;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class TestContextModule {
    @Singleton
    @Provides
    public Context provideApplicationContext() {
        return BetterUpApplication.getInstance().getApplicationContext();
    }

    @Singleton
    @Provides
    public Activity provideActivity() {
        return Mockito.mock(MainActivity.class);
    }
}
