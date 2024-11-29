package com.bitwormhole.starter4a.security;

import android.content.Context;

public class SecurityContext {

    private Context context;
    private KeyPairManager keyPairManager;

    public SecurityContext() {
        this.keyPairManager = new KeyPairManager(this);
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public KeyPairManager getKeyPairManager() {
        return keyPairManager;
    }

    public void setKeyPairManager(KeyPairManager keyPairManager) {
        this.keyPairManager = keyPairManager;
    }
}
