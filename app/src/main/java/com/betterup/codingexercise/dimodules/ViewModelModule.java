package com.betterup.codingexercise.dimodules;

import com.betterup.codingexercise.facades.AccountFacade;
import com.betterup.codingexercise.managers.AlertDialogManager;
import com.betterup.codingexercise.managers.MainActivityProviderManager;
import com.betterup.codingexercise.managers.NavigationManager;
import com.betterup.codingexercise.managers.NetworkManager;
import com.betterup.codingexercise.managers.ResourceManager;
import com.betterup.codingexercise.managers.ScreenManager;
import com.betterup.codingexercise.models.viewmodels.AccountInfoVM;
import com.betterup.codingexercise.models.viewmodels.LoginVM;
import com.betterup.codingexercise.models.viewmodels.MainActivityVM;
import com.betterup.codingexercise.models.viewmodels.SplashVM;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewModelModule {
    @Provides
    @Singleton
    public static MainActivityVM provideMainActivityVM(final ResourceManager resourceManager) {
        return new MainActivityVM(resourceManager);
    }

    @Provides
    public static LoginVM provideLoginVM(final AccountFacade accountFacade,
                                         final NavigationManager navigationManager,
                                         final NetworkManager networkManager,
                                         final AlertDialogManager alertDialogManager,
                                         final ScreenManager screenManager,
                                         final ResourceManager resourceManager) {
        return new LoginVM(accountFacade, navigationManager, networkManager, alertDialogManager, screenManager, resourceManager);
    }

    @Provides
    public static AccountInfoVM provideAccountInfoVM(final AccountFacade accountFacade,
                                                     final NetworkManager networkManager,
                                                     final ResourceManager resourceManager,
                                                     final AlertDialogManager alertDialogManager,
                                                     final ScreenManager screenManager,
                                                     final NavigationManager navigationManager,
                                                     final MainActivityProviderManager mainActivityProviderManager) {
        return new AccountInfoVM(accountFacade, networkManager, resourceManager, alertDialogManager, screenManager, navigationManager, mainActivityProviderManager);
    }

    @Provides
    public static SplashVM provideSplashVM(final AccountFacade accountFacade, final NavigationManager navigationManager, final ScreenManager screenManager) {
        return new SplashVM(accountFacade, navigationManager, screenManager);
    }
}
