package com.bitwormhole.starter4android.example;


import com.bitwormhole.starter4a.StarterApplication;
import com.bitwormhole.starter4j.application.Module;

public class ExampleApp extends StarterApplication {

    @Override
    public Module getMainModule() {
        Module parent = super.getMainModule();
        return ThisModule.module(parent);
    }
}
