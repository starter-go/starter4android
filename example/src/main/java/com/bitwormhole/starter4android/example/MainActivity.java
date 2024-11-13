package com.bitwormhole.starter4android.example;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.bitwormhole.starter4a.StarterActivity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainActivity extends StarterActivity {

    final static Logger logger = LoggerFactory.getLogger(MainActivity.class);


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);

        Log.d("Log.d", "a debug msg from android");
        logger.debug("a debug msg from org.slf4j.Logger");
        logger.warn("a warn msg from org.slf4j.Logger");

        startActivity(new Intent(this, StartupActivity.class));
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
