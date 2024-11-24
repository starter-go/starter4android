package com.bitwormhole.starter4android.example;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.bitwormhole.starter4a.Errors;
import com.bitwormhole.starter4a.StarterActivity;
import com.bitwormhole.starter4a.Startup;
import com.bitwormhole.starter4a.contexts.Current;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExampleActivity extends StarterActivity {

    final static Logger logger = LoggerFactory.getLogger(ExampleActivity.class);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_example);
    }

}
