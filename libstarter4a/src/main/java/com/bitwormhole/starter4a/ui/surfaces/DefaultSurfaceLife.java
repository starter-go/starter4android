package com.bitwormhole.starter4a.ui.surfaces;

import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.Nullable;

import com.bitwormhole.starter4a.FrontLife;

public class DefaultSurfaceLife implements FrontLife {

    private final SurfaceContext context;
    private SurfaceContext surfaceContextToInit;

    public DefaultSurfaceLife() {
        this.context = new SurfaceContext();
    }

    public DefaultSurfaceLife(SurfaceContext sc) {
        this.context = sc;
    }


    public SurfaceContext getContext() {
        return context;
    }

    private void init(SurfaceContext ctx) {

        if (ctx == null) {
            return;
        }

        SurfaceView view = ctx.getView();
        if (view == null) {
            throw new RuntimeException("InstrumentContext.view == null");
        }

        SurfaceHolder sh = view.getHolder();
        DefaultSurfaceHolderCallback callback = new DefaultSurfaceHolderCallback(ctx);
        DefaultSurfaceOnTouchListener listener = new DefaultSurfaceOnTouchListener(ctx);

        ctx.setCallback(callback);
        ctx.setOnTouchListener(listener);
        view.setOnTouchListener(listener);
        // ctx.setHolder(sh); // set by callback

        sh.addCallback(callback);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        this.surfaceContextToInit = this.context;
    }

    @Override
    public void onStart() {
        SurfaceContext sc = this.surfaceContextToInit;
        this.surfaceContextToInit = null;
        this.init(sc);
    }

    @Override
    public void onRestart() {

    }

    @Override
    public void onPause() {
        this.context.setLooper(null);
        this.context.setActive(false);
    }

    @Override
    public void onResume() {
        this.context.setActive(true);
    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }
}
