package com.betterup.codingexercise;

import com.betterup.codingexercise.activities.MainActivity;
import com.betterup.codingexercise.managers.MainActivityProviderManager;
import com.betterup.codingexercise.models.viewmodels.MainActivityVM;

import org.mockito.Mockito;

public class MainActivityProviderManageAndroidTestImpl implements MainActivityProviderManager {
    @Override
    public MainActivity provideMainActivity() {
        MainActivityVM mainActivityVM = Mockito.mock(MainActivityVM.class);

        MainActivity mainActivity = Mockito.mock(MainActivity.class);
        Mockito.when(mainActivity.getViewModel()).thenReturn(mainActivityVM);

        return mainActivity;
    }

    @Override
    public void runOnUiThread(final Runnable runnable) {
        if(runnable == null){
            return;
        }

        runnable.run();
    }
}
