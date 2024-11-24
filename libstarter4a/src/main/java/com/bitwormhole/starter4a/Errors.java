package com.bitwormhole.starter4a;

import android.app.AlertDialog;
import android.content.Context;
import android.widget.Toast;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Errors {

    static final Logger logger = LoggerFactory.getLogger(Errors.class);

    public static final int FLAG_ALERT = 0x01;
    public static final int FLAG_TOAST = 0x02;
    public static final int FLAG_LOG = 0x04;

    private Errors() {
    }

    private static void innerHandleError(Context ctx, int flags, Throwable err) {

        if (err == null) {
            return;
        }

        if (ctx == null || flags == 0) {
            flags = FLAG_LOG;
        }

        // for flags

        if ((flags & FLAG_TOAST) != 0) {
            String msg = "error:" + err.getMessage();
            Toast.makeText(ctx, msg, Toast.LENGTH_LONG).show();
        }
        if ((flags & FLAG_ALERT) != 0) {
            showErrorAlertDialog(ctx, err);
        }
        if ((flags & FLAG_LOG) != 0) {
            logger.error(err.getMessage());
        }
    }

    private static void showErrorAlertDialog(Context ctx, Throwable err) {
        AlertDialog.Builder b = new AlertDialog.Builder(ctx);
        b.setTitle("Error");
        b.setMessage(err.getMessage());
        b.setNegativeButton(R.string.close, (p1, p2) -> {
        });
        b.show();
    }

    public static void handleError(Context ctx, int flags, Throwable err) {
        innerHandleError(ctx, flags, err);
    }

    public static void handleError(Context ctx, Throwable err) {
        innerHandleError(ctx, FLAG_LOG | FLAG_TOAST, err);
    }

    public static void handleError(Throwable err) {
        innerHandleError(null, FLAG_LOG, err);
    }
}
