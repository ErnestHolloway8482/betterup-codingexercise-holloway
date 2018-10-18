package com.betterup.codingexercise.dimodules;

import android.app.Activity;
import android.content.Context;

import com.betterup.codingexercise.activities.MainActivity;
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
import com.betterup.codingexercise.models.viewmodels.MainActivityVM;
import com.betterup.codingexercise.views.AccountInfoScreen;
import com.betterup.codingexercise.views.LoginScreen;
import com.betterup.codingexercise.views.SplashScreen;

import org.mockito.Mockito;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class TestManagerModule {
    @Singleton
    @Provides
    public static AlertDialogManager provideAlertDialogManager(final Activity activity) {
        return Mockito.mock(AlertDialogManagerImpl.class);
    }

    @Singleton
    @Provides
    public static DatabaseManager provideDatabaseManager(final Context context) {
        return new DatabaseManagerImpl(context);
    }

    @Singleton
    @Provides
    public static ImageCacheManager provideImageCacheManager(final Context context) {
        return Mockito.mock(ImageCacheManagerImpl.class);
    }

    @Singleton
    @Provides
    public static NavigationManager provideNavigationManager() {
        return new NavigationManagerImpl();
    }

    @Singleton
    @Provides
    public static NetworkManager provideNetworkManager(final Context context) {
        return Mockito.mock(NetworkManagerImpl.class);
    }

    @Singleton
    @Provides
    public static ResourceManager provideResourceManager() {
        return Mockito.mock(ResourceManagerImpl.class);
    }

    @Singleton
    @Provides
    public static ScreenManager provideScreenManager() {
        ScreenManagerImpl screenManager = Mockito.mock(ScreenManagerImpl.class);

        SplashScreen splashScreen = Mockito.mock(SplashScreen.class);
        LoginScreen loginScreen = Mockito.mock(LoginScreen.class);
        AccountInfoScreen accountInfoScreen = Mockito.mock(AccountInfoScreen.class);

        Mockito.when(screenManager.getScreenFromClass(SplashScreen.class)).thenReturn(splashScreen);
        Mockito.when(screenManager.getScreenFromClass(LoginScreen.class)).thenReturn(loginScreen);
        Mockito.when(screenManager.getScreenFromClass(AccountInfoScreen.class)).thenReturn(accountInfoScreen);

        return screenManager;
    }

    @Singleton
    @Provides
    public static MainActivityProviderManager provideMainActivityProviderManager(){
        MainActivityProviderManagerImpl mainActivityProviderManager = Mockito.mock(MainActivityProviderManagerImpl.class);

        MainActivityVM mainActivityVM = Mockito.mock(MainActivityVM.class);

        MainActivity mainActivity = Mockito.mock(MainActivity.class);
        Mockito.when(mainActivity.getViewModel()).thenReturn(mainActivityVM);

        Mockito.when((mainActivityProviderManager.provideMainActivity())).thenReturn(mainActivity);



        return mainActivityProviderManager;
    }

}
