package com.betterup.codingexercise.dimodules;

import com.betterup.codingexercise.managers.NavigationManager;
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
    public static MainActivityVM provideBaseVM(){
        return new MainActivityVM();
    }

    @Provides
    public static LoginVM provideLoginVM(){
        return new LoginVM();
    }

    @Provides
    public static AccountInfoVM provideAccountInfoVM(){
        return new AccountInfoVM();
    }

    @Provides
    public static SplashVM provideSplashVM(final NavigationManager navigationManager){
        return new SplashVM(navigationManager);
    }
}
