package com.betterup.codingexercise.models.databasemodels;

import io.realm.RealmObject;

public class OAuthTokenDBM extends RealmObject {
    private String uuid;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(final String uuid) {
        this.uuid = uuid;
    }
}
