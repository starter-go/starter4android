package com.bitwormhole.starter4a;

import com.bitwormhole.starter4a.contexts.App;
import com.bitwormhole.starter4a.contexts.Current;
import com.bitwormhole.starter4a.contexts.CurrentHolder;
import com.bitwormhole.starter4j.application.ApplicationContext;
import com.bitwormhole.starter4j.application.Life;
import com.bitwormhole.starter4j.application.LifeCycle;
import com.bitwormhole.starter4j.base.Time;

public final class BackLifeManager implements LifeCycle {

    private CurrentHolder currentHolder;
    private ApplicationContext context;

    public BackLifeManager() {
    }

    @Override
    public Life life() {
        Life l = new Life();
        l.onCreate = this::create;
        l.onStartPre = this::start_pre;
        l.onStart = this::start;
        l.onLoop = this::loop;
        l.onStopPre = this::stop_pre;
        l.onStop = this::stop;
        l.onDestroy = this::destroy;
        return l;
    }

    private void create() {
        final long now = System.currentTimeMillis();
        final Current current = currentHolder.getCurrent();
        final App app = current.getApp();
        app.setCreatedAt(now);
    }

    private void start_pre() {
        final Current current = currentHolder.getCurrent();
        final App app = current.getApp();
        app.setStarting(true);
        app.setContext(this.context);
    }

    private void start() {
    }

    private void loop() {
        final long now = System.currentTimeMillis();
        final Current current = currentHolder.getCurrent();
        final App app = current.getApp();
        app.setStartedAt(now);
        app.setStarted(true);

        this.waitForStopping();
    }

    private void stop_pre() {
        final Current current = currentHolder.getCurrent();
        final App app = current.getApp();
        app.setStopping(true);
    }

    private void stop() {
    }

    private void destroy() {
        long now = System.currentTimeMillis();
        Current current = currentHolder.getCurrent();
        App app = current.getApp();
        app.setStoppedAt(now);
        app.setStopped(true);
    }

    private void waitForStopping() {
        final int step = 3000;
        final Current current = currentHolder.getCurrent();
        final App app = current.getApp();
        for (; ; ) {
            if (app.isStopping()) {
                break;
            }
            Time.sleep(step);
        }
    }

    public ApplicationContext getContext() {
        return context;
    }

    public void setContext(ApplicationContext context) {
        this.context = context;
    }

    public CurrentHolder getCurrentHolder() {
        return currentHolder;
    }

    public void setCurrentHolder(CurrentHolder currentHolder) {
        this.currentHolder = currentHolder;
    }
}
