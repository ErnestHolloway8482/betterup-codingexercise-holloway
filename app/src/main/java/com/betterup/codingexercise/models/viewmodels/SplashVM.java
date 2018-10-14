package com.betterup.codingexercise.models.viewmodels;

import com.betterup.codingexercise.managers.NavigationManager;
import com.betterup.codingexercise.utilities.LoggerUtils;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * {@link android.arch.lifecycle.ViewModel} that defines the controller logic for the SCREEN that is presented anytime the user starts the app.
 * This class will navigate directly to the SCREEN if the data has already been seeded from the file into the database, otherwise it will
 * seed the data first before allowing the user to view the SCREEN.
 */
public class SplashVM extends BaseVM {
    private final NavigationManager navigationManager;
    private Disposable delaySubscriber;

    public SplashVM(final NavigationManager navigationManager) {
        this.navigationManager = navigationManager;

        navigateToAccountInfoScreen();
    }

    private void navigateToAccountInfoScreen() {

    }

    private void setupAccountInfoScreen() {
        int navigationDelay = 2;

        cleanupSubscribers();

        delaySubscriber = Observable.timer(navigationDelay, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(__ -> setupNavigationStackForAccountInfoScreen(), throwable -> LoggerUtils.log(throwable.getMessage()));
    }

    private void setupNavigationStackForAccountInfoScreen(){

    }

    private void cleanupSubscribers() {
        if (delaySubscriber != null) {
            if (!delaySubscriber.isDisposed()) {
                delaySubscriber.dispose();
                delaySubscriber = null;
            }
        }
    }
}
