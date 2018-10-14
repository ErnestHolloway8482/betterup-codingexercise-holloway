package com.betterup.codingexercise.models.viewmodels;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

/**
 * {@link android.arch.lifecycle.ViewModel} for the control logic used to configure and display global screen elements such
 * as the {@link android.widget.Toolbar}, loading spinner, and {@link android.support.v7.app.AppCompatDialog}.
 */
public class MainActivityVM extends BaseVM {
    public final ObservableField<String> progressDialogMessage = new ObservableField<>();
    public final ObservableBoolean isProgressDialogVisible = new ObservableBoolean();

    public final ObservableBoolean isToolBarVisible = new ObservableBoolean();
    public final ObservableField<String> toolBarTitle = new ObservableField<>();
    public final ObservableBoolean isToolBarBackButtonVisible = new ObservableBoolean();

    public MainActivityVM() {
        isToolBarVisible.set(false);
        isToolBarBackButtonVisible.set(false);
        toolBarTitle.set(null);
    }

    public void onNavigationBackButtonPressed() {
        //TODO add code for calling out the actvities onBack.
    }

    public void displayProgressDialog(final String message) {
        progressDialogMessage.set(message);
        isProgressDialogVisible.set(true);
    }

    public void displayProgressDialog() {
        displayProgressDialog("");
    }

    public void displayProgressDialog(final int resourceId) {
        //TODO figure out how to convert resource id to string.
        //displayProgressDialog(MainActivity.getInstance().getString(resourceId));
    }

    public void dismissProgressDialog() {
        progressDialogMessage.set("");
        isProgressDialogVisible.set(false);
    }

    public void displayToolBar(final boolean displayBackButton, final String screenTitle) {
        isToolBarVisible.set(true);
        isToolBarBackButtonVisible.set(displayBackButton);
        toolBarTitle.set(screenTitle);
    }

    public void dismissToolbar() {
        isToolBarVisible.set(false);
        isToolBarBackButtonVisible.set(false);
    }
}
