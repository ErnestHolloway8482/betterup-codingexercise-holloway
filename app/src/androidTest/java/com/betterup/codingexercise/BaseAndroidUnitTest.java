package com.betterup.codingexercise;

import android.support.test.runner.AndroidJUnit4;

import com.betterup.codingexercise.dimodules.AndroidTestAppComponent;
import com.betterup.codingexercise.dimodules.DaggerAndroidTestAppComponent;
import com.betterup.codingexercise.managers.DatabaseManager;
import com.betterup.codingexercise.utilities.BuildConfigUtility;
import com.betterup.codingexercise.utilities.LoggerUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;

import javax.inject.Inject;

@RunWith(AndroidJUnit4.class)
public class BaseAndroidUnitTest {
    private AndroidTestAppComponent androidTestAppComponent;

    protected final String TEST_DATABASE_FILENAME = "well_data_android_test";

    @Inject
    public DatabaseManager databaseManager;

    public BaseAndroidUnitTest() {
        androidTestAppComponent = DaggerAndroidTestAppComponent.builder().build();

        androidTestAppComponent.inject(this);

        BuildConfigUtility.setIsInAndroidTestMode(true);
        BuildConfigUtility.setIsInTestMode(true);
    }

    @Before
    public void setup(){
        databaseManager.openDatabase(null);
    }

    @After
    public void tearDown(){
        databaseManager.closeDatabase();
    }

    protected AndroidTestAppComponent getTestAppComponent() {
        return androidTestAppComponent;
    }

    protected void sleep(final int numberOfSeconds) {
        try {
            int timeInSecobds = 1000 * numberOfSeconds;
            Thread.sleep(timeInSecobds);
        } catch (InterruptedException e) {
            LoggerUtils.logError(e.getMessage());
        }
    }
}
