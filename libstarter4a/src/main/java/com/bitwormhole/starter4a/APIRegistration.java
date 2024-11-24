package com.bitwormhole.starter4a;

public final class APIRegistration {

    private Class<?> type;
    private RemoteAPI remote;
    private LocalAPI local;
    private String name;

    public APIRegistration() {
    }

    public Class<?> getType() {
        return type;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }

    public RemoteAPI getRemote() {
        return remote;
    }

    public void setRemote(RemoteAPI remote) {
        this.remote = remote;
    }

    public LocalAPI getLocal() {
        return local;
    }

    public void setLocal(LocalAPI local) {
        this.local = local;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
