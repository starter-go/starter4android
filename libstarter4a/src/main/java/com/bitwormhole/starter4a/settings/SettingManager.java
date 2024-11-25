package com.bitwormhole.starter4a.settings;

import com.bitwormhole.starter4a.DataScope;

public interface SettingManager {

    <T extends Setting> T load(Class<T> t, DataScope scope);

    <T extends Setting> T load(Class<T> t);

    boolean exists(Class<? extends Setting> t);

    boolean exists(Class<? extends Setting> t, DataScope s);

    void store(Setting s, DataScope scope);

}
