package com.bitwormhole.starter4a.settings;

import com.bitwormhole.starter4a.DataScope;
import com.bitwormhole.starter4a.contexts.Current;
import com.bitwormhole.starter4a.contexts.CurrentHolder;
import com.bitwormhole.starter4a.contexts.ScopeContext;
import com.bitwormhole.starter4j.application.ApplicationContext;
import com.bitwormhole.starter4j.application.Life;
import com.bitwormhole.starter4j.application.LifeCycle;
import com.bitwormhole.starter4j.base.StarterException;
import com.google.gson.Gson;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SettingManagerFacade implements SettingManager, LifeCycle {

    final static Logger logger = LoggerFactory.getLogger(SettingManagerFacade.class);

    private final SettingManagerCore core;

    public SettingManagerFacade() {
        this.core = new SettingManagerCore();
    }

    public void init(ApplicationContext context, CurrentHolder holder) {
        core.setContext(context);
        core.setCurrentHolder(holder);
    }

    @Override
    public Life life() {
        return core.life();
    }

    private void prepare(SettingHolder h) {

        DataScope scope = h.getScope();
        Current current = h.getCurrent();
        Setting setting = h.getSetting();
        Class<? extends Setting> type = h.getType();

        if (scope == null) {
            scope = DataScope.AUTO;
        }

        if (current == null) {
            current = core.getCurrent(null);
        }

        if (setting == null && type == null) {
            throw new StarterException("both fields of SettingHolder: setting & type are null");
        } else if (type == null) {
            type = setting.getClass();
        }

        h.setError(null);
        h.setManager(this);
        h.setCurrent(current);
        h.setScope(scope);
        h.setType(type);
        h.setSetting(setting);
    }


    @Override
    public void load(SettingHolder h) throws IOException {
        if (h.getType() == null) {
            throw new NullPointerException("setting type is null");
        }
        this.prepare(h);
        core.load(h);
    }

    @Override
    public void save(SettingHolder h) throws IOException {
        if (h.getSetting() == null) {
            throw new NullPointerException("setting object is null");
        }
        this.prepare(h);
        core.store(h);
    }
}
