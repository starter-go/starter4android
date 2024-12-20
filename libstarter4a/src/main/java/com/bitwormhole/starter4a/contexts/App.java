package com.bitwormhole.starter4a.contexts;

import com.bitwormhole.starter4a.APIManager;
import com.bitwormhole.starter4a.settings.SettingManager;
import com.bitwormhole.starter4j.application.ApplicationContext;
import com.bitwormhole.starter4j.base.SafeMode;

import java.nio.file.Path;

import com.bitwormhole.starter4a.DataScope;

public class App extends ScopeContext {

    private final Framework framework;
    private ApplicationContext context;

    private SettingManager settings;
    private APIManager apis;


    private boolean started;
    private boolean starting;
    private boolean stopped;
    private boolean stopping;
    private long createdAt;
    private long stoppedAt;
    private long startedAt;


    public App(Framework _parent, SafeMode _mode) {
        super(_parent, DataScope.APP, _mode);
        this.framework = _parent;
    }

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public boolean isStarting() {
        return starting;
    }

    public void setStarting(boolean starting) {
        this.starting = starting;
    }

    public boolean isStopped() {
        return stopped;
    }

    public void setStopped(boolean stopped) {
        this.stopped = stopped;
    }

    public boolean isStopping() {
        return stopping;
    }

    public void setStopping(boolean stopping) {
        this.stopping = stopping;
    }

    public long getStoppedAt() {
        return stoppedAt;
    }

    public void setStoppedAt(long stoppedAt) {
        this.stoppedAt = stoppedAt;
    }

    public long getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(long startedAt) {
        this.startedAt = startedAt;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }


    public ApplicationContext getContext() {
        return context;
    }

    public void setContext(ApplicationContext context) {
        this.context = context;
    }


    public APIManager getApis() {
        return apis;
    }

    public void setApis(APIManager apis) {
        this.apis = apis;
    }

    public SettingManager getSettings() {
        return settings;
    }

    public void setSettings(SettingManager settings) {
        this.settings = settings;
    }

    public Framework getFramework() {
        return framework;
    }
}
