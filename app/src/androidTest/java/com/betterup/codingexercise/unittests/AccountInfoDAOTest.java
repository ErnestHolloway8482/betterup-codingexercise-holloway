package com.betterup.codingexercise.unittests;

import android.support.test.runner.AndroidJUnit4;

import com.betterup.codingexercise.BaseAndroidUnitTest;
import com.betterup.codingexercise.daos.AccountInfoDAO;
import com.betterup.codingexercise.models.databasemodels.AccountInfoDBM;
import com.betterup.codingexercise.models.databasemodels.OAuthTokenDBM;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.UUID;

import javax.inject.Inject;

@RunWith(AndroidJUnit4.class)
public class AccountInfoDAOTest extends BaseAndroidUnitTest {
    @Inject
    AccountInfoDAO accountInfoDAO;

    @Before
    public void setup() {
        super.setup();

        getTestAppComponent().inject(this);
    }

    @After
    public void tearDown() {
        super.tearDown();

        accountInfoDAO.deleteOAuthToken();
        accountInfoDAO.deleteAccountInfo();
    }

    @Test
    public void saveAccountInfoTest() {
        Assert.assertTrue(accountInfoDAO.saveAccountInfo(new AccountInfoDBM()));
    }

    @Test
    public void saveOAuthTokenTest() {
        Assert.assertTrue(accountInfoDAO.saveOAuthToken(new OAuthTokenDBM()));
    }

    @Test
    public void getAccountInfoTest() {
        AccountInfoDBM databaseModel = new AccountInfoDBM();
        databaseModel.setId("1000");

        Assert.assertTrue(accountInfoDAO.saveAccountInfo(databaseModel));

        AccountInfoDBM retrievedDatabaseModel = accountInfoDAO.getAccountInfo();

        Assert.assertNotNull(retrievedDatabaseModel);
        Assert.assertEquals(databaseModel.getId(), retrievedDatabaseModel.getId());
    }

    @Test
    public void getOauthTokenTest() {
        String uuid = UUID.randomUUID().toString();

        OAuthTokenDBM databaseModel = new OAuthTokenDBM();
        databaseModel.setAccessToken(uuid);

        Assert.assertTrue(accountInfoDAO.saveOAuthToken(databaseModel));

        OAuthTokenDBM retrievedDatabaseModel = accountInfoDAO.getOauthToken();

        Assert.assertNotNull(retrievedDatabaseModel);
        Assert.assertEquals(databaseModel.getAccessToken(), retrievedDatabaseModel.getAccessToken());
    }

    @Test
    public void deleteAccountInfoTest() {
        Assert.assertTrue(accountInfoDAO.saveAccountInfo(new AccountInfoDBM()));
        Assert.assertTrue(accountInfoDAO.deleteAccountInfo());
        Assert.assertNull(accountInfoDAO.getAccountInfo());
    }

    @Test
    public void deleteOAuthTokenTest() {
        Assert.assertTrue(accountInfoDAO.saveOAuthToken(new OAuthTokenDBM()));
        Assert.assertTrue(accountInfoDAO.deleteOAuthToken());
        Assert.assertNull(accountInfoDAO.getOauthToken());
    }
}
