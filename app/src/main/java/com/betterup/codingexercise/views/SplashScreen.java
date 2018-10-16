package com.betterup.codingexercise.views;

import android.content.Context;
import android.util.AttributeSet;

import com.betterup.codingexercise.R;
import com.betterup.codingexercise.databinding.SplashScreenBinding;
import com.betterup.codingexercise.models.viewmodels.SplashVM;

public class SplashScreen extends ScreenImpl<SplashVM, SplashScreenBinding> {
    public SplashScreen(final Context context) {
        super(context, R.layout.splash_screen);
    }

    public SplashScreen(final Context context, final AttributeSet attrs) {
        super(context, attrs, R.layout.splash_screen);
    }

    public SplashScreen(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr, R.layout.splash_screen);
    }
}
