package com.bitwormhole.starter4android.example;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.bitwormhole.starter4a.StarterActivity;

public class StartupActivity extends StarterActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_startup);
    }
}
