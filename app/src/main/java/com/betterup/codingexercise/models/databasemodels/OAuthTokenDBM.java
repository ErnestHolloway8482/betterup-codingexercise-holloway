package com.betterup.codingexercise.models.databasemodels;

import io.realm.RealmObject;

public class OAuthTokenDBM extends RealmObject {
    private String uuid;
    private String accessToken;
    private String refreshToken;
    private int expiresInMinutes;


    public String getUuid() {
        return uuid;
    }

    public void setUuid(final String uuid) {
        this.uuid = uuid;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(final String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(final String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public int getExpiresInMinutes() {
        return expiresInMinutes;
    }

    public void setExpiresInMinutes(final int expiresInMinutes) {
        this.expiresInMinutes = expiresInMinutes;
    }
}
