package com.betterup.codingexercise.dimodules;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

@Module
public class RealmModule {
    @Provides
    public static Realm provideRealm() {
        return Realm.getDefaultInstance();
    }
}
