package com.betterup.codingexercise.integrationtests;

import android.support.test.runner.AndroidJUnit4;

import com.betterup.codingexercise.BaseAndroidUnitTest;
import com.betterup.codingexercise.facades.AccountFacade;
import com.betterup.codingexercise.models.domainmodels.OAuthTokenDOM;
import com.betterup.codingexercise.models.servermodels.LoginRequestSM;
import com.betterup.codingexercise.models.servermodels.OAuthResponseSM;
import com.betterup.codingexercise.restclients.AccountRestClient;

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
    public void loginTest(){
        OAuthResponseSM response = new OAuthResponseSM();
        response.token = "token";

        Mockito.when(accountRestClient.login(Mockito.any(LoginRequestSM.class))).thenReturn(response);

        Assert.assertTrue(accountFacade.login("username", "password"));
    }

    @Test
    public void loginErrorTest(){
        Mockito.when(accountRestClient.login(Mockito.any(LoginRequestSM.class))).thenReturn(null);

        Assert.assertFalse(accountFacade.login("username", "password"));
    }

    @Test
    public void getOAuthTokenFromCacheTest(){
        OAuthResponseSM response = new OAuthResponseSM();
        response.token = "token";

        Mockito.when(accountRestClient.login(Mockito.any(LoginRequestSM.class))).thenReturn(response);

        Assert.assertTrue(accountFacade.login("username", "password"));

        OAuthTokenDOM oAuthTokenDOM = accountFacade.getOAuthTokenFromCache();

        Assert.assertNotNull(oAuthTokenDOM);
        Assert.assertEquals(response.token, oAuthTokenDOM.token);
    }

    @Test
    public void getOAuthTokenFromCacheErrorTest(){
        Mockito.when(accountRestClient.login(Mockito.any(LoginRequestSM.class))).thenReturn(null);

        Assert.assertFalse(accountFacade.login("username", "password"));

        Assert.assertNull(accountFacade.getOAuthTokenFromCache());
    }
}
