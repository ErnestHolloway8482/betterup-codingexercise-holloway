package com.betterup.codingexercise.dimodules;


import com.betterup.codingexercise.BaseUnitTest;
import com.betterup.codingexercise.unittests.AccountInfoDataMapperTest;
import com.betterup.codingexercise.unittests.NavigationManagerTest;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        TestManagerModule.class,
        TestContextModule.class,
        ViewModelModule.class,
        MapperModule.class,
        DAOModule.class,
        FacadeModule.class,
        TestRestClientModule.class,
        RealmModule.class})
public interface TestAppComponent extends AppComponent {
    void inject(final BaseUnitTest baseUnitTest);
    void inject(final AccountInfoDataMapperTest accountInfoDataMapperTest);
    void inject(final NavigationManagerTest navigationManagerTest);
}
