package com.betterup.codingexercise.daos;

import com.betterup.codingexercise.models.databasemodels.AccountInfoDBM;
import com.betterup.codingexercise.models.databasemodels.OAuthTokenDBM;

import io.realm.Realm;

public class AccountInfoDAOImpl extends RealmAbstractDAO implements AccountInfoDAO {
    public AccountInfoDAOImpl(final Realm realm) {
        super(realm);
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
            return super.read(AccountInfoDBM.class).get(0);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public OAuthTokenDBM getOauthToken() {
        try {
            return super.read(OAuthTokenDBM.class).get(0);
        } catch (Exception e) {
            return null;
        }
    }
}
