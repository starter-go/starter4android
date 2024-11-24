package com.bitwormhole.starter4a.ui.surfaces;

import android.view.SurfaceHolder;
import android.view.View;

import com.bitwormhole.starter4a.FrontLife;

public class SurfaceContextBuilder {

    private SurfaceContext surfaceContext;

    public SurfaceContextBuilder() {
        this.surfaceContext = new SurfaceContext();
    }

    public SurfaceContextBuilder(SurfaceContext sc) {
        this.surfaceContext = sc;
    }


    public SurfaceContext create() {
        SurfaceContext sc = this.surfaceContext;
        if (sc == null) {
            sc = new SurfaceContext();
        }

        SurfaceHolder.Callback callback = new DefaultSurfaceHolderCallback(sc);
        FrontLife life = new DefaultSurfaceLife(sc);
        SurfaceLooper looper = new DefaultSurfaceRenderLooper(sc);
        View.OnTouchListener on_touch = new DefaultSurfaceOnTouchListener(sc);

        sc.setHeight(0);
        sc.setWidth(0);
        sc.setActive(false);
        sc.setLayoutRevision(0);

        sc.setCallback(callback);
        sc.setOnTouchListener(on_touch);
        sc.setLooper(looper);
        sc.setHolder(null);
        sc.setLife(life);

        return sc;
    }

    public SurfaceContext getSurfaceContext() {
        return surfaceContext;
    }

    public void setSurfaceContext(SurfaceContext surfaceContext) {
        this.surfaceContext = surfaceContext;
    }
}
