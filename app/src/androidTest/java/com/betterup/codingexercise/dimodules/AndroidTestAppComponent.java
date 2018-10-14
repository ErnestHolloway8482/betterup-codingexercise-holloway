package com.betterup.codingexercise.dimodules;


import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        ManagerModule.class,
        Context.class,
        ViewModelModule.class,
        TestContextModule.class,
        DAOModule.class,
        FacadeModule.class})
public interface AndroidTestAppComponent extends AppComponent {
}
