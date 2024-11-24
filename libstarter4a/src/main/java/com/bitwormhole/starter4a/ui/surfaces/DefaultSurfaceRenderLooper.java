package com.bitwormhole.starter4a.ui.surfaces;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

import com.bitwormhole.starter4a.ui.boxes.B2LayoutContext;
import com.bitwormhole.starter4a.ui.boxes.B2LayoutThis;
import com.bitwormhole.starter4a.ui.boxes.B2RenderContext;
import com.bitwormhole.starter4a.ui.boxes.B2RenderThis;
import com.bitwormhole.starter4a.ui.boxes.B2Size;
import com.bitwormhole.starter4a.ui.boxes.B2View;
import com.bitwormhole.starter4j.base.Time;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultSurfaceRenderLooper implements SurfaceLooper {

    final static Logger logger = LoggerFactory.getLogger(DefaultSurfaceRenderLooper.class);

    private final SurfaceContext sc;
    private int layoutRevision;


    public DefaultSurfaceRenderLooper(SurfaceContext _sc) {
        this.sc = _sc;
    }

    @Override
    public void run() {
        try {
            logMyself("run-begin");
            B2LayoutContext lc = new B2LayoutContext();
            B2RenderContext rc = new B2RenderContext();
            lc.depthLimit = 32;
            rc.depthLimit = 32;
            while (isAlive()) {
                if (isWantLayout()) {
                    this.doLayout(lc);
                }
                if (isActive()) {
                    this.doPaint(rc);
                } else {
                    Time.sleep(100);
                }
            }
        } finally {
            logMyself("run-end");
        }
    }

    @Override
    public void start() {
        Thread th = new Thread(this);
        th.start();
    }

    ////////////////////////////////////////////////////////////////////////
    //private

    private void doLayout(B2LayoutContext lc) {

        logMyself("doLayout");

        this.layoutRevision = sc.getLayoutRevision();
        B2View v2 = sc.getView2();
        if (v2 == null) {
            return;
        }
        B2LayoutThis th = new B2LayoutThis(lc, v2);
        v2.x = 0;
        v2.y = 0;
        v2.width = sc.getWidth();
        v2.height = sc.getHeight();
        v2.updateLayout(th);
    }

    private void doPaint(B2RenderContext rc) {
        SurfaceHolder holder = sc.getHolder();
        B2View v2 = sc.getView2();
        if (holder == null || v2 == null) {
            return;
        }
        Canvas can = holder.lockCanvas();
        try {
            rc.canvas = can;
            B2RenderThis th = new B2RenderThis(rc, v2);
            v2.paint(th);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            holder.unlockCanvasAndPost(can);
        }
    }

    private boolean isWantLayout() {
        this.checkView2Size();
        return this.layoutRevision != sc.getLayoutRevision();
    }

    private void checkView2Size() {
        B2View v2 = sc.getView2();
        if (v2 == null) {
            return;
        }
        int w = sc.getWidth();
        int h = sc.getHeight();
        if (B2Size.equal(w, v2.width) && B2Size.equal(h, v2.height)) {
            return;
        }
        v2.height = h;
        v2.width = w;
        this.layoutRevision = -1;
    }


    private boolean isAlive() {
        final Runnable r1 = this;
        final Runnable r2 = sc.getLooper();
        if (r2 == null) {
            return false;
        }
        return r1.equals(r2);
    }

    private boolean isActive() {
        return sc.isFinallyActive();
    }

    private void logMyself(String msg) {
        String msg2 = this.getClass().getSimpleName() + ": " + msg;
        logger.info(msg2);
    }
}
