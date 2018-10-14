package com.betterup.codingexercise.managers;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import javax.inject.Singleton;

/**
 * This Singleton class can be used to see if any network connectivity is available.
 */
@Singleton
public class NetworkManagerImpl implements NetworkManager {
    private final ConnectivityManager connectivityManager;

    public NetworkManagerImpl(final Context context) {
        this.connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    @Override
    public boolean connectedToNetwork() {
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();

        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }
}
