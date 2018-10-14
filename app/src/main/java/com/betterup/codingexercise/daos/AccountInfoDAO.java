package com.betterup.codingexercise.daos;

import com.betterup.codingexercise.models.databasemodels.AccountInfoDBM;
import com.betterup.codingexercise.models.databasemodels.OAuthTokenDBM;

public interface AccountInfoDAO {
    boolean saveAccountInfo(final AccountInfoDBM accountInfoDBM);
    boolean saveOAuthToken(final OAuthTokenDBM authTokenDBM);
    AccountInfoDBM getAccountInfo();
    OAuthTokenDBM getOauthToken();
}
