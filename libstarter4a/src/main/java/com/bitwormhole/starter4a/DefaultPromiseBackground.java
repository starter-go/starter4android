package com.bitwormhole.starter4a;

import android.content.Context;

import com.bitwormhole.starter4j.application.tasks.BackgroundExecutor;


public class DefaultPromiseBackground implements BackgroundExecutor {

    private final Context context;

    public DefaultPromiseBackground(Context ctx) {
        this.context = ctx;
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
                int flag = Errors.FLAG_LOG;
                Errors.handleError(context, flag, e);
            }
        }
    }

    @Override
    public void execute(Runnable runnable) {
        runnable = new MyRunnableWrapper(runnable);
        Thread th = new Thread(runnable);
        th.start();
    }
}
