package com.bitwormhole.starter4android.example;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.bitwormhole.starter4a.Errors;
import com.bitwormhole.starter4a.StarterActivity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TryThrowErrorActivity extends StarterActivity {

    final static Logger logger = LoggerFactory.getLogger(TryThrowErrorActivity.class);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_try_throw_errors);

        setupButton(R.id.button_handle_error_all, this::onClickHandleErrorAll);
        setupButton(R.id.button_handle_error_alert, this::onClickHandleErrorAlert);
        setupButton(R.id.button_handle_error_log, this::onClickHandleErrorLog);
        setupButton(R.id.button_handle_error_toast, this::onClickHandleErrorToast);
        setupButton(R.id.button_bg_throw, this::onClickTryThrowBg);
        setupButton(R.id.button_fg_throw, this::onClickTryThrowFg);
    }


    private void setupButton(int id, View.OnClickListener l) {
        findViewById(id).setOnClickListener(l);
    }

    private void onClickHandleErrorAll(View view) {
        Exception err = new RuntimeException(this + ".onClickHandleErrorAll");
        int flags = Errors.FLAG_ALERT | Errors.FLAG_LOG | Errors.FLAG_TOAST;
        Errors.handleError(this, flags, err);
    }

    private void onClickHandleErrorAlert(View view) {
        Exception err = new RuntimeException(this + ".onClickHandleErrorAlert");
        int flags = Errors.FLAG_ALERT;
        Errors.handleError(this, flags, err);
    }

    private void onClickHandleErrorLog(View view) {
        Exception err = new RuntimeException(this + ".onClickHandleErrorLog");
        int flags = Errors.FLAG_LOG;
        Errors.handleError(this, flags, err);
    }

    private void onClickHandleErrorToast(View view) {
        Exception err = new RuntimeException(this + ".onClickHandleErrorToast");
        int flags = Errors.FLAG_TOAST;
        Errors.handleError(this, flags, err);
    }

    private void onClickTryThrowBg(View view) {
        Thread th = new Thread(() -> {
            throw new RuntimeException(this + ".onClickTryThrowBg");
        });
        th.start();
    }

    private void onClickTryThrowFg(View view) {
        throw new RuntimeException(this + ".onClickTryThrowFg");
    }
}
