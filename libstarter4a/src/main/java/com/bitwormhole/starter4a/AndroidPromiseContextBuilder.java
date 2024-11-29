package com.bitwormhole.starter4a;

import android.app.Activity;

import com.bitwormhole.starter4j.application.tasks.PromiseContext;

public class AndroidPromiseContextBuilder {

    private Activity activity;

    public AndroidPromiseContextBuilder() {
    }


    public PromiseContext create() {
        PromiseContext pc = new PromiseContext();
        pc.setBackground(new DefaultPromiseBackground(activity));
        pc.setForeground(new DefaultPromiseForeground(activity));
        return pc;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }
}
