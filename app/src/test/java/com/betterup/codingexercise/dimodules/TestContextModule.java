package com.betterup.codingexercise.dimodules;

import android.app.Activity;
import android.content.Context;

import com.betterup.codingexercise.activities.MainActivity;
import com.betterup.codingexercise.application.BetterUpApplication;
import com.betterup.codingexercise.models.viewmodels.MainActivityVM;

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
        MainActivity mainActivity = Mockito.mock(MainActivity.class);
        Mockito.when(mainActivity.getViewModel()).thenReturn(Mockito.mock(MainActivityVM.class));

        return Mockito.mock(MainActivity.class);
    }
}
