package com.bitwormhole.starter4a.contexts;


import com.bitwormhole.starter4a.users.LocalUserInfo;
import com.bitwormhole.starter4a.users.OnlineUserInfo;
import com.bitwormhole.starter4j.base.SafeMode;

import java.nio.file.Path;

import com.bitwormhole.starter4a.DataScope;

public final class User extends ScopeContext {

    private final App ownerApp;

    private String token;
    private boolean authenticated; // 是否已验证
    private OnlineUserInfo onlineUserInfo;
    private LocalUserInfo localUserInfo;

    public User(App _owner, SafeMode _mode) {
        super(_owner, DataScope.USER, _mode);
        this.ownerApp = _owner;
    }

    public LocalUserInfo getLocalUserInfo() {
        return localUserInfo;
    }

    public void setLocalUserInfo(LocalUserInfo localUserInfo) {
        this.localUserInfo = localUserInfo;
    }

    public OnlineUserInfo getOnlineUserInfo() {
        return onlineUserInfo;
    }

    public void setOnlineUserInfo(OnlineUserInfo onlineUserInfo) {
        this.onlineUserInfo = onlineUserInfo;
    }

    public App getOwnerApp() {
        return ownerApp;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }
}
