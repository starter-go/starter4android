package com.bitwormhole.starter4a.contexts;


import com.bitwormhole.starter4j.base.SafeMode;

import java.nio.file.Path;

import com.bitwormhole.starter4a.DataScope;

public final class User extends ScopeContext {

    private final App ownerApp;
    private String url;
    private String host;
    private int port;
    private String token;
    private String username;
    private String password;
    private boolean authenticated; // 是否已验证

    public User(App _owner, SafeMode _mode) {
        super(_owner, DataScope.USER, _mode);
        this.ownerApp = _owner;
    }

    public App getOwnerApp() {
        return ownerApp;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
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
