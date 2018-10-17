package com.betterup.codingexercise.models.viewmodels;

import android.databinding.ObservableField;

import com.betterup.codingexercise.R;
import com.betterup.codingexercise.activities.MainActivity;
import com.betterup.codingexercise.facades.AccountFacade;
import com.betterup.codingexercise.managers.AlertDialogManager;
import com.betterup.codingexercise.managers.NetworkManager;
import com.betterup.codingexercise.managers.ResourceManager;
import com.betterup.codingexercise.models.domainmodels.AccountInfoDOM;
import com.betterup.codingexercise.utilities.LoggerUtils;

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

    private Disposable subscriber;

    public AccountInfoVM(final AccountFacade accountFacade,
                         final NetworkManager networkManager,
                         final ResourceManager resourceManager,
                         final AlertDialogManager alertDialogManager) {
        this.accountFacade = accountFacade;
        this.networkManager = networkManager;
        this.resourceManager = resourceManager;
        this.alertDialogManager = alertDialogManager;

        setupToolBar();
        setup();
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

    private void doGetAccountInfoAsync(){
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

    private void handleGetAccountInfo(boolean successful){
        MainActivity.getInstance().getViewModel().dismissProgressDialog();

        if(!successful){
            displayAccountInfoErrorMessage();
        }
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

    private void cleanupSubscribers() {
        if (subscriber != null) {
            if (!subscriber.isDisposed()) {
                subscriber.dispose();
                subscriber = null;
            }
        }
    }
}
