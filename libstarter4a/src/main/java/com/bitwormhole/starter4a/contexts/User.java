package com.bitwormhole.starter4a.contexts;


import com.bitwormhole.starter4j.base.SafeMode;

public final class User extends ScopeContext {

    private final App ownerApp;

    public User(App _owner, SafeMode _mode) {
        super(_owner, _mode);
        this.ownerApp = _owner;
    }

    public App getOwnerApp() {
        return ownerApp;
    }
}
