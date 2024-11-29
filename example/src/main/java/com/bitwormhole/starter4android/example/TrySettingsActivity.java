package com.bitwormhole.starter4android.example;

import android.content.ComponentName;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;

import androidx.annotation.Nullable;

import com.bitwormhole.starter4a.StarterActivity;
import com.bitwormhole.starter4a.StarterBinder;
import com.bitwormhole.starter4a.StarterBinderObserver;
import com.bitwormhole.starter4a.StarterServiceClient;
import com.bitwormhole.starter4a.settings.SettingHolder;
import com.bitwormhole.starter4a.settings.SettingManager;
import com.bitwormhole.starter4android.example.com.ExampleSetting1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TrySettingsActivity extends StarterActivity {

    final static Logger logger = LoggerFactory.getLogger(TrySettingsActivity.class);

    private StarterBinder mBinder;
    private ExampleSetting1 mSetting;


    private CheckBox mInputEnabled;
    private EditText mInputName;
    private SeekBar mInputValue;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_try_settings);

        mInputEnabled = findViewById(R.id.input_enabled);
        mInputName = findViewById(R.id.input_name);
        mInputValue = findViewById(R.id.input_value);

        StarterBinderObserver.observe(this).setOnBegin((binderHolder) -> {
            StarterBinder b = binderHolder.getBinder();
            mBinder = b;
            TrySettingsActivity.this.loadSettings(b);
        });
    }


    protected void loadSettings(StarterBinder b) {
        if (b == null) {
            return;
        }
        SettingManager settings = b.getSettingManager();
        SettingHolder sh = new SettingHolder();
        ExampleSetting1 setting1 = new ExampleSetting1();
        sh.setManager(settings);
        setting1 = sh.fetch(ExampleSetting1.class, setting1);
        this.mSetting = setting1;
        this.refresh();
    }

    protected void storeSettings(StarterBinder b) {
        if (b == null) {
            return;
        }
        this.ui2model();
        SettingManager settings = b.getSettingManager();
        SettingHolder sh = new SettingHolder();
        ExampleSetting1 setting1 = this.mSetting;
        if (setting1 == null) {
            setting1 = new ExampleSetting1();
        }
        sh.setManager(settings);
        sh.setSetting(setting1);
        sh.setType(ExampleSetting1.class);
        sh.commit();
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.storeSettings(this.mBinder);
    }


    private void ui2model() {
        ExampleSetting1 src = new ExampleSetting1();
        src.setEnabled(mInputEnabled.isChecked());
        src.setName(mInputName.getText().toString());
        src.setValue(mInputValue.getProgress());
        this.mSetting = src;
    }

    private void refresh() {
        ExampleSetting1 src = this.mSetting;
        if (src == null) {
            return;
        }
        mInputEnabled.setChecked(src.isEnabled());
        mInputName.setText(src.getName());
        mInputValue.setProgress(src.getValue());
    }
}
