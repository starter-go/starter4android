package com.bitwormhole.starter4a;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

public final class AppCrashHandler {

    private AppCrashHandler() {
    }

    private static void setupMainThreadErrorHandler(Application app) {
        Looper lp = Looper.getMainLooper();
        Handler h = new Handler(lp);
        h.post(() -> {
            for (; ; ) {
                try {
                    Looper.loop();
                } catch (Throwable e) {
                    Errors.handleError(app, e);
                }
            }
        });
    }

    private static void setupWorkerThreadErrorHandler(Application app) {

        final Handler h = new Handler();

        // Thread.UncaughtExceptionHandler older = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler((thd, err) -> {
            h.post(() -> {
                Errors.handleError(app, err);
            });
        });
    }

    public static void setup(Application app) {
        setupMainThreadErrorHandler(app);
        setupWorkerThreadErrorHandler(app);
    }
}
