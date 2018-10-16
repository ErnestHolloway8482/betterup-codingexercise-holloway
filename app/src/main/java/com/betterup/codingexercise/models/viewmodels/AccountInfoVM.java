package com.betterup.codingexercise.models.viewmodels;

import android.databinding.ObservableField;

import com.betterup.codingexercise.R;
import com.betterup.codingexercise.facades.AccountFacade;
import com.betterup.codingexercise.managers.AlertDialogManager;
import com.betterup.codingexercise.managers.NetworkManager;
import com.betterup.codingexercise.managers.ResourceManager;
import com.betterup.codingexercise.models.domainmodels.AccountInfoDOM;

public class AccountInfoVM extends BaseVM {
    public final ObservableField<AccountInfoDOM> accountInfoDOM = new ObservableField<>();

    private final AccountFacade accountFacade;
    private final NetworkManager networkManager;
    private final ResourceManager resourceManager;
    private final AlertDialogManager alertDialogManager;

    public AccountInfoVM(final AccountFacade accountFacade,
                         final NetworkManager networkManager,
                         final ResourceManager resourceManager,
                         final AlertDialogManager alertDialogManager) {
        this.accountFacade = accountFacade;
        this.networkManager = networkManager;
        this.resourceManager = resourceManager;
        this.alertDialogManager = alertDialogManager;

        setup();
    }

    private void setup() {
        if (networkManager.connectedToNetwork()) {
            setupAccountInfo();
        } else {
            displayNetworkErrorMessage();
        }
    }

    private void setupAccountInfo() {
        if (!getAccountInfo()) {
            displayAccountInfoErrorMessage();
        }
    }

    private boolean getAccountInfo() {
        accountInfoDOM.set(accountFacade.getAccountInfoFromCache());

        AccountInfoDOM accountInfoFromServer = accountFacade.getAccountInfoFromServer();

        if (accountInfoFromServer != null) {
            accountInfoDOM.set(accountInfoFromServer);
        }

        return accountInfoDOM.get() != null;
    }

    private void displayNetworkErrorMessage() {
        String title = resourceManager.getString(R.string.network_error_title);
        String body = resourceManager.getString(R.string.network_error_message);

        alertDialogManager.displayAlertMessage(title, body, "OK", () -> getAccountInfo());
    }

    private void displayAccountInfoErrorMessage() {
        String title = resourceManager.getString(R.string.account_info_error_title);
        String body = resourceManager.getString(R.string.account_info_error_message);

        alertDialogManager.displayAlertMessage(title, body);
    }
}
