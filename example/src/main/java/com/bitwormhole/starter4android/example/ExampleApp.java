package com.bitwormhole.starter4android.example;


import com.bitwormhole.starter4a.StarterApplication;
import com.bitwormhole.starter4j.Initializer;
import com.bitwormhole.starter4j.application.Module;
import com.bitwormhole.starter4j.application.properties.Properties;

public class ExampleApp extends StarterApplication {

    @Override
    public Module getMainModule() {
        Module parent = super.getMainModule();
        return ThisModule.module(parent);
    }

    @Override
    public void customize(Initializer i) {
        super.customize(i);
        Properties props = i.getProperties();
        props.setProperty("debug.enabled", "1");
        props.setProperty("debug.log-environment", "1");
    }
}
