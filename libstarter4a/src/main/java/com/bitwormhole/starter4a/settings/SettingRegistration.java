package com.bitwormhole.starter4a.settings;


import com.bitwormhole.starter4a.DataScope;

public final class SettingRegistration {

    private Class<? extends Setting> type;
    private DataScope scope;

    public SettingRegistration() {
    }

    public Class<? extends Setting> getType() {
        return type;
    }

    public void setType(Class<? extends Setting> type) {
        this.type = type;
    }

    public  DataScope getScope() {
        return scope;
    }


    public void setScope(DataScope scope) {
        this.scope = scope;
    }
}
