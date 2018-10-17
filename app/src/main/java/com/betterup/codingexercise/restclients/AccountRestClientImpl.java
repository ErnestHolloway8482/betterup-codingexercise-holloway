package com.betterup.codingexercise.restclients;

import com.betterup.codingexercise.models.servermodels.LoginRequestSM;
import com.betterup.codingexercise.models.servermodels.OAuthResponseSM;
import com.betterup.codingexercise.models.servermodels.UserResponseSM;
import com.betterup.codingexercise.utilities.LoggerUtils;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AccountRestClientImpl implements AccountRestClient {
    private AccountRestClientRetrofit service;

    public AccountRestClientImpl() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .client(httpClient.build());

        Retrofit restAdapter = retrofitBuilder
                .baseUrl(EndPointURL.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = restAdapter.create(AccountRestClientRetrofit.class);
    }

    @Override
    public OAuthResponseSM login(final LoginRequestSM loginRequest) {
        try {
            return service.getOAuthToken("password", loginRequest.username, loginRequest.password).execute().body();
        } catch (IOException e) {
            LoggerUtils.logError(e.getMessage());
            return null;
        }
    }

    @Override
    public UserResponseSM getAccountInformation() {
        try {
            UserResponseSM response = service.getAccountInfo().execute().body();

            return response;
        } catch (IOException e) {
            LoggerUtils.logError(e.getMessage());
            return null;
        }
    }

    @Override
    public UserResponseSM getAccountInformation(final String header) {
        try {
            UserResponseSM response = service.getAccountInfo(header).execute().body();

            return response;
        } catch (IOException e) {
            LoggerUtils.logError(e.getMessage());
            return null;
        }
    }
}
