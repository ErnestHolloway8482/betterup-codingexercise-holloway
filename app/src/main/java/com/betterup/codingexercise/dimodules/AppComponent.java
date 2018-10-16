package com.betterup.codingexercise.dimodules;

import com.betterup.codingexercise.activities.MainActivity;

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
    void inject(MainActivity mainActivity);

    @Component.Builder
    interface Builder {
        AppComponent build();
    }
}
