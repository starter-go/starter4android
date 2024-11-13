package com.bitwormhole.starter4a.contexts;


import com.bitwormhole.starter4j.base.SafeMode;
import com.bitwormhole.starter4j.application.Module;

public final class Framework extends ScopeContext {

    private Module main; // the main module

    public Framework(SafeMode _mode) {
        super(null, _mode);
    }

    public Module getMain() {
        return main;
    }

    public void setMain(Module main) {
        this.main = main;
    }
}
