package com.betterup.codingexercise.models.viewmodels;

import android.databinding.ObservableField;

import com.betterup.codingexercise.models.domainmodels.AccountInfoDOM;

public class AccountInfoVM {
    public final ObservableField<AccountInfoDOM> accountInfoDOM = new ObservableField<>();
    public final ObservableField<String> errorMessage = new ObservableField<>();

    private void getAccountInfoFromServer(){

    }

    private void displayErrorMessage(){

    }

}
