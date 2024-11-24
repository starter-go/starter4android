package com.bitwormhole.starter4a.settings;

import com.bitwormhole.starter4a.DataScope;

public final class SettingHolder<T extends Setting> {

    private T setting;
    private DataScope scope;

    public SettingHolder() {
    }

    public T getSetting() {
        return setting;
    }

    public void setSetting(T setting) {
        this.setting = setting;
    }

    public DataScope getScope() {
        return scope;
    }

    public void setScope(DataScope scope) {
        this.scope = scope;
    }

}
