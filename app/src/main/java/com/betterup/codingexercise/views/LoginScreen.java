package com.betterup.codingexercise.views;

import android.content.Context;
import android.util.AttributeSet;

import com.betterup.codingexercise.R;
import com.betterup.codingexercise.databinding.AccountInfoScreenBinding;
import com.betterup.codingexercise.models.viewmodels.LoginVM;

public class LoginScreen extends ScreenImpl<LoginVM, AccountInfoScreenBinding> {
    public LoginScreen(final Context context) {
        super(context, R.layout.account_info_screen);
    }

    public LoginScreen(final Context context, final AttributeSet attrs) {
        super(context, attrs, R.layout.account_info_screen);
    }

    public LoginScreen(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr, R.layout.account_info_screen);
    }
}
