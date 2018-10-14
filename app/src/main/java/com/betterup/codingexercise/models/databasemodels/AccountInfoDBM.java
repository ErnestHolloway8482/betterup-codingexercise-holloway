package com.betterup.codingexercise.models.databasemodels;

import io.realm.RealmObject;
import io.realm.annotations.Index;

public class AccountInfoDBM extends RealmObject {
    @Index
    private int id;

    //Personal Information
    private String title;
    private String motivation;
    private String name;
    private String phoneNumber;
    private String email;

    //Avatar
    private String avatarUrl;

    //TimeZone Preferences
    private String timeZonePreference;

    //Notifications
    private boolean smsNotificationEnabled;
    private boolean emailNotificationEnabled;

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getMotivation() {
        return motivation;
    }

    public void setMotivation(final String motivation) {
        this.motivation = motivation;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(final String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(final String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getTimeZonePreference() {
        return timeZonePreference;
    }

    public void setTimeZonePreference(final String timeZonePreference) {
        this.timeZonePreference = timeZonePreference;
    }

    public boolean isSmsNotificationEnabled() {
        return smsNotificationEnabled;
    }

    public void setSmsNotificationEnabled(final boolean smsNotificationEnabled) {
        this.smsNotificationEnabled = smsNotificationEnabled;
    }

    public boolean isEmailNotificationEnabled() {
        return emailNotificationEnabled;
    }

    public void setEmailNotificationEnabled(final boolean emailNotificationEnabled) {
        this.emailNotificationEnabled = emailNotificationEnabled;
    }
}
