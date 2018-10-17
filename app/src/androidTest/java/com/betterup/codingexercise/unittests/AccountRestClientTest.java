package com.betterup.codingexercise.unittests;

import android.support.test.runner.AndroidJUnit4;

import com.betterup.codingexercise.BaseAndroidUnitTest;
import com.betterup.codingexercise.models.servermodels.LoginRequestSM;
import com.betterup.codingexercise.models.servermodels.OAuthResponseSM;
import com.betterup.codingexercise.models.servermodels.UserResponseSM;
import com.betterup.codingexercise.restclients.AccountRestClient;
import com.betterup.codingexercise.restclients.AccountRestClientImpl;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class AccountRestClientTest extends BaseAndroidUnitTest {
    private AccountRestClient accountRestClient;

    @Before
    public void setup() {
        super.setup();

        getTestAppComponent().inject(this);

        accountRestClient = new AccountRestClientImpl();
    }

    @After
    public void tearDown() {
        super.tearDown();
    }

    @Test
    public void loginTest() {
        LoginRequestSM request = new LoginRequestSM();
        request.username = "ernest.holloway@embersoftwarellc.com";
        request.password = "Sprinter198!";

        OAuthResponseSM response = accountRestClient.login(request);

        Assert.assertNotNull(response);
        Assert.assertNotNull(response.accessToken);
        Assert.assertNotNull(response.refreshToken);
        Assert.assertNotNull(response.tokenType);
        Assert.assertTrue(response.createdAt > 0);
        Assert.assertTrue(response.expireIn > 0);
    }

    @Test
    public void loginErrorTest() {
        //Username Wrong
        LoginRequestSM request = new LoginRequestSM();
        request.username = "ernest.holloway@embersoftwarellc.co";
        request.password = "Sprinter198!";

        OAuthResponseSM response = accountRestClient.login(request);

        Assert.assertNull(response);

        //Password Wrong
        request = new LoginRequestSM();
        request.username = "ernest.holloway@embersoftwarellc.com";
        request.password = "Sprinter198";

        response = accountRestClient.login(request);

        Assert.assertNull(response);

        //Username and password wrong
        request = new LoginRequestSM();
        request.username = "ernest.holloway@embersoftwarellc.co";
        request.password = "Sprinter198";

        response = accountRestClient.login(request);

        Assert.assertNull(response);
    }

    @Test
    public void getAccountInfoTest() {
        LoginRequestSM request = new LoginRequestSM();
        request.username = "ernest.holloway@embersoftwarellc.com";
        request.password = "Sprinter198!";

        OAuthResponseSM response = accountRestClient.login(request);
        Assert.assertNotNull(response);

        String headerFormat = "%s %s";
        String header = String.format(headerFormat, response.tokenType, response.accessToken);

        UserResponseSM userResponseSM = accountRestClient.getAccountInformation(header);
        Assert.assertNotNull(userResponseSM);
    }

    @Test
    @Ignore
    public void getAccountInfoErrorTest() {
        String headerFormat = "%s %s";
        String header = String.format(headerFormat, "error", "token");

        UserResponseSM userResponseSM = accountRestClient.getAccountInformation(header);
        Assert.assertNull(userResponseSM);
    }
}
