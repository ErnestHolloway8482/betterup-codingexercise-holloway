package com.betterup.codingexercise.restclients;

import com.betterup.codingexercise.models.servermodels.LoginRequestSM;
import com.betterup.codingexercise.models.servermodels.OAuthResponseSM;
import com.betterup.codingexercise.models.servermodels.UserResponseSM;

public interface AccountRestClient {
    UserResponseSM getAccountInformation();

    UserResponseSM getAccountInformation(final String accessToken);

    OAuthResponseSM login(final LoginRequestSM loginRequest);
}
