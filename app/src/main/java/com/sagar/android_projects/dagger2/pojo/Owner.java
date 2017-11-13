package com.sagar.android_projects.dagger2.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sagar on 11/13/2017.
 */
public class Owner {
    @SerializedName("url")
    private String url;

    public Owner(String url) {
        this.url = url;
    }

    public Owner() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
