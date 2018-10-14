package com.betterup.codingexercise.dimodules;

import com.betterup.codingexercise.daos.AccountInfoDAO;
import com.betterup.codingexercise.facades.AccountFacade;
import com.betterup.codingexercise.managers.DatabaseManager;
import com.betterup.codingexercise.mappers.AccountInfoDataMapper;
import com.betterup.codingexercise.restclients.AccountRestClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class FacadeModule {
    @Singleton
    @Provides
    public static AccountFacade provideAccountFacade(final AccountRestClient accountRestClient,
                                                     final AccountInfoDataMapper accountInfoDataMapper,
                                                     final DatabaseManager databaseManager,
                                                     final AccountInfoDAO accountInfoDAO) {
        return new AccountFacade(accountRestClient, accountInfoDataMapper, databaseManager, accountInfoDAO);
    }
}
