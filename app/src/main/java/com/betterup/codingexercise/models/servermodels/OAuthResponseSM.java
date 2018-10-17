package com.betterup.codingexercise.models.servermodels;

import com.google.gson.annotations.SerializedName;

public class OAuthResponseSM {
    @SerializedName("access_token")
    public String accessToken;

    @SerializedName("token_type")
    public String tokenType;

    @SerializedName("expires_in")
    public int expireIn;

    @SerializedName("refresh_token")
    public String refreshToken;

    @SerializedName("created_at")
    public long createdAt;
}
