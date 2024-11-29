package com.bitwormhole.starter4android.example;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.bitwormhole.starter4a.StarterActivity;

public class HomeMenuActivity extends StarterActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_menu);

        this.setupButton(R.id.button_surface_ui_demo, SurfaceDemoActivity.class);
        this.setupButton(R.id.button_try_api, TryApiActivity.class);
        this.setupButton(R.id.button_try_settings, TrySettingsActivity.class);
        this.setupButton(R.id.button_try_throw, TryThrowErrorActivity.class);
    }

    private void setupButton(int id, View.OnClickListener l) {
        findViewById(id).setOnClickListener(l);
    }

    private void setupButton(int id, Class<?> targetActivityClass) {
        setupButton(id, (v) -> {
            Intent i = new Intent(this, targetActivityClass);
            this.startActivity(i);
        });
    }
}
