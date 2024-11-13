package com.bitwormhole.starter4a;

import android.content.Context;
import android.os.Handler;

import java.util.concurrent.Executor;

public class PromiseContext {

    private Context context;
    private Executor foreground;
    private Executor background;

    public PromiseContext() {
    }

    public void set(PromiseContext src) {
        if (src == null) {
            return;
        }
        this.context = src.context;
        this.background = src.background;
        this.foreground = src.foreground;
    }

    public Executor getForeground() {
        return foreground;
    }

    public void setForeground(Executor foreground) {
        this.foreground = foreground;
    }

    public Executor getBackground() {
        return background;
    }

    public void setBackground(Executor background) {
        this.background = background;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
