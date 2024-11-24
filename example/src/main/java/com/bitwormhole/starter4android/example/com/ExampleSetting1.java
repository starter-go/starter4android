package com.bitwormhole.starter4android.example.com;

import com.bitwormhole.starter4a.DataScope;
import com.bitwormhole.starter4a.settings.Setting;
import com.bitwormhole.starter4a.settings.SettingRegistration;
import com.bitwormhole.starter4a.settings.SettingRegistrationListBuilder;
import com.bitwormhole.starter4a.settings.SettingRegistry;

public class ExampleSetting1 implements Setting, SettingRegistry {

    @Override
    public SettingRegistration[] listSettingRegistrations() {
        SettingRegistrationListBuilder b = new SettingRegistrationListBuilder();
        b.setType(ExampleSetting1.class);
        b.add(DataScope.DOCUMENT).add(DataScope.USER).add(DataScope.APP);
        return b.create();
    }
}
