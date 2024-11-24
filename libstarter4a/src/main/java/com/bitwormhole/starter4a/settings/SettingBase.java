package com.bitwormhole.starter4a.settings;

import com.bitwormhole.starter4a.DataScope;

public class SettingBase implements Setting {

    private DataScope scope;

    public SettingBase() {
    }

    public SettingBase(DataScope s) {
        this.scope = s;
    }

    public DataScope getScope() {
        return scope;
    }

    public void setScope(DataScope scope) {
        this.scope = scope;
    }
}
