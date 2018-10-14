package com.betterup.codingexercise.application;

import android.app.Application;

public class BetterUpApplication extends Application {
    private static BetterUpApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        setup();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();

        cleanUp();
    }

    public static BetterUpApplication getInsance(){
        return instance;
    }

    private void setup(){

    }

    private void cleanUp(){
        instance = null;
    }
}
