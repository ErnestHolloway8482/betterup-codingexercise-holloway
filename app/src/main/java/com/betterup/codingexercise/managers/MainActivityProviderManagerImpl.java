package com.betterup.codingexercise.managers;

import com.betterup.codingexercise.activities.MainActivity;

import javax.inject.Singleton;

@Singleton
public class MainActivityProviderManagerImpl implements MainActivityProviderManager {
    @Override
    public MainActivity provideMainActivity() {
        return MainActivity.getInstance();
    }
}
