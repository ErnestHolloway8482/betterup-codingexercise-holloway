package com.betterup.codingexercise.viewmodeltests;

import android.content.Context;
import android.support.test.runner.AndroidJUnit4;

import com.betterup.codingexercise.BaseAndroidUnitTest;
import com.betterup.codingexercise.R;
import com.betterup.codingexercise.facades.AccountFacade;
import com.betterup.codingexercise.managers.AlertDialogManager;
import com.betterup.codingexercise.managers.MainActivityProviderManager;
import com.betterup.codingexercise.managers.NavigationManager;
import com.betterup.codingexercise.managers.NetworkManager;
import com.betterup.codingexercise.managers.ResourceManager;
import com.betterup.codingexercise.managers.ScreenManager;
import com.betterup.codingexercise.models.servermodels.UserResponseSM;
import com.betterup.codingexercise.models.viewmodels.AccountInfoVM;
import com.betterup.codingexercise.restclients.AccountRestClient;

import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import javax.inject.Inject;


@RunWith(AndroidJUnit4.class)
public class AccountInfoVMTest extends BaseAndroidUnitTest {

    @Inject
    NavigationManager navigationManager;

    @Inject
    NetworkManager networkManager;

    @Inject
    AlertDialogManager alertDialogManager;

    @Inject
    ResourceManager resourceManager;

    @Inject
    Context context;

    @Inject
    AccountRestClient accountRestClient;

    @Inject
    AccountFacade accountFacade;

    @Inject
    ScreenManager screenManager;

    @Inject
    MainActivityProviderManager mainActivityProviderManager;

    private AccountInfoVM accountInfoVM;

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
    public void displayNetworkErrorMessageTest() {
        Mockito.when(networkManager.connectedToNetwork()).thenReturn(false);

        accountInfoVM = new AccountInfoVM(accountFacade, networkManager, resourceManager, alertDialogManager, screenManager, navigationManager, mainActivityProviderManager);

        Mockito.when(resourceManager.getString(R.string.network_error_title)).thenReturn("network_error_title");
        Mockito.when(resourceManager.getString(R.string.network_error_message)).thenReturn("network_error_message");

        String title = resourceManager.getString(R.string.network_error_title);
        String message = resourceManager.getString(R.string.network_error_message);

        Mockito.verify(alertDialogManager, Mockito.atMost(1)).displayAlertMessage(title, message);
    }

    @Test
    public void displayAccountInfoErrorMessageTest() {
        Mockito.when(networkManager.connectedToNetwork()).thenReturn(true);

        accountInfoVM = new AccountInfoVM(accountFacade, networkManager, resourceManager, alertDialogManager, screenManager, navigationManager, mainActivityProviderManager);

        String title = context.getString(R.string.account_info_error_title);
        String message = context.getString(R.string.account_info_error_message);

        Mockito.verify(alertDialogManager, Mockito.atMost(1)).displayAlertMessage(title, message);
    }

    @Test
    public void getAccountInfoTest() {
        Mockito.when(networkManager.connectedToNetwork()).thenReturn(true);
        Mockito.when(accountRestClient.getAccountInformation(Mockito.anyString())).thenReturn(getUserResponse());

        accountInfoVM = new AccountInfoVM(accountFacade, networkManager, resourceManager, alertDialogManager, screenManager, navigationManager, mainActivityProviderManager);

        Mockito.verify(alertDialogManager, Mockito.never()).displayAlertMessage(Mockito.anyString(), Mockito.anyString());
    }

    @Test
    public void displayLogoutAlertDialogTest() {
        Mockito.when(networkManager.connectedToNetwork()).thenReturn(true);
        Mockito.when(accountRestClient.getAccountInformation(Mockito.anyString())).thenReturn(getUserResponse());

        accountInfoVM = new AccountInfoVM(accountFacade, networkManager, resourceManager, alertDialogManager, screenManager, navigationManager, mainActivityProviderManager);

        String title = context.getString(R.string.logout_alert_title);
        String message = context.getString(R.string.logout_alert_message);
        String alertPositiveButton = context.getString(R.string.logout_alert_positive_button_title);
        String alertNegativeButton = context.getString(R.string.logout_alert_negative_button_title);

        accountInfoVM.logout();

        Mockito.verify(alertDialogManager, Mockito.atMost(1)).displayAlertMessage(
                Mockito.eq(title),
                Mockito.eq(message),
                Mockito.eq(alertPositiveButton),
                Mockito.any(Runnable.class),
                Mockito.eq(alertNegativeButton),
                Mockito.any(Runnable.class));
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
