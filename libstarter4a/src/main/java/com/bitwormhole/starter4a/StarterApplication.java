package com.bitwormhole.starter4a;

import android.app.Application;

import com.bitwormhole.starter4a.contexts.Customizer;
import com.bitwormhole.starter4a.contexts.Current;
import com.bitwormhole.starter4a.contexts.CurrentHolder;
import com.bitwormhole.starter4a.contexts.MainModuleProvider;
import com.bitwormhole.starter4j.Initializer;
import com.bitwormhole.starter4j.application.Module;


public class StarterApplication extends Application implements MainModuleProvider, Customizer {

    private CurrentHolder mCurrentHolder;

    @Override
    public void onCreate() {
        super.onCreate();
        AppCrashHandler.setup(this);
    }

    @Override
    public Module getMainModule() {
        return ThisModule.module();
    }

    @Override
    public void customize(Initializer i) {
        StarterApplicationCustomizer sac = new StarterApplicationCustomizer(this);
        sac.customize(i);
    }

    private static final class MyCurrentHolder implements CurrentHolder {
        final Current current = new Current();

        @Override
        public Current getCurrent() {
            return this.current;
        }
    }

    private final CurrentHolder getCurrentHolder() {
        CurrentHolder ch = mCurrentHolder;
        if (ch == null) {
            ch = new MyCurrentHolder();
            ch.getCurrent().setContext(this);
            mCurrentHolder = ch;
        }
        return ch;
    }

    @Override
    public String getSystemServiceName(Class<?> serviceClass) {
        if (CurrentHolder.class.equals(serviceClass)) {
            return serviceClass.getName();
        }
        return super.getSystemServiceName(serviceClass);
    }

    @Override
    public Object getSystemService(String name) {
        final String want = CurrentHolder.class.getName();
        if (want.equals(name)) {
            return this.getCurrentHolder();
        }
        return super.getSystemService(name);
    }
}
