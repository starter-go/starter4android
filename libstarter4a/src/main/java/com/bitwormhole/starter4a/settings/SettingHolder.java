package com.bitwormhole.starter4a.settings;

import com.bitwormhole.starter4a.DataScope;
import com.bitwormhole.starter4a.contexts.Current;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SettingHolder {

    final static Logger logger = LoggerFactory.getLogger(SettingHolder.class);

    private Setting setting;
    private Class<? extends Setting> type;
    private DataScope scope;
    private Current current;
    private SettingManager manager;
    private Throwable error;

    public SettingHolder() {
    }

    public SettingHolder(SettingHolder sh) {
        if (sh == null) {
            return;
        }
        this.current = sh.current;
        this.error = sh.error;
        this.manager = sh.manager;
        this.scope = sh.scope;
        this.setting = sh.setting;
        this.type = sh.type;
    }

    public Setting getSetting() {
        return setting;
    }

    public void setSetting(Setting setting) {
        this.setting = setting;
    }

    public Class<? extends Setting> getType() {
        return type;
    }

    public void setType(Class<? extends Setting> type) {
        this.type = type;
    }

    public DataScope getScope() {
        return scope;
    }

    public void setScope(DataScope scope) {
        this.scope = scope;
    }

    public Current getCurrent() {
        return current;
    }

    public void setCurrent(Current current) {
        this.current = current;
    }

    public SettingManager getManager() {
        return manager;
    }

    public void setManager(SettingManager manager) {
        this.manager = manager;
    }

    public Throwable getError() {
        return error;
    }

    public void setError(Throwable error) {
        this.error = error;
    }


    /** @noinspection unchecked*/
    public <T extends Setting> T fetch(Class<T> t, T def) {
        this.type = t;
        this.setting = null;
        if (this.fetch()) {
            return (T) this.setting;
        }
        return def;
    }

    public boolean fetch() {
        try {
            this.manager.load(this);
            return true;
        } catch (Exception e) {
            this.error = e;
            logger.warn("SettingHolder.fetch:", e);
            return false;
        }
    }

    public boolean commit() {
        try {
            this.manager.save(this);
            return true;
        } catch (Exception e) {
            this.error = e;
            logger.warn("SettingHolder.commit:", e);
            return false;
        }
    }
}
