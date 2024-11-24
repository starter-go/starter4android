package com.bitwormhole.starter4a.ui.surfaces;

import android.view.SurfaceHolder;
import android.view.View;

import androidx.annotation.NonNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultSurfaceHolderCallback implements SurfaceHolder.Callback {

    final static Logger logger = LoggerFactory.getLogger(DefaultSurfaceHolderCallback.class);

    private final SurfaceContext sc;

    public DefaultSurfaceHolderCallback(SurfaceContext _sc) {
        this.sc = _sc;
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {

        View view = sc.getView();
        int w = view.getWidth();
        int h = view.getHeight();

        logMyself("surfaceCreated(w:" + w + ",h:" + h + ")");

        sc.setHolder(holder);
        sc.setWidth(w);
        sc.setHeight(h);
        sc.updateLayoutRevision(1);


        SurfaceLooper looper = sc.getLooper();
        if (looper == null) {
            looper = new DefaultSurfaceRenderLooper(sc);
            sc.setLooper(looper);
        }
        looper.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {
        logMyself("surfaceChanged(w:" + width + ",h:" + height + ")");
        sc.setHolder(holder);
        sc.setWidth(width);
        sc.setHeight(height);
        sc.updateLayoutRevision(1);
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
        logMyself("surfaceDestroyed");
        sc.setHolder(null);
        sc.setLooper(null);
    }

    private void logMyself(String msg) {
        String prefix = this.getClass().getSimpleName() + ": ";
        logger.info(prefix + msg);
    }
}
