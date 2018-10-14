package com.betterup.codingexercise.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.betterup.codingexercise.R;
import com.betterup.codingexercise.managers.NavigationManager;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {
    @Inject
    NavigationManager navigationManager;

    private static MainActivity instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        instance = this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        cleanUp();
    }

    @Override
    public void onBackPressed() {
        navigationManager.onBackPressed();

        if (navigationManager.isOnLastScreen()) {
            finish();
        }
    }

    public static Activity getInstance() {
        return instance;
    }

    private void cleanUp() {
        instance = null;
    }
}
