package com.betterup.codingexercise.models.viewmodels;

import com.betterup.codingexercise.activities.MainActivity;
import com.betterup.codingexercise.facades.AccountFacade;
import com.betterup.codingexercise.managers.NavigationManager;
import com.betterup.codingexercise.managers.ScreenManager;
import com.betterup.codingexercise.utilities.LoggerUtils;
import com.betterup.codingexercise.views.AccountInfoScreen;
import com.betterup.codingexercise.views.LoginScreen;
import com.betterup.codingexercise.views.Screen;

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
    private final AccountFacade accountFacade;
    private final NavigationManager navigationManager;
    private final ScreenManager screenManager;

    private Disposable delaySubscriber;

    public SplashVM(final AccountFacade accountFacade, final NavigationManager navigationManager, final ScreenManager screenManager) {
        this.accountFacade = accountFacade;
        this.navigationManager = navigationManager;
        this.screenManager = screenManager;

        delayNavigationTimeToCorrectScreen();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        MainActivity.getInstance().getViewModel().dismissToolbar();
        cleanupSubscribers();
    }

    private void delayNavigationTimeToCorrectScreen() {
        int navigationDelay = 2;

        cleanupSubscribers();

        delaySubscriber = Observable.timer(navigationDelay, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(__ -> navigateToCorrectScreen(), throwable -> LoggerUtils.log(throwable.getMessage()));
    }

    private void navigateToCorrectScreen() {
        if (accountFacade.getAccountInfoFromCache() != null) {
            navigateToAccountInfoScreen();
        } else {
            navigateToLoginScreen();
        }

        cleanupSubscribers();
    }

    private void navigateToLoginScreen() {
        Screen loginScreen = screenManager.getScreenFromClass(LoginScreen.class);

        navigationManager.clearAllViewsFromStack();
        navigationManager.push(loginScreen);
        navigationManager.showScreen();
    }

    private void navigateToAccountInfoScreen() {
        Screen accountInfoScreen = screenManager.getScreenFromClass(AccountInfoScreen.class);

        navigationManager.clearAllViewsFromStack();
        navigationManager.push(accountInfoScreen);
        navigationManager.showScreen();
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
