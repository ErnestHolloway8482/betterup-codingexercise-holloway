package com.betterup.codingexercise.models.viewmodels;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import com.betterup.codingexercise.activities.MainActivity;
import com.betterup.codingexercise.managers.ResourceManager;

/**
 * {@link android.arch.lifecycle.ViewModel} for the control logic used to configure and display global screen elements such
 * as the {@link android.widget.Toolbar}, loading spinner, and {@link android.support.v7.app.AppCompatDialog}.
 */
public class MainActivityVM extends BaseVM {
    private final ResourceManager resourceManager;

    public final ObservableField<String> progressDialogMessage = new ObservableField<>();
    public final ObservableBoolean isProgressDialogVisible = new ObservableBoolean();

    public final ObservableBoolean isToolBarVisible = new ObservableBoolean();
    public final ObservableField<String> toolBarTitle = new ObservableField<>();
    public final ObservableBoolean isToolBarBackButtonVisible = new ObservableBoolean();

    public MainActivityVM(final ResourceManager resourceManager) {
        this.resourceManager = resourceManager;

        isToolBarVisible.set(false);
        isToolBarBackButtonVisible.set(false);
        toolBarTitle.set(null);
    }

    public void onNavigationBackButtonPressed() {
        MainActivity.getInstance().onBackPressed();
    }

    public void displayProgressDialog(final String message) {
        progressDialogMessage.set(message);
        isProgressDialogVisible.set(true);
    }

    public void displayProgressDialog() {
        displayProgressDialog("");
    }

    public void displayProgressDialog(final int resourceId) {
        displayProgressDialog(resourceManager.getString(resourceId));
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
        toolBarTitle.set(null);
    }
}
