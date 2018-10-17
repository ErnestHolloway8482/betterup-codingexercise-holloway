package com.betterup.codingexercise.unittests;

import com.betterup.codingexercise.BaseUnitTest;
import com.betterup.codingexercise.managers.ScreenManager;
import com.betterup.codingexercise.views.AccountInfoScreen;
import com.betterup.codingexercise.views.LoginScreen;
import com.betterup.codingexercise.views.SplashScreen;
import com.betterup.codingexercise.views.ViewContainer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import javax.inject.Inject;

@RunWith(JUnit4.class)
public class ScreenManagerTest extends BaseUnitTest {
    @Inject
    ScreenManager screenManager;

    private ViewContainer viewContainer;

    @Before
    public void setup(){
        super.setup();

        getTestAppComponent().inject(this);
    }

    @Test
    public void getScreenFromClassTest(){
        Assert.assertTrue(screenManager.getScreenFromClass(SplashScreen.class) instanceof  SplashScreen);
        Assert.assertTrue(screenManager.getScreenFromClass(LoginScreen.class) instanceof  LoginScreen);
        Assert.assertTrue(screenManager.getScreenFromClass(AccountInfoScreen.class) instanceof  AccountInfoScreen);
    }
}
