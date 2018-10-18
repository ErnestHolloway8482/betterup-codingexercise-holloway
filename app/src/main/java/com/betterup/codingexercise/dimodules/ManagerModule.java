package com.betterup.codingexercise.dimodules;

import android.app.Activity;
import android.content.Context;

import com.betterup.codingexercise.managers.AlertDialogManager;
import com.betterup.codingexercise.managers.AlertDialogManagerImpl;
import com.betterup.codingexercise.managers.DatabaseManager;
import com.betterup.codingexercise.managers.DatabaseManagerImpl;
import com.betterup.codingexercise.managers.ImageCacheManager;
import com.betterup.codingexercise.managers.ImageCacheManagerImpl;
import com.betterup.codingexercise.managers.MainActivityProviderManager;
import com.betterup.codingexercise.managers.MainActivityProviderManagerImpl;
import com.betterup.codingexercise.managers.NavigationManager;
import com.betterup.codingexercise.managers.NavigationManagerImpl;
import com.betterup.codingexercise.managers.NetworkManager;
import com.betterup.codingexercise.managers.NetworkManagerImpl;
import com.betterup.codingexercise.managers.ResourceManager;
import com.betterup.codingexercise.managers.ResourceManagerImpl;
import com.betterup.codingexercise.managers.ScreenManager;
import com.betterup.codingexercise.managers.ScreenManagerImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ManagerModule {
    @Singleton
    @Provides
    public static AlertDialogManager provideAlertDialogManager(final Activity activity) {
        return new AlertDialogManagerImpl(activity);
    }

    @Singleton
    @Provides
    public static DatabaseManager provideDatabaseManager(final Context context) {
        return new DatabaseManagerImpl(context);
    }

    @Singleton
    @Provides
    public static ImageCacheManager provideImageCacheManager(final Context context) {
        return new ImageCacheManagerImpl(context);
    }

    @Singleton
    @Provides
    public static NavigationManager provideNavigationManager() {
        return new NavigationManagerImpl();
    }

    @Singleton
    @Provides
    public static NetworkManager provideNetworkManager(final Context context) {
        return new NetworkManagerImpl(context);
    }

    @Singleton
    @Provides
    public static ResourceManager provideResourceManager() {
        return new ResourceManagerImpl();
    }

    @Singleton
    @Provides
    public static ScreenManager provideScreenManager() {
        return new ScreenManagerImpl();
    }

    @Singleton
    @Provides
    public static MainActivityProviderManager provideMainActivityProviderManager() {
        return new MainActivityProviderManagerImpl();
    }
}
