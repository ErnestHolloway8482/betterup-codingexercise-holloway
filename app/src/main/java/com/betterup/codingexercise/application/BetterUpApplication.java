package com.betterup.codingexercise.application;

import android.app.Application;

import com.betterup.codingexercise.dimodules.AppComponent;
import com.betterup.codingexercise.dimodules.DaggerAppComponent;

public class BetterUpApplication extends Application {
    private static AppComponent appComponent;
    private static BetterUpApplication instance;

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

    }

    private void cleanUp() {
        instance = null;
    }
}
