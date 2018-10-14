package com.betterup.codingexercise.models.viewmodels;

import android.databinding.ObservableField;

import com.betterup.codingexercise.facades.AccountFacade;
import com.betterup.codingexercise.managers.NetworkManager;
import com.betterup.codingexercise.models.domainmodels.AccountInfoDOM;

public class AccountInfoVM extends BaseVM {
    public final ObservableField<AccountInfoDOM> accountInfoDOM = new ObservableField<>();
    public final ObservableField<String> errorMessage = new ObservableField<>();

    private final AccountFacade accountFacade;
    private final NetworkManager networkManager;

    public AccountInfoVM(final AccountFacade accountFacade, final NetworkManager networkManager){
        this.accountFacade = accountFacade;
        this.networkManager = networkManager;

        getAccountInfoFromServer();
    }

    private void getAccountInfoFromServer(){

    }

    private void displayErrorMessage(){

    }
}
