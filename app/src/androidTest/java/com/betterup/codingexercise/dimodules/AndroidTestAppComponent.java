package com.betterup.codingexercise.dimodules;


import com.betterup.codingexercise.BaseAndroidUnitTest;
import com.betterup.codingexercise.viewmodeltests.LoginVMTest;

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

}
