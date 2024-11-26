package com.bitwormhole.starter4a.settings;

import com.bitwormhole.starter4a.DataScope;
import com.bitwormhole.starter4a.contexts.Current;

import java.io.IOException;

public interface SettingManager {

    void load(SettingHolder h) throws IOException;

    void save(SettingHolder h) throws IOException;

}
