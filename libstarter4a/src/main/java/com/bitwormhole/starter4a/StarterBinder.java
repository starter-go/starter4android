package com.bitwormhole.starter4a;

import android.os.IBinder;

import com.bitwormhole.starter4a.contexts.Current;
import com.bitwormhole.starter4a.settings.SettingManager;

public interface StarterBinder {

    IBinder binder();

    <T extends LocalAPI> T getLocalAPI(Class<T> t);

    <T extends RemoteAPI> T getRemoteAPI(Class<T> t);

    SettingManager getSettingManager();

    Current current();

}
