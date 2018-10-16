package com.betterup.codingexercise.managers;

import com.betterup.codingexercise.activities.MainActivity;
import com.betterup.codingexercise.views.AccountInfoScreen;
import com.betterup.codingexercise.views.LoginScreen;
import com.betterup.codingexercise.views.Screen;
import com.betterup.codingexercise.views.SplashScreen;

import javax.inject.Singleton;

@Singleton
public class ScreenManagerImpl implements ScreenManager {
    @Override
    public <T extends Screen> Screen getScreenFromClass(final Class<T> screenClass) {
        if (screenClass == SplashScreen.class) {
            return new SplashScreen(MainActivity.getInstance());
        } else if (screenClass == LoginScreen.class) {
            return new LoginScreen(MainActivity.getInstance());
        } else if (screenClass == AccountInfoScreen.class) {
            return new AccountInfoScreen(MainActivity.getInstance());
        } else {
            return null;
        }
    }
}
