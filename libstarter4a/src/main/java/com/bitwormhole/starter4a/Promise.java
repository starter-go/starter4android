package com.bitwormhole.starter4a;

import android.content.Context;
import android.os.Handler;

import com.bitwormhole.starter4j.base.StarterException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

public final class Promise<T> {

    private final PromiseInner<T> mInner;

    public Promise(Context ctx) {
        this.mInner = new PromiseInner<>();
        this.mInner.init(ctx);
    }

    public Promise(PromiseContext pc) {
        this.mInner = new PromiseInner<>();
        this.mInner.init(pc);
    }

    public static class Result<T> {
        public T value;
        public Exception error;
    }

    public static interface ResultHandler<T> {
        Result<T> handle(Result<T> res);
    }

    public static interface Task<T> {
        Result<T> run();
    }


    public Promise<T> Run(Task<T> task) {
        this.mInner.addTask(task);
        return this;
    }

    public Promise<T> Then(ResultHandler<T> callback) {
        this.mInner.addThenHandler(callback);
        return this;
    }

    public Promise<T> Catch(ResultHandler<T> callback) {
        this.mInner.addCatchHandler(callback);
        return this;
    }

    public Promise<T> Finally(ResultHandler<T> callback) {
        this.mInner.addFinallyHandler(callback);
        return this;
    }

    public void Start() {
        this.mInner.start();
    }
}
