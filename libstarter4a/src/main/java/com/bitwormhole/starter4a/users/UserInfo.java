package com.bitwormhole.starter4a.users;


public class UserInfo {

    private long id; // the user id
    private String name; // the user name
    private String displayName; // the nick name
    private String avatar; // a url to avatar image

    public UserInfo() {
    }

    public UserInfo(UserInfo src) {
        if (src == null) {
            return;
        }
        this.id = src.id;
        this.avatar = src.avatar;
        this.name = src.name;
        this.displayName = src.displayName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
