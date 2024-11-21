package com.bitwormhole.starter4android.example;

import com.bitwormhole.starter4android.example.res.m.S4AExampleModuleRes;
import com.bitwormhole.starter4j.application.Module;
import com.bitwormhole.starter4j.application.ModuleBuilder;

final class ThisModule {

    final static String theModuleName = ExampleApp.class.getName();
    final static String theModuleVer = "0.0.0";
    final static int theModuleRev = 0;

    private ThisModule() {
    }

    public static Module module(Module parent) {
        ModuleBuilder mb = new ModuleBuilder();
        mb.setName(theModuleName);
        mb.setVersion(theModuleVer);
        mb.setRevision(theModuleRev);

        mb.setResources(S4AExampleModuleRes.res());
        mb.setComponents(null);
        mb.depend(parent);

        return mb.create();
    }
}
