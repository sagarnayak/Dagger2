package com.sagar.android_projects.dagger2.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sagar on 11/13/2017.
 * -------------------------------------------------------------------------------------------------
 * {
 * "login": "mojombo",
 * "id": 1,
 * "avatar_url": "https://avatars0.githubusercontent.com/u/1?v=4",
 * "gravatar_id": "",
 * }
 * -------------------------------------------------------------------------------------------------
 */
public class DetailProfile {
    @SerializedName("login")
    private String login;
    @SerializedName("id")
    private String id;
    @SerializedName("avatar_url")
    private String avatarUrl;

    public DetailProfile(String login, String id, String avatarUrl) {
        this.login = login;
        this.id = id;
        this.avatarUrl = avatarUrl;
    }

    public String getLogin() {
        return login;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }
}
