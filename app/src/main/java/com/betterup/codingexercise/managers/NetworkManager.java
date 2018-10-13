package com.betterup.codingexercise.managers;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.underarmour.android.mmf.activities.MainActivity;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * This Singleton class can be used to see if any network connectivity is available.
 */
@Singleton
public class NetworkManager implements INetworkManager {

    @Inject
    public NetworkManager() {

    }

    @Override
    public boolean connectedToNetwork() {
        ConnectivityManager cm =
                (ConnectivityManager) MainActivity.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }
}
