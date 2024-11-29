package com.bitwormhole.starter4android.example;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.os.Bundle;
import android.view.SurfaceView;

import androidx.annotation.Nullable;

import com.bitwormhole.starter4a.FrontLifeManager;
import com.bitwormhole.starter4a.StarterActivity;
import com.bitwormhole.starter4a.ui.boxes.B2OnTouchContext;
import com.bitwormhole.starter4a.ui.boxes.B2OnTouchPointer;
import com.bitwormhole.starter4a.ui.boxes.B2OnTouchThis;
import com.bitwormhole.starter4a.ui.boxes.B2RenderThis;
import com.bitwormhole.starter4a.ui.boxes.B2View;
import com.bitwormhole.starter4a.ui.boxes.ICanvas;
import com.bitwormhole.starter4a.ui.surfaces.SurfaceContext;
import com.bitwormhole.starter4a.ui.surfaces.SurfaceContextBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SurfaceTouchActivity extends StarterActivity {

    final static Logger logger = LoggerFactory.getLogger(SurfaceTouchActivity.class);


    private SurfaceView mSurfaceV1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_surface_touch);
        mSurfaceV1 = findViewById(R.id.surface_view1);
        setupSurfaceView();
    }

    private void setupSurfaceView() {

        FrontLifeManager lm = this.getFrontContext().getLifeManager();

        SurfaceContextBuilder builder = new SurfaceContextBuilder();
        SurfaceContext sc = builder.getSurfaceContext();
        sc.setParent(this);
        sc.setView(mSurfaceV1);
        sc.setView2(new MyInnerView());
        sc.setLifeManager(lm);

        sc = builder.create();
        lm.add(sc.getLife());
    }

    private static class MyPointerBuffer {

        final int id;
        final List<PointF> points;

        MyPointerBuffer(int _id) {
            List<PointF> pts = new ArrayList<>();
            this.id = _id;
            this.points = Collections.synchronizedList(pts);
        }

        public void handlePointer(B2OnTouchContext ctx, PointF point) {
            int action = ctx.action;
            switch (action) {
                case B2OnTouchContext.ACTION_DOWN:
                    this.points.clear();
                    this.points.add(point);
                    break;
                case B2OnTouchContext.ACTION_POINTER_DOWN:
                case B2OnTouchContext.ACTION_MOVE:
                case B2OnTouchContext.ACTION_POINTER_UP:
                case B2OnTouchContext.ACTION_UP:
                    this.points.add(point);
                    break;
                case B2OnTouchContext.ACTION_CANCEL:
                default:
                    break;
            }
        }

        public float[] toLineString() {
            final PointF[] src = this.points.toArray(new PointF[0]);
            final int size = src.length;
            if (size < 2) {
                return new float[0];
            }
            float[] dst = new float[(size - 1) * 4];
            for (int i = 1; i < size; ++i) {
                PointF p0 = src[i - 1];
                PointF p1 = src[i];
                int j = (i - 1) * 4;
                dst[j] = p0.x;
                dst[j + 1] = p0.y;
                dst[j + 2] = p1.x;
                dst[j + 3] = p1.y;
            }
            return dst;
        }
    }

    private static class MyPointerSet {
        final MyPointerBuffer[] buffers;

        MyPointerSet() {
            this.buffers = new MyPointerBuffer[10];
        }

        public MyPointerBuffer getBuffer(int id, boolean create) {
            MyPointerBuffer buffer;
            if (0 <= id && id < this.buffers.length) {
                buffer = this.buffers[id];
            } else {
                return null;
            }
            if (buffer == null && create) {
                buffer = new MyPointerBuffer(id);
                this.buffers[id] = buffer;
            }
            return buffer;
        }
    }

    private static class MyInnerView extends B2View {

        private MyPointerSet history;
        private int[] colors;

        MyInnerView() {
            this.history = new MyPointerSet();
            this.colors = new int[]{
                    Color.RED,
                    Color.rgb(255, 255, 100),
                    Color.YELLOW,
                    Color.GREEN,
                    Color.rgb(100, 255, 255),
                    Color.BLUE,
                    Color.rgb(255, 100, 255),
            };
        }

        @Override
        protected void onTouchAfter(B2OnTouchThis self) {
            super.onTouchAfter(self);
            B2OnTouchContext ctx = self.context;
            int action = ctx.action;
            B2OnTouchPointer ptr = ctx.pointer;
            if (action == B2OnTouchContext.ACTION_DOWN) {
                this.history = new MyPointerSet();
            }
            MyPointerBuffer buffer = this.history.getBuffer(ptr.id, true);
            if (buffer == null) {
                return;
            }
            PointF ptr1 = new PointF(ptr.globalX, ptr.globalY);
            PointF ptr2 = self.coordinates.global2local(ptr1);
            buffer.handlePointer(ctx, ptr2);
        }

        @Override
        protected void onPaintAfter(B2RenderThis self) {
            super.onPaintAfter(self);
            ICanvas can = self.getLocalCanvas();
            this.paintBackground(self, can);
            this.paintHistory(self, can);
        }

        private void paintHistory(B2RenderThis self, ICanvas can) {
            Paint p = new Paint();
            p.setStyle(Paint.Style.STROKE);
            p.setStrokeWidth(2);
            MyPointerBuffer[] all = this.history.buffers;
            for (MyPointerBuffer buffer : all) {
                if (buffer == null) {
                    continue;
                }
                p.setColor(this.colorByID(buffer.id));
                float[] line_string = buffer.toLineString();
                can.drawLines(line_string, p);
            }
        }

        private int colorByID(int id) {
            int[] all = this.colors;
            if (all != null) {
                if (0 <= id && id < all.length) {
                    return all[id];
                }
            }
            return 0;
        }

        private void paintBackground(B2RenderThis self, ICanvas can) {
            float w = self.view.width;
            float h = self.view.height;
            Paint p = new Paint();
            p.setColor(Color.BLUE);
            p.setStyle(Paint.Style.FILL_AND_STROKE);
            can.drawRect(0, 0, w, h, p);
        }
    }
}
