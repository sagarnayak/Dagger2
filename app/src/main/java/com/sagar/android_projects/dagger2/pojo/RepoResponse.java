package com.sagar.android_projects.dagger2.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sagar on 11/13/2017.
 * the repo response
 * ------------------------------------------------------------------------------------------------
 * {
 * "id": 1,
 * "name": "grit",
 * "full_name": "mojombo/grit",
 * "owner": {
 * "login": "mojombo",
 * "id": 1,
 * "avatar_url": "https://avatars0.githubusercontent.com/u/1?v=4",
 * "gravatar_id": "",
 * "url": "https://api.github.com/users/mojombo"
 * }
 * }
 * ------------------------------------------------------------------------------------------------
 */
public class RepoResponse {
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("full_name")
    private String fullName;
    @SerializedName("owner")
    private Owner owner;

    public RepoResponse() {
    }

    public RepoResponse(String id, String name, String fullName, Owner owner) {
        this.id = id;
        this.name = name;
        this.fullName = fullName;
        this.owner = owner;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
}
