package com.bitwormhole.starter4a;

import android.os.Handler;

import java.util.concurrent.Executor;

public class DefaultPromiseForeground implements Executor {

    private final Handler handler;

    public DefaultPromiseForeground() {
        this.handler = new Handler();
    }

    public DefaultPromiseForeground(Handler h) {
        if (h == null) {
            h = new Handler();
        }
        this.handler = h;
    }


    @Override
    public void execute(Runnable runnable) {
        this.handler.post(runnable);
    }
}
