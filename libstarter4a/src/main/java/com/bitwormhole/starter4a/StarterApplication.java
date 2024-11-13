package com.bitwormhole.starter4a;

import android.app.Application;

import com.bitwormhole.starter4j.application.Module;


public class StarterApplication extends Application {

    public Module getModule() {
        return null;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        AppCrashHandler.setup(this);
 //       this.mFramework = this.createFramework();
    }
}
