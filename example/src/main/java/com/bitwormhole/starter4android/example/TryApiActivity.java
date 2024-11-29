package com.bitwormhole.starter4android.example;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.bitwormhole.starter4a.Errors;
import com.bitwormhole.starter4a.StarterActivity;
import com.bitwormhole.starter4a.StarterBinder;
import com.bitwormhole.starter4a.StarterBinderObserver;
import com.bitwormhole.starter4android.example.com.ExampleLocalApi1;
import com.bitwormhole.starter4android.example.com.ExampleRemoteApi1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TryApiActivity extends StarterActivity {

    final static Logger logger = LoggerFactory.getLogger(TryApiActivity.class);

    private StarterBinder mBinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_try_api);

        findViewById(R.id.button_try_remote_api_err).setOnClickListener(this::handleClickRemoteApiError);
        findViewById(R.id.button_try_remote_api_ok).setOnClickListener(this::handleClickRemoteApiOk);
        findViewById(R.id.button_try_local_api_err).setOnClickListener(this::handleClickLocalApiError);
        findViewById(R.id.button_try_local_api_ok).setOnClickListener(this::handleClickLocalApiOk);

        StarterBinderObserver.observe(this).setOnBegin((binderHolder) -> {
            mBinder = binderHolder.getBinder();
        });
    }

    private StarterBinder getStarterBinder() {
        // return this . getFrontContext() .getClient().getBinder();
        return this.mBinder;
    }

    private void handleClickRemoteApiError(View view) {
        try {
            StarterBinder binder = getStarterBinder();
            ExampleRemoteApi1 api = binder.getRemoteAPI(ExampleRemoteApi1.class);
            final long now = System.currentTimeMillis();
            api.bad(now);
        } catch (Exception e) {
            int flags = Errors.FLAG_LOG | Errors.FLAG_ALERT;
            Errors.handleError(this, flags, e);
        }
    }

    private void handleClickRemoteApiOk(View view) {
        try {
            StarterBinder binder = getStarterBinder();
            ExampleRemoteApi1 api = binder.getRemoteAPI(ExampleRemoteApi1.class);
            String msg = api.hello("" + this);

            AlertDialog.Builder ab = new AlertDialog.Builder(this);
            ab.setTitle("OK");
            ab.setMessage(msg);
            ab.setNegativeButton("close", (x, y) -> {
            });
            ab.show();
        } catch (Exception e) {
            int flags = Errors.FLAG_LOG | Errors.FLAG_ALERT;
            Errors.handleError(this, flags, e);
        }
    }

    private void handleClickLocalApiError(View view) {
        try {
            StarterBinder binder = getStarterBinder();
            ExampleLocalApi1 api = binder.getLocalAPI(ExampleLocalApi1.class);
            final long now = System.currentTimeMillis();
            api.bad(now);
        } catch (Exception e) {
            int flags = Errors.FLAG_LOG | Errors.FLAG_ALERT;
            Errors.handleError(this, flags, e);
        }
    }

    private void handleClickLocalApiOk(View view) {
        try {
            StarterBinder binder = getStarterBinder();
            ExampleLocalApi1 api = binder.getLocalAPI(ExampleLocalApi1.class);
            String msg = api.hello("" + this);

            AlertDialog.Builder ab = new AlertDialog.Builder(this);
            ab.setTitle("OK");
            ab.setMessage(msg);
            ab.setNegativeButton("close", (x, y) -> {
            });
            ab.show();
        } catch (Exception e) {
            int flags = Errors.FLAG_LOG | Errors.FLAG_ALERT;
            Errors.handleError(this, flags, e);
        }
    }
}
