package com.betterup.codingexercise.models.databasemodels;

import io.realm.RealmObject;

public class OAuthTokenDBM extends RealmObject {
    private String uuid;
    private String token;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(final String uuid) {
        this.uuid = uuid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(final String token) {
        this.token = token;
    }
}
