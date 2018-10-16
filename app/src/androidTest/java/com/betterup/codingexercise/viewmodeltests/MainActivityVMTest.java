package com.betterup.codingexercise.viewmodeltests;

import android.support.test.runner.AndroidJUnit4;

import com.betterup.codingexercise.BaseAndroidUnitTest;
import com.betterup.codingexercise.R;
import com.betterup.codingexercise.managers.NavigationManager;
import com.betterup.codingexercise.managers.ResourceManager;
import com.betterup.codingexercise.managers.ScreenManager;
import com.betterup.codingexercise.models.viewmodels.MainActivityVM;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import javax.inject.Inject;


@RunWith(AndroidJUnit4.class)
public class MainActivityVMTest extends BaseAndroidUnitTest {
    @Inject
    ResourceManager resourceManager;

    @Inject
    NavigationManager navigationManager;

    @Inject
    ScreenManager screenManager;

    private MainActivityVM mainActivityVM;

    @Before
    public void setup() {
        super.setup();
        getTestAppComponent().inject(this);

        mainActivityVM = new MainActivityVM(resourceManager);
    }

    @After
    public void tearDown() {
        super.tearDown();
        navigationManager.clearAllViewsFromStack();
    }

    @Test
    public void initialValuesTest() {
        Assert.assertFalse(mainActivityVM.isToolBarVisible.get());
        Assert.assertFalse(mainActivityVM.isToolBarBackButtonVisible.get());
        Assert.assertNull(mainActivityVM.toolBarTitle.get());
    }

    @Test
    public void displayProgressDialogWithStringMessageTest() {
        String message = "message";

        mainActivityVM.displayProgressDialog(message);

        Assert.assertEquals(message, mainActivityVM.progressDialogMessage.get());
        Assert.assertTrue(mainActivityVM.isProgressDialogVisible.get());
    }

    @Test
    public void displayProgressDialogWithNoMessageTest() {
        mainActivityVM.displayProgressDialog();

        Assert.assertEquals("", mainActivityVM.progressDialogMessage.get());
        Assert.assertTrue(mainActivityVM.isProgressDialogVisible.get());
    }

    @Test
    public void displayProgressDialogWithResourceIdTest() {
        Mockito.when(resourceManager.getString(R.string.login_error_title)).thenReturn("Login Error");
        mainActivityVM.displayProgressDialog(R.string.login_error_title);

        Assert.assertEquals("Login Error", mainActivityVM.progressDialogMessage.get());
        Assert.assertTrue(mainActivityVM.isProgressDialogVisible.get());
        Mockito.verify(resourceManager, Mockito.atMost(1)).getString(R.string.login_error_message);
    }

    @Test
    public void dismissProgressDialogTest() {
        mainActivityVM.dismissProgressDialog();

        Assert.assertEquals("", mainActivityVM.progressDialogMessage.get());
        Assert.assertFalse(mainActivityVM.isProgressDialogVisible.get());
    }

    @Test
    public void displayToolBarTest() {
        mainActivityVM.displayToolBar(true, "Screen Title");

        Assert.assertTrue(mainActivityVM.isToolBarVisible.get());
        Assert.assertTrue(mainActivityVM.isToolBarBackButtonVisible.get());
        Assert.assertEquals("Screen Title", mainActivityVM.toolBarTitle.get());
    }

    @Test
    public void dismissToolbarTest() {
        mainActivityVM.dismissToolbar();

        Assert.assertFalse(mainActivityVM.isToolBarVisible.get());
        Assert.assertFalse(mainActivityVM.isToolBarBackButtonVisible.get());
        Assert.assertNull(mainActivityVM.toolBarTitle.get());
    }
}
