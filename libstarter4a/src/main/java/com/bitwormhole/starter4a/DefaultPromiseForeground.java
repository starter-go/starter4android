package com.bitwormhole.starter4a;

import android.app.Activity;

import com.bitwormhole.starter4j.application.tasks.ForegroundExecutor;

public class DefaultPromiseForeground implements ForegroundExecutor {

    private final Activity activity;

    public DefaultPromiseForeground(Activity a) {
        this.activity = a;
    }


    private class MyRunnableWrapper implements Runnable {

        final Runnable inner;

        MyRunnableWrapper(Runnable in) {
            this.inner = in;
        }

        @Override
        public void run() {
            try {
                this.inner.run();
            } catch (Exception e) {
                int flag = Errors.FLAG_LOG | Errors.FLAG_TOAST;
                Errors.handleError(activity, flag, e);
            }
        }
    }

    @Override
    public void execute(Runnable runnable) {
        runnable = new MyRunnableWrapper(runnable);
        this.activity.runOnUiThread(runnable);
    }
}
