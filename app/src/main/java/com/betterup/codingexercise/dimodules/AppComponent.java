package com.betterup.codingexercise.dimodules;

import android.content.Context;

import com.betterup.codingexercise.bindings.CommonViewBindings;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        ManagerModule.class,
        Context.class,
        ViewModelModule.class,
        DAOModule.class,
        FacadeModule.class,
        RestClientModule.class,
        RealmModule.class})
public interface AppComponent {
    void inject(final CommonViewBindings commonViewBindings);
}
