package com.bitwormhole.starter4a.settings;


import com.bitwormhole.starter4a.DataScope;

import java.util.ArrayList;
import java.util.List;

public final class SettingRegistrationListBuilder {

    private Class<? extends Setting> type;
    private DataScope scope;
    private List<SettingRegistration> list;

    public SettingRegistrationListBuilder() {
        this.scope = DataScope.APP;
        this.list = new ArrayList<>();
    }

    public SettingRegistrationListBuilder add(DataScope _scope) {
        return this.add(null, _scope);
    }

    public SettingRegistrationListBuilder add(Class<? extends Setting> _type) {
        return this.add(_type, null);
    }

    public SettingRegistrationListBuilder add(Class<? extends Setting> t,DataScope s) {
        if (s == null) {
            s = this.scope;
        }
        if (t == null) {
            t = this.type;
        }
        SettingRegistration item = new SettingRegistration();
        item.setScope(s);
        item.setType(t);
        this.list.add(item);
        return this;
    }

    public SettingRegistration[] create() {
        return list.toArray(new SettingRegistration[0]);
    }

    public Class<? extends Setting> getType() {
        return type;
    }

    public SettingRegistrationListBuilder setType(Class<? extends Setting> t) {
        if (t != null) {
            this.type = t;
        }
        return this;
    }

    public DataScope getScope() {
        return scope;
    }

    public SettingRegistrationListBuilder setScope(DataScope s) {
        if (s != null) {
            this.scope = s;
        }
        return this;
    }

    public List<SettingRegistration> getList() {
        return list;
    }

    public SettingRegistrationListBuilder setList(List<SettingRegistration> l) {
        if (l != null) {
            this.list = l;
        }
        return this;
    }
}
