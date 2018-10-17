package com.betterup.codingexercise;

import com.betterup.codingexercise.dimodules.DaggerTestAppComponent;
import com.betterup.codingexercise.dimodules.TestAppComponent;
import com.betterup.codingexercise.utilities.BuildConfigUtility;
import com.betterup.codingexercise.utilities.LoggerUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class BaseUnitTest {
    private TestAppComponent testAppComponent;

    public BaseUnitTest() {
        testAppComponent = DaggerTestAppComponent.builder().build();

        testAppComponent.inject(this);

        BuildConfigUtility.setIsInAndroidTestMode(false);
        BuildConfigUtility.setIsInTestMode(true);
    }

    @Before
    public void setup() {

    }

    @After
    public void tearDown() {

    }

    protected TestAppComponent getTestAppComponent() {
        return testAppComponent;
    }

    protected void sleep(final int numberOfSeconds) {
        try {
            int timeInSeconds = 1000 * numberOfSeconds;
            Thread.sleep(timeInSeconds);
        } catch (InterruptedException e) {
            LoggerUtils.logError(e.getMessage());
        }
    }
}
