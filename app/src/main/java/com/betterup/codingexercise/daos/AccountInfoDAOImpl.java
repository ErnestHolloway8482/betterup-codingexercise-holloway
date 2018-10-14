package com.betterup.codingexercise.daos;

import io.realm.Realm;

public class AccountInfoDAOImpl extends RealmAbstractDAO implements AccountInfoDAO {
    public AccountInfoDAOImpl(final Realm realm) {
        super(realm);
    }
}
