package com.betterup.codingexercise.mappers;

import com.betterup.codingexercise.models.databasemodels.AccountInfoDBM;
import com.betterup.codingexercise.models.databasemodels.OAuthTokenDBM;
import com.betterup.codingexercise.models.domainmodels.AccountInfoDOM;
import com.betterup.codingexercise.models.domainmodels.OAuthTokenDOM;
import com.betterup.codingexercise.models.servermodels.OAuthResponseSM;
import com.betterup.codingexercise.models.servermodels.UserResponseSM;
import com.betterup.codingexercise.utilities.LoggerUtils;

public class AccountInfoDataMapper {

    public AccountInfoDBM map(final UserResponseSM serverModel) {
        try {
            return getAccountInfoDBM(serverModel);
        } catch (Exception e) {
            LoggerUtils.logError(e.getMessage());

            return null;
        }
    }

    public AccountInfoDOM map(final AccountInfoDBM databaseModel) {
        try {
            return getAccountInfoDOM(databaseModel);
        } catch (Exception e) {
            LoggerUtils.logError(e.getMessage());

            return null;
        }
    }

    public OAuthTokenDBM map(final OAuthResponseSM serverModel) {
        try {
            return getOAuthTokenDBM(serverModel);
        } catch (Exception e) {
            LoggerUtils.logError(e.getMessage());

            return null;
        }
    }

    public OAuthTokenDOM map(final OAuthTokenDBM databaseModel) {
        try {
            return getOAuthTokenDOM(databaseModel);
        } catch (Exception e) {
            LoggerUtils.logError(e.getMessage());

            return null;
        }
    }

    private AccountInfoDBM getAccountInfoDBM(final UserResponseSM serverModel) {
        AccountInfoDBM databaseModel = new AccountInfoDBM();

        databaseModel.setId(serverModel.id);
        databaseModel.setTitle(serverModel.title);
        databaseModel.setMotivation(serverModel.motivation);
        databaseModel.setName(serverModel.name);
        databaseModel.setPhoneNumber(serverModel.phone);
        databaseModel.setEmail(serverModel.email);
        databaseModel.setAvatarUrl(serverModel.avatar.links.thumbnail.href);
        databaseModel.setTimeZonePreference(serverModel.timeZone);
        databaseModel.setSmsNotificationEnabled(serverModel.smsEnabled);
        databaseModel.setEmailNotificationEnabled(serverModel.emailMessagesEnabled);

        return databaseModel;
    }

    private AccountInfoDOM getAccountInfoDOM(final AccountInfoDBM databaseModel) {
        AccountInfoDOM domainModel = new AccountInfoDOM();

        domainModel.title = databaseModel.getTitle();
        domainModel.motivation = databaseModel.getMotivation();
        domainModel.name = databaseModel.getName();
        domainModel.phoneNumber = databaseModel.getPhoneNumber();
        domainModel.email = databaseModel.getEmail();
        domainModel.avatarUrl = databaseModel.getAvatarUrl();
        domainModel.timeZonePreference = databaseModel.getTimeZonePreference();
        domainModel.smsNotificationEnabled = databaseModel.isSmsNotificationEnabled();
        domainModel.emailNotificationEnabled = databaseModel.isEmailNotificationEnabled();

        return domainModel;
    }

    private OAuthTokenDBM getOAuthTokenDBM(final OAuthResponseSM serverModel) {
        OAuthTokenDBM databaseModel = new OAuthTokenDBM();
        databaseModel.setAccessToken(serverModel.accessToken);

        return databaseModel;
    }

    private OAuthTokenDOM getOAuthTokenDOM(final OAuthTokenDBM databaseModel) {
        OAuthTokenDOM domainModel = new OAuthTokenDOM();

        domainModel.accessToken = databaseModel.getAccessToken();

        return domainModel;
    }
}
