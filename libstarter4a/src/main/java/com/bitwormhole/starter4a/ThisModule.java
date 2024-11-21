package com.bitwormhole.starter4a;

import com.bitwormhole.starter4a.res.m.LibStarter4aEmbeddedRes;
import com.bitwormhole.starter4j.Starter;
import com.bitwormhole.starter4j.application.Module;
import com.bitwormhole.starter4j.application.ModuleBuilder;

import com.bitwormhole.starter4a.config.ConfigThisModuleComponents;
import com.bitwormhole.starter4j.stopper.Stopper;

final class ThisModule {

    private static final String theModuleName = StarterApplication.class.getName();
    private static final String theModuleVersion = "0.0.0";
    private static final int theModuleRev = 0;

    private static Module theModule;

    private static Module createModule() {
        ModuleBuilder mb = new ModuleBuilder();
        mb.setName(theModuleName);
        mb.setVersion(theModuleVersion);
        mb.setRevision(theModuleRev);

        mb.setResources(LibStarter4aEmbeddedRes.res());
        mb.setComponents(ConfigThisModuleComponents.func());

        mb.depend(Starter.module());
        // mb.depend(Stopper.module());

        return mb.create();
    }

    public static Module module() {
        Module m = theModule;
        if (m == null) {
            m = createModule();
            theModule = m;
        }
        return m;
    }
}