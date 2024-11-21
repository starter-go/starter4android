package com.bitwormhole.starter4a;

import android.os.IBinder;

import com.bitwormhole.starter4a.contexts.Current;

public interface StarterBinder {

    IBinder binder();

    LocalAPI getLocalAPI(String name);

    RemoteAPI getRemoteAPI(String name);

    Current current();

}
