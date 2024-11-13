package com.bitwormhole.starter4a.contexts;

import com.bitwormhole.starter4j.application.ApplicationContext;
import com.bitwormhole.starter4j.base.SafeMode;

public class App extends ScopeContext {

    private final Framework framework;
    private ApplicationContext ac;

    public App(Framework _parent, SafeMode _mode) {
        super(_parent, _mode);
        this.framework = _parent;
    }

    public ApplicationContext getAC() {
        return ac;
    }

    public void setAC(ApplicationContext ac) {
        this.ac = ac;
    }

    public Framework getFramework() {
        return framework;
    }
}
