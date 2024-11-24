package com.bitwormhole.starter4a.contexts;

import com.bitwormhole.starter4j.application.ApplicationContext;
import com.bitwormhole.starter4j.application.Life;
import com.bitwormhole.starter4j.application.LifeCycle;
import com.bitwormhole.starter4j.base.StarterException;

public class AppCurrentHolderCom implements CurrentHolder, LifeCycle {

    private CurrentHolder inner;
    private ApplicationContext ac;

    public AppCurrentHolderCom() {
    }

    public ApplicationContext getAc() {
        return ac;
    }

    public void setAc(ApplicationContext ac) {
        if (ac == null) {
            return;
        }
        this.inner = AppCurrentHolder.getInstance(ac.getAttributes());
        this.ac = ac;
    }


    @Override
    public Life life() {
        Life l = new Life();
        l.onCreate = this::onCreate;
        l.onStartPost = this::onStartPost;
        return l;
    }

    private void onCreate() {
        if (ac == null) {
            throw new StarterException("AppCurrentHolderCom: ac is null");
        }
        if (inner == null) {
            throw new StarterException("AppCurrentHolderCom: inner is null");
        }
        App app = inner.getCurrent().getApp();
        app.setContext(ac);
    }

    private void onStartPost() {
        // startup done
    }

    @Override
    public Current getCurrent() {
        CurrentHolder i = inner;
        if (i == null) {
            return null;
        }
        return i.getCurrent();
    }
}
