package com.betterup.codingexercise.restclients;

import com.betterup.codingexercise.models.servermodels.OAuthResponseSM;
import com.betterup.codingexercise.models.servermodels.UserResponseSM;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface AccountRestClientRetrofit {
    @GET(EndPointURL.ACCOUNT_URL)
    Call<UserResponseSM> getAccountInfo(@Header("Authorization") String accessTokenHeader);

    @FormUrlEncoded
    @POST(EndPointURL.OAUTH_TOKEN_URL)
    Call<OAuthResponseSM> getOAuthToken(@Field("grant_type") String grantType, @Field("username") String username, @Field("password") String password);
}
