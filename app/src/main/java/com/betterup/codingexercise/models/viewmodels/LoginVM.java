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
import com.betterup.codingexercise.utilities.LoggerUtils;
import com.betterup.codingexercise.views.AccountInfoScreen;
import com.betterup.codingexercise.views.Screen;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginVM extends BaseVM {
    public final ObservableField<String> username = new ObservableField<>();
    public final ObservableField<String> password = new ObservableField<>();

    private final AccountFacade accountFacade;
    private final NavigationManager navigationManager;
    private final NetworkManager networkManager;
    private final AlertDialogManager alertDialogManager;
    private final ScreenManager screenManager;
    private final ResourceManager resourceManager;

    private Disposable subscriber;


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

        setupToolBar();

        //For testing only please remove
        username.set("ernest.holloway@embersoftwarellc.com");
        password.set("Sprinter198!");
    }

    public void login() {
        MainActivity.getInstance().getViewModel().displayProgressDialog();

        if (!networkManager.connectedToNetwork()) {
            displayNetworkErrorMessage();
        } else {
            doLoginAsync();
        }
    }

    @Override
    public void setupToolBar() {
        MainActivity.getInstance().getViewModel().displayToolBar(false, resourceManager.getString(R.string.login_screen_title));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        MainActivity.getInstance().getViewModel().dismissToolbar();
        cleanupSubscribers();
    }

    private void doLoginAsync() {
        cleanupSubscribers();

        subscriber = Single.fromCallable(() -> accountFacade.login(username.get(), password.get()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleLoginResult, throwable -> LoggerUtils.log(throwable.getMessage()));
    }

    private void handleLoginResult(boolean successful) {
        MainActivity.getInstance().getViewModel().dismissProgressDialog();

        if (successful) {
            MainActivity.getInstance().runOnUiThread(this::navigateToAccountInformationScreen);
        } else {
            displayLoginErrorMessage();
        }
    }

    private void navigateToAccountInformationScreen() {
        Screen accountInfoScreen = screenManager.getScreenFromClass(AccountInfoScreen.class);

        navigationManager.clearAllViewsFromStack();
        navigationManager.push(accountInfoScreen);
        navigationManager.showScreen();
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

    private void cleanupSubscribers() {
        if (subscriber != null) {
            if (!subscriber.isDisposed()) {
                subscriber.dispose();
                subscriber = null;
            }
        }
    }
}
