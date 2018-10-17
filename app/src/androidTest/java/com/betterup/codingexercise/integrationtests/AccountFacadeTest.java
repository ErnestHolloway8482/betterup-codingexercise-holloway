package com.betterup.codingexercise.integrationtests;

import android.support.test.runner.AndroidJUnit4;

import com.betterup.codingexercise.BaseAndroidUnitTest;
import com.betterup.codingexercise.facades.AccountFacade;
import com.betterup.codingexercise.models.domainmodels.OAuthTokenDOM;
import com.betterup.codingexercise.models.servermodels.LoginRequestSM;
import com.betterup.codingexercise.models.servermodels.OAuthResponseSM;
import com.betterup.codingexercise.models.servermodels.UserResponseSM;
import com.betterup.codingexercise.restclients.AccountRestClient;

import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import javax.inject.Inject;

@RunWith(AndroidJUnit4.class)
public class AccountFacadeTest extends BaseAndroidUnitTest {
    @Inject
    AccountFacade accountFacade;

    @Inject
    AccountRestClient accountRestClient;

    @Before
    public void setup() {
        super.setup();

        getTestAppComponent().inject(this);
    }

    @After
    public void tearDown() {
        super.tearDown();

        accountFacade.clearDataBaseItems();
    }

    @Test
    public void loginTest() {
        OAuthResponseSM response = new OAuthResponseSM();
        response.accessToken = "accessToken";

        Mockito.when(accountRestClient.login(Mockito.any(LoginRequestSM.class))).thenReturn(response);

        Assert.assertTrue(accountFacade.login("username", "password"));
    }

    @Test
    public void loginErrorTest() {
        Mockito.when(accountRestClient.login(Mockito.any(LoginRequestSM.class))).thenReturn(null);

        Assert.assertFalse(accountFacade.login("username", "password"));
    }

    @Test
    public void getOAuthTokenFromCacheTest() {
        OAuthResponseSM response = new OAuthResponseSM();
        response.accessToken = "accessToken";

        Mockito.when(accountRestClient.login(Mockito.any(LoginRequestSM.class))).thenReturn(response);

        Assert.assertTrue(accountFacade.login("username", "password"));

        OAuthTokenDOM oAuthTokenDOM = accountFacade.getOAuthTokenFromCache();

        Assert.assertNotNull(oAuthTokenDOM);
        Assert.assertEquals(response.accessToken, oAuthTokenDOM.accessToken);
    }

    @Test
    public void getOAuthTokenFromCacheErrorTest() {
        Mockito.when(accountRestClient.login(Mockito.any(LoginRequestSM.class))).thenReturn(null);

        Assert.assertFalse(accountFacade.login("username", "password"));

        Assert.assertNull(accountFacade.getOAuthTokenFromCache());
    }

    @Test
    public void getAccountInfoFromServerTest() {
        Mockito.when(accountRestClient.getAccountInformation()).thenReturn(getUserResponse());

        Assert.assertNotNull(accountFacade.getAccountInfoFromServer());
    }

    @Test
    public void getAccountInfoFromServerErrorTest() {
        Mockito.when(accountRestClient.getAccountInformation()).thenReturn(null);

        Assert.assertNull(accountFacade.getAccountInfoFromServer());
    }

    @Test
    public void getAccountInfoFromCacheTest() {
        Mockito.when(accountRestClient.getAccountInformation()).thenReturn(getUserResponse());

        Assert.assertNotNull(accountFacade.getAccountInfoFromServer());
        Assert.assertNotNull(accountFacade.getAccountInfoFromCache());
    }

    @Test
    public void getAccountInfoFromCacheErrorTest() {
        Mockito.when(accountRestClient.getAccountInformation()).thenReturn(null);

        Assert.assertNull(accountFacade.getAccountInfoFromServer());
        Assert.assertNull(accountFacade.getAccountInfoFromCache());
    }

    @Test
    public void removeDatabaseTest() {
        Assert.assertTrue(accountFacade.removeDatabase());
    }

    @Test
    public void clearDatabaseItemsTest() {
        OAuthResponseSM response = new OAuthResponseSM();
        response.accessToken = "accessToken";

        Mockito.when(accountRestClient.login(Mockito.any(LoginRequestSM.class))).thenReturn(response);
        Mockito.when(accountRestClient.getAccountInformation()).thenReturn(getUserResponse());

        Assert.assertTrue(accountFacade.login("username", "password"));
        Assert.assertNotNull(accountFacade.getAccountInfoFromServer());

        Assert.assertNotNull(accountFacade.getOAuthTokenFromCache());
        Assert.assertNotNull(accountFacade.getAccountInfoFromCache());

        accountFacade.clearDataBaseItems();

        Assert.assertNull(accountFacade.getOAuthTokenFromCache());
        Assert.assertNull(accountFacade.getAccountInfoFromCache());
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
}
