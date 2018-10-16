package com.betterup.codingexercise.models.viewmodels;

import android.databinding.ObservableField;

import com.betterup.codingexercise.R;
import com.betterup.codingexercise.facades.AccountFacade;
import com.betterup.codingexercise.managers.AlertDialogManager;
import com.betterup.codingexercise.managers.NavigationManager;
import com.betterup.codingexercise.managers.NetworkManager;
import com.betterup.codingexercise.managers.ResourceManager;
import com.betterup.codingexercise.managers.ScreenManager;
import com.betterup.codingexercise.views.AccountInfoScreen;
import com.betterup.codingexercise.views.Screen;

public class LoginVM extends BaseVM {
    public final ObservableField<String> username = new ObservableField<>();
    public final ObservableField<String> password = new ObservableField<>();

    private final AccountFacade accountFacade;
    private final NavigationManager navigationManager;
    private final NetworkManager networkManager;
    private final AlertDialogManager alertDialogManager;
    private final ScreenManager screenManager;
    private final ResourceManager resourceManager;

    public LoginVM(final AccountFacade accountFacade,
                   final NavigationManager navigationManager,
                   final NetworkManager networkManager,
                   final AlertDialogManager alertDialogManager,
                   final ScreenManager screenManager,
                   final ResourceManager resourceManager) {
        this.accountFacade = accountFacade;
        this.navigationManager = navigationManager;
        this.networkManager = networkManager;
        this.alertDialogManager = alertDialogManager;
        this.screenManager = screenManager;
        this.resourceManager = resourceManager;
    }

    public void login() {
        if (!networkManager.connectedToNetwork()) {
            displayNetworkErrorMessage();
        } else if (accountFacade.login(username.get(), password.get())) {
            navigateToAccountInformationScreen();
        } else {
            displayLoginErrorMessage();
        }
    }

    private void navigateToAccountInformationScreen() {
        Screen accountInfoScreen = screenManager.getScreenFromClass(AccountInfoScreen.class);

        navigationManager.clearAllViewsFromStack();
        navigationManager.push(accountInfoScreen);
    }

    private void displayLoginErrorMessage() {
        String title = resourceManager.getString(R.string.login_error_title);
        String body = resourceManager.getString(R.string.login_error_message);

        alertDialogManager.displayAlertMessage(title, body);
    }

    private void displayNetworkErrorMessage() {
        String title = resourceManager.getString(R.string.network_error_title);
        String body = resourceManager.getString(R.string.network_error_message);

        alertDialogManager.displayAlertMessage(title, body);
    }
}
