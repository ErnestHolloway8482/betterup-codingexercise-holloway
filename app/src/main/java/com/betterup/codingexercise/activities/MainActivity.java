package com.betterup.codingexercise.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.betterup.codingexercise.R;

public class MainActivity extends AppCompatActivity {
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

    public static Activity getInstance() {
        return instance;
    }

    private void cleanUp() {
        instance = null;
    }
}
