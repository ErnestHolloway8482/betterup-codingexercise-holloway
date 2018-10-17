package com.betterup.codingexercise.restclients;

import android.support.annotation.NonNull;

import com.betterup.codingexercise.bindings.Converter;
import com.betterup.codingexercise.models.servermodels.LoginRequestSM;
import com.betterup.codingexercise.models.servermodels.OAuthResponseSM;
import com.betterup.codingexercise.models.servermodels.UserResponseSM;
import com.betterup.codingexercise.utilities.LoggerUtils;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

import java.io.IOException;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Response;
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
            Response response = service.getAccountInfo(authentication).execute();

            if (response.isSuccessful()) {
                try {
                    return convertRawResponseToUserResponseSM(response);
                } catch (Exception e) {
                    return null;
                }
            } else {
                return null;
            }
        } catch (IOException e) {
            LoggerUtils.logError(e.getMessage());
            return null;
        }
    }

    @NonNull
    private UserResponseSM convertRawResponseToUserResponseSM(final Response response) throws IOException {
        ResponseBody responseBody = (ResponseBody) response.body();

        String jsonString = responseBody.string();

        Gson gson = new Gson();

        Map<String, Object> map = gson.fromJson(jsonString, Map.class);
        Map<String, Object> userMap = (Map<String, Object>) map.get("user");

        UserResponseSM userResponseSM = new UserResponseSM();
        userResponseSM.id = Converter.convertIntegerToString(((Double) userMap.get("id")).intValue());
        userResponseSM.name = (String) userMap.get("name");
        userResponseSM.timeZone = (String) userMap.get("time_zone");
        userResponseSM.title = (String) userMap.get("title");
        userResponseSM.motivation = (String) userMap.get("motivation");
        userResponseSM.avatar = convertPartialResponseToAvatarObject(userMap.get("avatar"));
        userResponseSM.phone = (String) userMap.get("phone");
        userResponseSM.activatedAt = (String) userMap.get("activated_at");
        userResponseSM.email = (String) userMap.get("email");
        userResponseSM.lastActiveAt = (String) userMap.get("last_active_at");
        userResponseSM.smsEnabled = (Boolean) userMap.get("sms_enabled");
        userResponseSM.emailMessagesEnabled = (Boolean) userMap.get("email_messages_enabled");

        return userResponseSM;
    }

    private UserResponseSM.Avatar convertPartialResponseToAvatarObject(Object object) {
        LinkedTreeMap<String, Object> map = (LinkedTreeMap<String, Object>) object;
        LinkedTreeMap<String, Object> linksMap = (LinkedTreeMap<String, Object>) map.get("links");
        LinkedTreeMap<String, Object> href = (LinkedTreeMap<String, Object>) linksMap.get("thumbnail");

        UserResponseSM.Avatar avatar = new UserResponseSM.Avatar();
        avatar.links = new UserResponseSM.Links();
        avatar.links.thumbnail = new UserResponseSM.Thumbnail();

        if (href.get("href") != null) {
            avatar.links.thumbnail.href = (String) href.get("href");
        }

        return avatar;
    }
}
