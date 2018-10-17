package com.betterup.codingexercise.views;

import android.content.Context;
import android.util.AttributeSet;

import com.betterup.codingexercise.R;
import com.betterup.codingexercise.application.BetterUpApplication;
import com.betterup.codingexercise.databinding.LoginScreenBinding;
import com.betterup.codingexercise.models.viewmodels.LoginVM;

public class LoginScreen extends ScreenImpl<LoginVM, LoginScreenBinding> {
    public LoginScreen(final Context context) {
        super(context, R.layout.login_screen);

        BetterUpApplication.getAppComponent().inject(this);

        setViewModel(mViewModel);
    }

    public LoginScreen(final Context context, final AttributeSet attrs) {
        super(context, attrs, R.layout.login_screen);

        BetterUpApplication.getAppComponent().inject(this);

        setViewModel(mViewModel);
    }

    public LoginScreen(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr, R.layout.login_screen);

        BetterUpApplication.getAppComponent().inject(this);

        setViewModel(mViewModel);
    }
}
