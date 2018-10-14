package com.betterup.codingexercise.models.viewmodels;

import android.databinding.ObservableField;

import com.betterup.codingexercise.facades.AccountFacade;
import com.betterup.codingexercise.managers.NavigationManager;
import com.betterup.codingexercise.managers.NetworkManager;

public class LoginVM extends BaseVM {
    public final ObservableField<String> userName = new ObservableField<>();
    public final ObservableField<String> passWord = new ObservableField<>();
    public final ObservableField<String> errorMessage = new ObservableField<>();

    private final AccountFacade accountFacade;
    private final NavigationManager navigationManager;
    private final NetworkManager networkManager;

    public LoginVM(final AccountFacade accountFacade, final NavigationManager navigationManager, final NetworkManager networkManager){
        this.accountFacade = accountFacade;
        this.navigationManager = navigationManager;
        this.networkManager = networkManager;
    }


    public void login(){
        if(!networkManager.connectedToNetwork()){
            displayNetworkErrorMessage();
        }else if(accountFacade.login(userName.get(), passWord.get())){
            navigateToAccountInformationScreen();
        } else{
            displayLoginErrorMessage();
        }
    }

    private void navigateToAccountInformationScreen(){

    }

    private void displayLoginErrorMessage(){

    }

    private void displayNetworkErrorMessage(){

    }
}
