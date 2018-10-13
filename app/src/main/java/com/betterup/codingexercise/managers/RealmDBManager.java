package com.betterup.codingexercise.managers;

import android.content.Context;

import com.betterup.codingexercise.utilities.BuildConfigUtility;
import com.betterup.codingexercise.utilities.LoggerUtils;
import com.betterup.codingexercise.utilities.StringUtility;

import javax.inject.Singleton;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * This singleton manager class is responsible for setting up the database file name, schema, revision number and serves as the centralized
 * setup location for allowing the app to utilize Realm for storing it's objects.
 */
@Singleton
public class RealmDBManager implements DatabaseManager {
    private static final String TAG = "RealmDBManager";
    private static final String DB_FILE_NAME = "betterup.realm";
    private static final String DB_FILE_NAME_TEST = "betterup_test.realm";
    private static final int SCHEMA_VERSION = 1;
    private final Context context;

    private Realm realm;

    public RealmDBManager(final Context context) {
        this.context = context;
    }

    @Override
    public void openDatabase(final String fileName) {
        if (BuildConfigUtility.isInTestMode()) {
            setupDefaultConfiguration(context, generateFileName(fileName));
        } else {
            setupDefaultConfigurationForTest(context, generateFileName(fileName));
        }
    }

    @Override
    public void closeDatabase() {
        closeRealm();
    }

    @Override
    public boolean deleteDatabase() {
        return Realm.deleteRealm(Realm.getDefaultConfiguration());
    }

    public Realm getRealm() {
        if (realm == null) {
            realm = Realm.getDefaultInstance();
        }

        return realm;
    }

    private void closeRealm() {
        Runnable runnable = () -> {
            Realm realm = Realm.getDefaultInstance();
            realm.close();
        };

        Thread thread = new Thread(runnable);
        thread.run();
    }

    private void setupDefaultConfiguration(final Context context, final String fileName) {
        // The RealmConfiguration is created using the builder pattern.
        Realm.init(context);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name(fileName)
                .schemaVersion(SCHEMA_VERSION)
                .deleteRealmIfMigrationNeeded()
                .build();

        Realm.setDefaultConfiguration(realmConfiguration);

        LoggerUtils.log(TAG, "setupDefaultConfiguration");
    }

    private void setupDefaultConfigurationForTest(final Context context, final String fileName) {
        // The RealmConfiguration is created using the builder pattern.
        Realm.init(context);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name(fileName)
                .schemaVersion(SCHEMA_VERSION)
                .deleteRealmIfMigrationNeeded()
                .inMemory()
                .build();

        Realm.setDefaultConfiguration(realmConfiguration);

        LoggerUtils.log(TAG, "setupDefaultConfigurationForTest");
    }

    private String generateFileName(final String fileName) {
        if (BuildConfigUtility.isInTestMode()) {
            return !StringUtility.isEmpty(fileName) ? fileName : DB_FILE_NAME_TEST;
        } else {
            return !StringUtility.isEmpty(fileName) ? fileName : DB_FILE_NAME;
        }
    }
}
