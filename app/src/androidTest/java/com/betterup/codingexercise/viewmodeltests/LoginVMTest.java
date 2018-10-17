package com.betterup.codingexercise.viewmodeltests;

import android.content.Context;
import android.support.test.runner.AndroidJUnit4;

import com.betterup.codingexercise.BaseAndroidUnitTest;
import com.betterup.codingexercise.R;
import com.betterup.codingexercise.facades.AccountFacade;
import com.betterup.codingexercise.managers.AlertDialogManager;
import com.betterup.codingexercise.managers.NavigationManager;
import com.betterup.codingexercise.managers.NetworkManager;
import com.betterup.codingexercise.managers.ResourceManager;
import com.betterup.codingexercise.managers.ScreenManager;
import com.betterup.codingexercise.models.servermodels.LoginRequestSM;
import com.betterup.codingexercise.models.servermodels.OAuthResponseSM;
import com.betterup.codingexercise.models.viewmodels.LoginVM;
import com.betterup.codingexercise.restclients.AccountRestClient;
import com.betterup.codingexercise.views.AccountInfoScreen;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import javax.inject.Inject;


@RunWith(AndroidJUnit4.class)
public class LoginVMTest extends BaseAndroidUnitTest {

    @Inject
    NavigationManager navigationManager;

    @Inject
    NetworkManager networkManager;

    @Inject
    AlertDialogManager alertDialogManager;

    @Inject
    ScreenManager screenManager;

    @Inject
    ResourceManager resourceManager;

    @Inject
    Context context;

    @Inject
    AccountRestClient accountRestClient;

    @Inject
    AccountFacade accountFacade;

    private LoginVM loginVM;

    @Before
    public void setup() {
        super.setup();
        getTestAppComponent().inject(this);

        loginVM = new LoginVM(accountFacade, navigationManager, networkManager, alertDialogManager, screenManager, resourceManager);
    }

    @After
    public void tearDown() {
        super.tearDown();
        accountFacade.clearDataBaseItems();
    }

    @Test
    public void navigateToAccountInformationScreenTest() {
        Mockito.when(networkManager.connectedToNetwork()).thenReturn(true);

        OAuthResponseSM response = new OAuthResponseSM();
        response.accessToken = "accessToken";

        Mockito.when(accountRestClient.login(Mockito.any(LoginRequestSM.class))).thenReturn(response);

        loginVM.username.set("username");
        loginVM.password.set("password");
        loginVM.login();

        Assert.assertTrue(navigationManager.isOnLastScreen());
        Assert.assertTrue(navigationManager.peek() instanceof AccountInfoScreen);
    }

    @Test
    public void displayNetworkErrorTest() {
        Mockito.when(networkManager.connectedToNetwork()).thenReturn(false);

        loginVM.login();

        String title = context.getString(R.string.network_error_title);
        String message = context.getString(R.string.network_error_message);

        Mockito.verify(alertDialogManager, Mockito.atMost(1)).displayAlertMessage(title, message);
    }

    @Test
    public void displayLoginErrorTest() {
        Mockito.when(networkManager.connectedToNetwork()).thenReturn(true);

        Mockito.when(accountRestClient.login(Mockito.any(LoginRequestSM.class))).thenReturn(null);

        loginVM.username.set("username");
        loginVM.password.set("password");
        loginVM.login();

        String title = context.getString(R.string.login_error_title);
        String message = context.getString(R.string.login_error_message);

        Mockito.verify(alertDialogManager, Mockito.atMost(1)).displayAlertMessage(title, message);
    }
}
