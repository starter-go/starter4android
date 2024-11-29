package com.bitwormhole.starter4a;

import android.os.IBinder;

import com.bitwormhole.starter4a.contexts.Current;
import com.bitwormhole.starter4a.settings.SettingManager;

public class StarterBinderHolder {

    private StarterBinder binder;

    public StarterBinderHolder() {
    }


    public interface Listener {
        void callback(StarterBinderHolder holder);
    }


    public StarterBinder getBinder() {
        return binder;
    }

    public void setBinder(StarterBinder binder) {
        this.binder = binder;
    }
}
