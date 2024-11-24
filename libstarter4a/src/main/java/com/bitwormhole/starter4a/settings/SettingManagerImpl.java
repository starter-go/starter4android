package com.bitwormhole.starter4a.settings;

import com.bitwormhole.starter4a.DataScope;
import com.bitwormhole.starter4j.application.ApplicationContext;
import com.bitwormhole.starter4j.application.Life;
import com.bitwormhole.starter4j.application.LifeCycle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class SettingManagerImpl implements SettingManager, LifeCycle {

    final static Logger logger = LoggerFactory.getLogger(SettingManagerImpl.class);

    private ApplicationContext context;

    public SettingManagerImpl() {
    }

    public ApplicationContext getContext() {
        return context;
    }

    public void setContext(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public <T extends Setting> T get(Class<T> t, DataScope scope) {
        return null;
    }

    @Override
    public <T extends Setting> T get(Class<T> t) {
        return null;
    }

    @Override
    public boolean exists(Class<? extends Setting> t) {
        return false;
    }

    @Override
    public boolean exists(Class<? extends Setting> t, DataScope s) {
        return false;
    }

    @Override
    public void set(Setting s, DataScope scope) {

    }

    private void reload() {
        final String clazz = SettingRegistry.class.getName();
        final List<Object> list = this.context.selectComponents("." + clazz, null);
        for (Object item : list) {
            final SettingRegistry r1 = (SettingRegistry) item;
            SettingRegistration[] r2list = r1.listSettingRegistrations();
            for (SettingRegistration r2 : r2list) {
                logger.warn("todo: handle setting-reg for: {}", r2.getType());
            }
        }
    }

    @Override
    public Life life() {
        Life l = new Life();
        l.onCreate = this::reload;
        return l;
    }
}
