package com.betterup.codingexercise.models.viewmodels;

import android.databinding.ObservableField;

import com.betterup.codingexercise.R;
import com.betterup.codingexercise.activities.MainActivity;
import com.betterup.codingexercise.facades.AccountFacade;
import com.betterup.codingexercise.managers.AlertDialogManager;
import com.betterup.codingexercise.managers.NavigationManager;
import com.betterup.codingexercise.managers.NetworkManager;
import com.betterup.codingexercise.managers.ResourceManager;
import com.betterup.codingexercise.managers.ScreenManager;
import com.betterup.codingexercise.models.domainmodels.AccountInfoDOM;
import com.betterup.codingexercise.utilities.LoggerUtils;
import com.betterup.codingexercise.views.LoginScreen;
import com.betterup.codingexercise.views.Screen;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AccountInfoVM extends BaseVM {
    public final ObservableField<AccountInfoDOM> accountInfoDOM = new ObservableField<>();

    private final AccountFacade accountFacade;
    private final NetworkManager networkManager;
    private final ResourceManager resourceManager;
    private final AlertDialogManager alertDialogManager;
    private final ScreenManager screenManager;
    private final NavigationManager navigationManager;

    private Disposable subscriber;

    public AccountInfoVM(final AccountFacade accountFacade,
                         final NetworkManager networkManager,
                         final ResourceManager resourceManager,
                         final AlertDialogManager alertDialogManager,
                         final ScreenManager screenManager,
                         final NavigationManager navigationManager) {
        this.accountFacade = accountFacade;
        this.networkManager = networkManager;
        this.resourceManager = resourceManager;
        this.alertDialogManager = alertDialogManager;
        this.screenManager = screenManager;
        this.navigationManager = navigationManager;

        setupToolBar();
        setup();
    }

    public void logout() {
        MainActivity.getInstance().runOnUiThread(this::displayLogoutAlertDialog);
    }

    @Override
    public void setupToolBar() {
        MainActivity.getInstance().getViewModel().displayToolBar(false, resourceManager.getString(R.string.account_info_screen_title));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        MainActivity.getInstance().getViewModel().dismissToolbar();
        cleanupSubscribers();
    }

    private void setup() {
        MainActivity.getInstance().getViewModel().displayProgressDialog();

        if (networkManager.connectedToNetwork()) {
            doGetAccountInfoAsync();
        } else {
            displayNetworkErrorMessage();
        }
    }

    private void doGetAccountInfoAsync() {
        cleanupSubscribers();

        subscriber = Single.fromCallable(this::getAccountInfo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleGetAccountInfo, throwable -> LoggerUtils.log(throwable.getMessage()));

    }

    private boolean getAccountInfo() {
        accountInfoDOM.set(accountFacade.getAccountInfoFromCache());

        AccountInfoDOM accountInfoFromServer = accountFacade.getAccountInfoFromServer();

        if (accountInfoFromServer != null) {
            accountInfoDOM.set(accountInfoFromServer);
        }

        return accountInfoDOM.get() != null;
    }

    private void handleGetAccountInfo(boolean successful) {
        MainActivity.getInstance().getViewModel().dismissProgressDialog();

        if (!successful) {
            displayAccountInfoErrorMessage();
        }
    }

    private void displayNetworkErrorMessage() {
        String title = resourceManager.getString(R.string.network_error_title);
        String body = resourceManager.getString(R.string.network_error_message);

        MainActivity.getInstance().runOnUiThread(() -> alertDialogManager.displayAlertMessage(title, body, "OK", () -> getAccountInfo()));
    }

    private void displayAccountInfoErrorMessage() {
        String title = resourceManager.getString(R.string.account_info_error_title);
        String body = resourceManager.getString(R.string.account_info_error_message);

        MainActivity.getInstance().runOnUiThread(() -> alertDialogManager.displayAlertMessage(title, body));
    }

    private void displayLogoutErrorMessage() {
        String title = resourceManager.getString(R.string.logout_error_title);
        String body = resourceManager.getString(R.string.logout_error_message);

        MainActivity.getInstance().runOnUiThread(() -> alertDialogManager.displayAlertMessage(title, body));
    }

    private void displayLogoutAlertDialog() {
        alertDialogManager.displayAlertMessage(
                resourceManager.getString(R.string.logout_alert_title),
                resourceManager.getString(R.string.logout_alert_message),
                resourceManager.getString(R.string.logout_alert_positive_button_title),
                this::doLogoutAsync,
                resourceManager.getString(R.string.logout_alert_negative_button_title),
                () -> {
                }
        );
    }

    private void cleanupSubscribers() {
        if (subscriber != null) {
            if (!subscriber.isDisposed()) {
                subscriber.dispose();
                subscriber = null;
            }
        }
    }

    private void doLogoutAsync() {
        cleanupSubscribers();

        MainActivity.getInstance().getViewModel().dismissProgressDialog();

        subscriber = Single.fromCallable(() -> accountFacade.clearDataBaseItems())
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleLogout, throwable -> LoggerUtils.log(throwable.getMessage()));
    }

    private void handleLogout(final boolean successful) {
        if (successful) {
            MainActivity.getInstance().runOnUiThread(this::navigateToLoginScreen);
        } else {
            displayLogoutErrorMessage();
        }
    }

    private void navigateToLoginScreen() {
        Screen loginScreen = screenManager.getScreenFromClass(LoginScreen.class);

        navigationManager.clearAllViewsFromStack();
        navigationManager.push(loginScreen);
        navigationManager.showScreen();
    }
}
