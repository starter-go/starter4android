package com.bitwormhole.starter4a.contexts;


import com.bitwormhole.starter4j.base.SafeMode;
import com.bitwormhole.starter4j.application.Module;

public final class Framework extends ScopeContext {

    private Module mainModule;
    private MainModuleProvider mainModuleProvider;
    private Customizer customizer;

    public Framework(SafeMode _mode) {
        super(null, _mode);
    }

    public Module getMainModule() {
        return mainModule;
    }

    public void setMainModule(Module mainModule) {
        this.mainModule = mainModule;
    }

    public MainModuleProvider getMainModuleProvider() {
        return mainModuleProvider;
    }

    public void setMainModuleProvider(MainModuleProvider mainModuleProvider) {
        this.mainModuleProvider = mainModuleProvider;
    }

    public Customizer getCustomizer() {
        return customizer;
    }

    public void setCustomizer(Customizer customizer) {
        this.customizer = customizer;
    }
}
