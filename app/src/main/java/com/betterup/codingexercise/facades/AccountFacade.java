package com.betterup.codingexercise.facades;

import com.betterup.codingexercise.daos.AccountInfoDAO;
import com.betterup.codingexercise.managers.DatabaseManager;
import com.betterup.codingexercise.mappers.AccountInfoDataMapper;
import com.betterup.codingexercise.models.databasemodels.AccountInfoDBM;
import com.betterup.codingexercise.models.databasemodels.OAuthTokenDBM;
import com.betterup.codingexercise.models.domainmodels.AccountInfoDOM;
import com.betterup.codingexercise.models.domainmodels.OAuthTokenDOM;
import com.betterup.codingexercise.models.servermodels.LoginRequestSM;
import com.betterup.codingexercise.models.servermodels.OAuthResponseSM;
import com.betterup.codingexercise.models.servermodels.UserResponseSM;
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

        databaseManager.openDatabase(null);
    }

    public boolean login(final String username, final String password) {
        LoginRequestSM request = new LoginRequestSM();
        request.username = username;
        request.password = password;

        OAuthResponseSM serverResponse = accountRestClient.login(request);

        OAuthTokenDBM oAuthTokenDBM = accountInfoDataMapper.map(serverResponse);

        return accountInfoDAO.saveOAuthToken(oAuthTokenDBM);
    }

    public OAuthTokenDOM getOAuthTokenFromCache(){
        OAuthTokenDBM databaseModel = accountInfoDAO.getOauthToken();

        return accountInfoDataMapper.map(databaseModel);
    }

    public AccountInfoDOM getAccountInfoFromServer() {
        UserResponseSM serverResponse = accountRestClient.getAccountInformation();

        AccountInfoDBM accountInfoDBM = accountInfoDataMapper.map(serverResponse);

        if(accountInfoDAO.saveAccountInfo(accountInfoDBM)){
            return accountInfoDataMapper.map(accountInfoDBM);
        } else{
            return null;
        }
    }

    public AccountInfoDOM getAccountInfoFromCache(){
        AccountInfoDBM accountInfoDBM = accountInfoDAO.getAccountInfo();

        return accountInfoDataMapper.map(accountInfoDBM);
    }

    public void closeDatabase(){
        databaseManager.closeDatabase();
    }

    public boolean removeDatabase(){
        databaseManager.closeDatabase();

        return databaseManager.deleteDatabase();
    }

    public void clearDataBaseItems(){
        accountInfoDAO.deleteAccountInfo();
        accountInfoDAO.deleteOAuthToken();
    }
}
