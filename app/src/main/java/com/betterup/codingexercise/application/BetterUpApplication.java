package com.betterup.codingexercise.application;

import android.app.Application;

import com.betterup.codingexercise.bindings.CommonViewBindings;
import com.betterup.codingexercise.dimodules.AppComponent;
import com.betterup.codingexercise.dimodules.DaggerAppComponent;
import com.betterup.codingexercise.managers.ImageCacheManager;

import javax.inject.Inject;

public class BetterUpApplication extends Application {
    private static AppComponent appComponent;
    private static BetterUpApplication instance;

    @Inject
    ImageCacheManager imageCacheManager;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        //Sets up the Dagger dependency injection graph for the entire application.
        appComponent = DaggerAppComponent.builder().build();

        setup();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();

        cleanUp();
    }

    public static BetterUpApplication getInstance() {
        return instance;
    }

    /**
     * @return a singleton reference of the Dagger dependency graph.
     */
    public static AppComponent getAppComponent() {
        return appComponent;
    }

    private void setup() {
        appComponent.inject(this);

        CommonViewBindings.setImageCacheManager(imageCacheManager);

    }

    private void cleanUp() {
        CommonViewBindings.setImageCacheManager(null);

        instance = null;
    }
}
