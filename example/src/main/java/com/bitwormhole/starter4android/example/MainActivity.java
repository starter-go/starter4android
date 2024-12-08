package com.bitwormhole.starter4android.example;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.bitwormhole.starter4a.Errors;
import com.bitwormhole.starter4a.StarterActivity;
import com.bitwormhole.starter4a.Startup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainActivity extends StarterActivity {

    final static Logger logger = LoggerFactory.getLogger(MainActivity.class);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Startup.start(this).Then((res) -> {
            this.startHomeActivity();
            return null;
        }).Catch((res) -> {
            Errors.handleError(this, Errors.FLAG_ALERT, res.getError());
            return null;
        }).Finally((res) -> {
            Log.d("Log.d", "a debug msg from android");
            logger.debug("a debug msg from org.slf4j.Logger");
            logger.warn("a warn msg from org.slf4j.Logger");
            return null;
        });
    }

    private void startHomeActivity() {
        startActivity(new Intent(this, HomeMenuActivity.class));
    }
}
