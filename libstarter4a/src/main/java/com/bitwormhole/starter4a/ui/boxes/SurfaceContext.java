package com.bitwormhole.starter4a.ui.boxes;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.bitwormhole.starter4a.FrontLife;
import com.bitwormhole.starter4a.FrontLifeManager;


public class SurfaceContext {

    private SurfaceView view;
    private SurfaceHolder holder;
    private SurfaceHolder.Callback callback;
    private View.OnTouchListener onTouchListener;
    private B2View view2;
    private Runnable looper;
    private FrontLife life;
    private FrontLifeManager lifeManager;
    private Context parent;

    private int width;
    private int height;
    private boolean active;
    private int layoutRevision;


    public SurfaceContext() {
    }

    public B2View getView2() {
        return view2;
    }

    public void setView2(B2View view2) {
        this.view2 = view2;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public SurfaceView getView() {
        return view;
    }

    public void setView(SurfaceView view) {
        this.view = view;
    }

    public SurfaceHolder getHolder() {
        return holder;
    }

    public void setHolder(SurfaceHolder holder) {
        this.holder = holder;
    }

    public SurfaceHolder.Callback getCallback() {
        return callback;
    }

    public void setCallback(SurfaceHolder.Callback callback) {
        this.callback = callback;
    }

    public View.OnTouchListener getOnTouchListener() {
        return onTouchListener;
    }

    public void setOnTouchListener(View.OnTouchListener onTouchListener) {
        this.onTouchListener = onTouchListener;
    }

    public Runnable getLooper() {
        return looper;
    }

    public void setLooper(Runnable looper) {
        this.looper = looper;
    }


    public int getLayoutRevision() {
        return layoutRevision;
    }

    public void setLayoutRevision(int layoutRevision) {
        this.layoutRevision = layoutRevision;
    }

    public void updateLayoutRevision(int delta) {
        this.layoutRevision += delta;
    }


    public boolean isFinallyActive() {
        return (active && (holder != null) && (looper != null));
    }

    public boolean isActive() {
        return active;
    }


    public void setActive(boolean active) {
        this.active = active;
    }


    public FrontLife getLife() {
        return life;
    }

    public void setLife(FrontLife life) {
        this.life = life;
    }

    public FrontLifeManager getLifeManager() {
        return lifeManager;
    }

    public void setLifeManager(FrontLifeManager lifeManager) {
        this.lifeManager = lifeManager;
    }

    public Context getParent() {
        return parent;
    }

    public void setParent(Context parent) {
        this.parent = parent;
    }
}
