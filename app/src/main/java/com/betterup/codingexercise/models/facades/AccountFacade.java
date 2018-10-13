package com.betterup.codingexercise.models.facades;

import com.betterup.codingexercise.mappers.AccountInfoDataMapper;
import com.betterup.codingexercise.models.daos.AccountInfoDAO;
import com.betterup.codingexercise.models.domainmodels.AccountInfoDOM;
import com.betterup.codingexercise.models.managers.DatabaseManager;
import com.betterup.codingexercise.restclients.AccountRestClient;

import javax.inject.Singleton;

@Singleton
public class AccountFacade {
    private final AccountRestClient accountRestClient;
    private final AccountInfoDataMapper accountInfoDataMapper;
    private final DatabaseManager databaseManager;
    private final AccountInfoDAO accountInfoDAO;

    public AccountFacade(final AccountRestClient accountRestClient,
                         final AccountInfoDataMapper accountInfoDataMapper,
                         final DatabaseManager databaseManager,
                         final AccountInfoDAO accountInfoDAO) {
        this.accountRestClient = accountRestClient;
        this.accountInfoDataMapper = accountInfoDataMapper;
        this.databaseManager = databaseManager;
        this.accountInfoDAO = accountInfoDAO;
    }

    public boolean login(final String userName, final String passWord){
        return false;
    }

    public AccountInfoDOM getAccountInfo(){
        return null;
    }
}
