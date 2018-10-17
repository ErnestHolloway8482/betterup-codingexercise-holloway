package com.betterup.codingexercise.unittests;

import com.betterup.codingexercise.BaseUnitTest;
import com.betterup.codingexercise.mappers.AccountInfoDataMapper;
import com.betterup.codingexercise.models.databasemodels.AccountInfoDBM;
import com.betterup.codingexercise.models.databasemodels.OAuthTokenDBM;
import com.betterup.codingexercise.models.domainmodels.AccountInfoDOM;
import com.betterup.codingexercise.models.domainmodels.OAuthTokenDOM;
import com.betterup.codingexercise.models.servermodels.OAuthResponseSM;
import com.betterup.codingexercise.models.servermodels.UserResponseSM;

import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import javax.inject.Inject;

@RunWith(JUnit4.class)
public class AccountInfoDataMapperTest extends BaseUnitTest {

    @Inject
    AccountInfoDataMapper accountInfoDataMapper;

    @Before
    public void setup() {
        super.setup();

        getTestAppComponent().inject(this);
    }

    @After
    public void tearDown() {
        super.tearDown();
    }

    @Test
    public void mapUserResponseSMToAccountInfoDBMTest() {
        UserResponseSM responseSM = getUserResponse();

        AccountInfoDBM accountInfoDBM = accountInfoDataMapper.map(getUserResponse());

        Assert.assertNotNull(accountInfoDBM);
        Assert.assertEquals(accountInfoDBM.getId(), responseSM.id);
        Assert.assertEquals(accountInfoDBM.getTitle(), responseSM.title);
        Assert.assertEquals(accountInfoDBM.getMotivation(), responseSM.motivation);
        Assert.assertEquals(accountInfoDBM.getName(), responseSM.name);
        Assert.assertEquals(accountInfoDBM.getPhoneNumber(), responseSM.phone);
        Assert.assertEquals(accountInfoDBM.getEmail(), responseSM.email);
        Assert.assertEquals(accountInfoDBM.getAvatarUrl(), responseSM.avatar.links.thumbnail.href);
        Assert.assertEquals(accountInfoDBM.isSmsNotificationEnabled(), responseSM.smsEnabled);
        Assert.assertEquals(accountInfoDBM.isEmailNotificationEnabled(), responseSM.emailMessagesEnabled);
    }

    @Test
    public void mapUserResponseSMToAccountInfoDBErrorTest() {
        //Entire response is null
        UserResponseSM responseSM = null;

        AccountInfoDBM accountInfoDBM = accountInfoDataMapper.map(responseSM);
        Assert.assertNull(accountInfoDBM);

        //Single field is null
        responseSM = getUserResponse();
        responseSM.avatar = null;
        accountInfoDBM = accountInfoDataMapper.map(responseSM);
        Assert.assertNull(accountInfoDBM);
    }

    @Test
    public void mapAccountInfoDBMToAccountInfoDOMTest() {
        AccountInfoDBM accountInfoDBM = getAccountInfoDBM();

        AccountInfoDOM accountInfoDOM = accountInfoDataMapper.map(accountInfoDBM);
        Assert.assertNotNull(accountInfoDOM);
        Assert.assertEquals(accountInfoDBM.getTitle(), accountInfoDOM.title);
        Assert.assertEquals(accountInfoDBM.getMotivation(), accountInfoDOM.motivation);
        Assert.assertEquals(accountInfoDBM.getName(), accountInfoDOM.name);
        Assert.assertEquals(accountInfoDBM.getPhoneNumber(), accountInfoDOM.phoneNumber);
        Assert.assertEquals(accountInfoDBM.getEmail(), accountInfoDOM.email);
        Assert.assertEquals(accountInfoDBM.getAvatarUrl(), accountInfoDOM.avatarUrl);
        Assert.assertEquals(accountInfoDBM.isSmsNotificationEnabled(), accountInfoDOM.smsNotificationEnabled);
        Assert.assertEquals(accountInfoDBM.isEmailNotificationEnabled(), accountInfoDOM.emailNotificationEnabled);
    }

    @Test
    public void mapAccountInfoDBMToAccountInfoDOMErrorTest() {
        //Datbase model is null.
        AccountInfoDBM accountInfoDBM = null;

        AccountInfoDOM accountInfoDOM = accountInfoDataMapper.map(accountInfoDBM);
        Assert.assertNull(accountInfoDOM);
    }

    @Test
    public void mapOAuthResponseSMToOAuthTokenDBMTest() {
        OAuthResponseSM oAuthResponseSM = getOAuthResponseSM();

        OAuthTokenDBM oAuthTokenDBM = accountInfoDataMapper.map(oAuthResponseSM);

        Assert.assertNotNull(oAuthTokenDBM);
        Assert.assertEquals(oAuthResponseSM.accessToken, oAuthTokenDBM.getAccessToken());
        Assert.assertEquals(oAuthResponseSM.refreshToken, oAuthTokenDBM.getRefreshToken());
        Assert.assertEquals(oAuthResponseSM.expireIn, oAuthTokenDBM.getExpiresInMinutes());
    }

    @Test
    public void mapOAuthTokenDBMToOAuthTokenDOMTest() {
        OAuthTokenDBM oAuthTokenDBM = getOAuthTokenDBM();

        OAuthTokenDOM oAuthTokenDOM = accountInfoDataMapper.map(oAuthTokenDBM);

        Assert.assertNotNull(oAuthTokenDBM);
        Assert.assertEquals(oAuthTokenDBM.getAccessToken(), oAuthTokenDOM.accessToken);
        Assert.assertEquals(oAuthTokenDBM.getRefreshToken(), oAuthTokenDOM.refreshToken);
        Assert.assertEquals(oAuthTokenDBM.getExpiresInMinutes(), oAuthTokenDOM.expiresInMinutes);
    }

    private UserResponseSM getUserResponse() {
        UserResponseSM responseSM = new UserResponseSM();
        responseSM.id = 1;
        responseSM.name = "name";
        responseSM.timeZone = "CST";
        responseSM.title = "title";
        responseSM.motivation = "motivation";

        UserResponseSM.Avatar avatar = new UserResponseSM.Avatar();
        avatar.links = new UserResponseSM.Links();
        avatar.links.thumbnail = new UserResponseSM.Thumbnail();
        avatar.links.thumbnail.href = "www.picture.url.com";
        responseSM.avatar = avatar;

        responseSM.phone = "123-456-7890";
        responseSM.activatedAt = DateTime.now().toString();
        responseSM.email = "email";
        responseSM.lastActiveAt = DateTime.now().toString();

        responseSM.smsEnabled = true;
        responseSM.emailMessagesEnabled = true;

        return responseSM;
    }

    private AccountInfoDBM getAccountInfoDBM() {
        AccountInfoDBM accountInfoDBM = new AccountInfoDBM();
        accountInfoDBM.setId(1);
        accountInfoDBM.setName("name");
        accountInfoDBM.setTimeZonePreference("CST");
        accountInfoDBM.setTitle("title");
        accountInfoDBM.setMotivation("motivation");
        accountInfoDBM.setAvatarUrl("www.picture.url.com");
        accountInfoDBM.setPhoneNumber("123-456-7890");
        accountInfoDBM.setAvatarUrl(DateTime.now().toString());
        accountInfoDBM.setEmail("email");
        accountInfoDBM.setSmsNotificationEnabled(true);
        accountInfoDBM.setEmailNotificationEnabled(true);

        return accountInfoDBM;
    }

    private OAuthResponseSM getOAuthResponseSM() {
        OAuthResponseSM oAuthResponseSM = new OAuthResponseSM();
        oAuthResponseSM.accessToken = "accessToken";
        oAuthResponseSM.refreshToken = "refreshToken";
        oAuthResponseSM.expireIn = 30;

        return oAuthResponseSM;
    }

    private OAuthTokenDBM getOAuthTokenDBM() {
        OAuthTokenDBM oAuthTokenDBM = new OAuthTokenDBM();

        oAuthTokenDBM.setAccessToken("accessToken");
        oAuthTokenDBM.setRefreshToken("refreshToken");
        oAuthTokenDBM.setExpiresInMinutes(30);

        return oAuthTokenDBM;
    }


}
