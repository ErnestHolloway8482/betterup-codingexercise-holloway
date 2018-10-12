package com.betterup.codingexercise.models.viewmodels;

import android.databinding.ObservableField;

public class LoginVM {
    public final ObservableField<String> userName = new ObservableField<>();
    public final ObservableField<String> passWord = new ObservableField<>();
    public final ObservableField<String> errorMessage = new ObservableField<>();


    public void login(){

    }

    private void navigateToAccountInformationScreen(){

    }

    private void displayLoginErrorMessage(){

    }
}
