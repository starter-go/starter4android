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
    private final Map<String, MyItem> table;

    public SettingManagerImpl() {
    }

    public ApplicationContext getContext() {
        return context;
    }

    public void setContext(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public <T extends Setting> T load(Class<T> t, DataScope scope) {
        return null;
    }

    @Override
    public <T extends Setting> T load(Class<T> t) {
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
    public void store(Setting s, DataScope scope) {

    }

    private MyItem findItem(Class<? extends Setting> t, boolean create) {
        String key = t.getName();
        MyItem item = this.table.get(key);
        if (item==null &&   create) {

        }
        return item  ;
    }

    private static class MySaver {
    }

    private static class MyLoader {
    }

    private static class MyItem {
        Class<? extends Setting> type;

        boolean hasScopeDocument;
        boolean hasScopeProject;
        boolean hasScopeUser;
        boolean hasScopeApp;
        boolean hasScopeFramework;

    }

    private static class MyLocater {
        Class<? extends Setting> type;
        Scope scope;
        Current current;

        Path locate() {
        }
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
