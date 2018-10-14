package com.betterup.codingexercise.dimodules;

import com.betterup.codingexercise.daos.AccountInfoDAO;
import com.betterup.codingexercise.daos.AccountInfoDAOImpl;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

@Module
public class DAOModule {
    @Provides
    public static AccountInfoDAO provideAccountInfoDAO(final Realm realm) {
        return new AccountInfoDAOImpl(realm);
    }
}
