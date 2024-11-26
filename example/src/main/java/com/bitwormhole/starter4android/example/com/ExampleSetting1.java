package com.bitwormhole.starter4android.example.com;

import com.bitwormhole.starter4a.DataScope;
import com.bitwormhole.starter4a.settings.Setting;
import com.bitwormhole.starter4a.settings.SettingBase;
import com.bitwormhole.starter4a.settings.SettingRegistration;
import com.bitwormhole.starter4a.settings.SettingRegistrationListBuilder;
import com.bitwormhole.starter4a.settings.SettingRegistry;

public class ExampleSetting1 extends SettingBase implements Setting, SettingRegistry {


    private String name;
    private int value;
    private boolean enabled;

    public ExampleSetting1() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public SettingRegistration[] listSettingRegistrations() {
        SettingRegistrationListBuilder b = new SettingRegistrationListBuilder();
        b.setType(ExampleSetting1.class);
        b.add(DataScope.DOCUMENT).add(DataScope.USER).add(DataScope.APP);
        return b.create();
    }
}
