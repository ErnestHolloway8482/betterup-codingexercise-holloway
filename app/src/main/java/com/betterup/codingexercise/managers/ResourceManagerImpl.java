package com.betterup.codingexercise.managers;

import com.betterup.codingexercise.activities.MainActivity;

import javax.inject.Singleton;

@Singleton
public class ResourceManagerImpl implements ResourceManager {
    @Override
    public String getString(final int resourceId) {
        return MainActivity.getInstance().getString(resourceId);
    }
}
