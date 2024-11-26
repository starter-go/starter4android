package com.bitwormhole.starter4a.users;


public class OnlineUserInfo extends UserInfo {

    private String domain;
    private String email;
    private String url;
    private String host;
    private int port;


    public OnlineUserInfo() {
    }

    public OnlineUserInfo(OnlineUserInfo src) {
        super(src);
        if (src == null) {
            return;
        }
        this.domain = src.domain;
        this.email = src.email;
        this.host = src.host;
        this.port = src.port;
        this.url = src.url;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
