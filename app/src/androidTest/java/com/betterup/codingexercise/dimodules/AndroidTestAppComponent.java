package com.betterup.codingexercise.dimodules;


import com.betterup.codingexercise.BaseAndroidUnitTest;
import com.betterup.codingexercise.integrationtests.AccountFacadeTest;
import com.betterup.codingexercise.unittests.AccountInfoDAOTest;
import com.betterup.codingexercise.viewmodeltests.AccountInfoVMTest;
import com.betterup.codingexercise.viewmodeltests.LoginVMTest;
import com.betterup.codingexercise.viewmodeltests.MainActivityVMTest;
import com.betterup.codingexercise.viewmodeltests.SplashVMTest;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        AndroidTestManagerModule.class,
        AndroidTestContextModule.class,
        ViewModelModule.class,
        MapperModule.class,
        DAOModule.class,
        FacadeModule.class,
        AndroidTestRestClientModule.class,
        RealmModule.class})
public interface AndroidTestAppComponent extends AppComponent {
    void inject(final BaseAndroidUnitTest baseAndroidUnitTest);

    void inject(final LoginVMTest loginVMTest);

    void inject(final SplashVMTest splashVMTest);

    void inject(final AccountInfoVMTest accountInfoVMTest);

    void inject(final MainActivityVMTest mainActivityVMTest);

    void inject(AccountInfoDAOTest accountInfoDAOTest);

    void inject(AccountFacadeTest accountFacadeTest);

}
