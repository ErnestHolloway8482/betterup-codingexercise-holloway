package com.betterup.codingexercise.dimodules;

import android.app.Activity;
import android.content.Context;
import android.support.test.InstrumentationRegistry;

import com.betterup.codingexercise.activities.MainActivity;

import dagger.Module;

@Module
public class TestContextModule extends ContextModule {
    @Override
    public Context provideApplicationContext() {
        return InstrumentationRegistry.getContext();
    }

    @Override
    public Activity provideActivity() {
        try {
            return MainActivity.class.newInstance();
        } catch (Exception e) {
            return null;
        }
    }
}
