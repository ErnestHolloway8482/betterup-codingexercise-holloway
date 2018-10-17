package com.betterup.codingexercise.viewmodeltests;

import android.support.test.runner.AndroidJUnit4;

import com.betterup.codingexercise.BaseAndroidUnitTest;
import com.betterup.codingexercise.daos.AccountInfoDAO;
import com.betterup.codingexercise.facades.AccountFacade;
import com.betterup.codingexercise.managers.NavigationManager;
import com.betterup.codingexercise.managers.ScreenManager;
import com.betterup.codingexercise.models.servermodels.UserResponseSM;
import com.betterup.codingexercise.models.viewmodels.SplashVM;
import com.betterup.codingexercise.restclients.AccountRestClient;
import com.betterup.codingexercise.views.AccountInfoScreen;
import com.betterup.codingexercise.views.LoginScreen;

import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import javax.inject.Inject;


@RunWith(AndroidJUnit4.class)
public class SplashVMTest extends BaseAndroidUnitTest {

    @Inject
    NavigationManager navigationManager;

    @Inject
    ScreenManager screenManager;

    @Inject
    AccountFacade accountFacade;

    @Inject
    AccountInfoDAO accountInfoDAO;

    @Inject
    AccountRestClient accountRestClient;

    private SplashVM splashVM;

    @Before
    public void setup() {
        super.setup();
        getTestAppComponent().inject(this);
    }

    @After
    public void tearDown() {
        super.tearDown();
        navigationManager.clearAllViewsFromStack();
        accountFacade.clearDataBaseItems();

        sleep(1);
    }

    @Test
    public void navigateToLoginScreenTest() {
        splashVM = new SplashVM(accountFacade, navigationManager, screenManager);

        sleep(4);

        Assert.assertNull(accountFacade.getAccountInfoFromCache());
        Assert.assertTrue(navigationManager.isOnLastScreen());
        Assert.assertTrue(navigationManager.peek() instanceof LoginScreen);
    }

    @Test
    public void navigateToAccountInfoScreenTest() {
        Mockito.when(accountRestClient.getAccountInformation(Mockito.anyString())).thenReturn(getUserResponse());

        Assert.assertNotNull(accountFacade.getAccountInfoFromServer());

        splashVM = new SplashVM(accountFacade, navigationManager, screenManager);

        sleep(4);

        Assert.assertTrue(navigationManager.isOnLastScreen());
        Assert.assertTrue(navigationManager.peek() instanceof AccountInfoScreen);
    }

    private UserResponseSM getUserResponse() {
        UserResponseSM responseSM = new UserResponseSM();
        responseSM.id = "1";
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
