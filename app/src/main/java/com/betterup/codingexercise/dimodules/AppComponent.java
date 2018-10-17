package com.betterup.codingexercise.dimodules;

import com.betterup.codingexercise.activities.MainActivity;
import com.betterup.codingexercise.application.BetterUpApplication;
import com.betterup.codingexercise.views.AccountInfoScreen;
import com.betterup.codingexercise.views.LoginScreen;
import com.betterup.codingexercise.views.SplashScreen;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        ManagerModule.class,
        ContextModule.class,
        ViewModelModule.class,
        MapperModule.class,
        DAOModule.class,
        FacadeModule.class,
        RestClientModule.class,
        RealmModule.class})
public interface AppComponent {
    void inject(final BetterUpApplication betterUpApplication);
    
    void inject(final MainActivity mainActivity);

    void inject(final SplashScreen splashScreen);

    void inject(final LoginScreen loginScreen);

    void inject(final AccountInfoScreen accountInfoScreen);

    @Component.Builder
    interface Builder {
        AppComponent build();
    }
}
