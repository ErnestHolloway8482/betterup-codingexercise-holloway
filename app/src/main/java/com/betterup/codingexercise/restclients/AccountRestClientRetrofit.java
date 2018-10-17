package com.betterup.codingexercise.restclients;

import com.betterup.codingexercise.models.servermodels.OAuthResponseSM;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface AccountRestClientRetrofit {
    @GET(EndPointURL.ACCOUNT_URL)
    @Headers("Accept: application/json")
    Call<ResponseBody> getAccountInfo(@Header("Authorization") String authorization);

    @FormUrlEncoded
    @POST(EndPointURL.OAUTH_TOKEN_URL)
    Call<OAuthResponseSM> getOAuthToken(@Field("grant_type") String grantType, @Field("username") String username, @Field("password") String password);
}
