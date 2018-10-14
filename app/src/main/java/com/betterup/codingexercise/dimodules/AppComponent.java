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
        ContextModule.class,
        DAOModule.class,
        FacadeModule.class,
        RestClientModule.class})
public interface AppComponent {
    void inject(final CommonViewBindings commonViewBindings);
}
