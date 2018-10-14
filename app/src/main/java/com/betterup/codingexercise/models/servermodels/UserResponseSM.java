package com.betterup.codingexercise.models.servermodels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * A serer data model that represents the User restful API response.
 * Note that in the interest of time only the values that are actually going to be consumed from within the
 * app are included as part of the response. If this were a full production version the entire response
 * fields would be included.
 */
public class UserResponseSM {
    public int id;
    public String name;

    @SerializedName("time_zone")
    public String timeZone;

    public String title;

    public String motivation;

    @SerializedName("avatar")
    @Expose
    public Avatar avatar;

    public String phone;

    @SerializedName("activated_at")
    public String activatedAt;

    @SerializedName("email")
    public String email;

    @SerializedName("last_active_at")
    @Expose
    public String lastActiveAt;

    @SerializedName("sms_enabled")
    public boolean smsEnabled;

    @SerializedName("email_messages_enabled")
    public boolean emailMessagesEnabled;

    public static class Avatar {
        public Links links;
    }

    public static class Links {
        public Thumbnail thumbnail;
    }

    public static class Thumbnail {
        public String href;
    }
}
