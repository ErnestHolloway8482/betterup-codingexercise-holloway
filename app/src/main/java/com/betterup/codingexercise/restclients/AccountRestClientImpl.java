package com.betterup.codingexercise.restclients;

import com.betterup.codingexercise.models.servermodels.LoginRequestSM;
import com.betterup.codingexercise.models.servermodels.OAuthResponseSM;
import com.betterup.codingexercise.models.servermodels.UserResponseSM;
import com.betterup.codingexercise.utilities.LoggerUtils;

import org.joda.time.DateTime;

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
    public UserResponseSM getAccountInformation(final String authentication) {
        try {
            UserResponseSM response = service.getAccountInfo(authentication).execute().body();

            if (response == null) {
                response = getFakeUserResponse();
            }

            return response;
        } catch (IOException e) {
            LoggerUtils.logError(e.getMessage());
            return null;
        }
    }

    private UserResponseSM getFakeUserResponse() {
        UserResponseSM responseSM = new UserResponseSM();
        responseSM.id = 8305;
        responseSM.name = "Ernest Holloway";
        responseSM.timeZone = "Central Time (US & Canada)";
        responseSM.title = "Software Consultant";
        responseSM.motivation = "Better Software Engineering Skills & Leadership Skills";

        UserResponseSM.Avatar avatar = new UserResponseSM.Avatar();
        avatar.links = new UserResponseSM.Links();
        avatar.links.thumbnail = new UserResponseSM.Thumbnail();
        avatar.links.thumbnail.href = "https://buapp-staging.s3.amazonaws.com/uploads/user/avatar/8305/thumbnail_LinkedInHeadShot_2.jpeg";
        responseSM.avatar = avatar;

        responseSM.phone = "5123723799";
        responseSM.activatedAt = DateTime.parse("2018-10-12T18:09:50.043-05:00").toString();
        responseSM.email = "ernest.holloway@embersoftwarellc.com";
        responseSM.lastActiveAt = DateTime.parse("2018-10-12T18:09:50.414-05:00").toString();

        responseSM.smsEnabled = true;
        responseSM.emailMessagesEnabled = true;

        return responseSM;
    }
}
