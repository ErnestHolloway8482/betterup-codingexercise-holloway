package com.betterup.codingexercise.daos;

import com.betterup.codingexercise.models.databasemodels.AccountInfoDBM;
import com.betterup.codingexercise.models.databasemodels.OAuthTokenDBM;

public class AccountInfoDAOImpl extends RealmAbstractDAO implements AccountInfoDAO {
    public AccountInfoDAOImpl() {
        super();
    }

    @Override
    public boolean saveAccountInfo(final AccountInfoDBM accountInfoDBM) {
        if (accountInfoDBM == null) {
            return false;
        }

        return super.create(accountInfoDBM);
    }

    @Override
    public boolean saveOAuthToken(final OAuthTokenDBM authTokenDBM) {
        if (authTokenDBM == null) {
            return false;
        }

        return super.create(authTokenDBM);
    }

    @Override
    public AccountInfoDBM getAccountInfo() {
        try {
            return super.readSingleObject(AccountInfoDBM.class);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public OAuthTokenDBM getOauthToken() {
        try {
            return super.readSingleObject(OAuthTokenDBM.class);
        } catch (Exception e) {
            return null;
        }
    }
}
