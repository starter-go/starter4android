package com.bitwormhole.starter4a;

import android.content.Context;
import android.os.Handler;

import com.bitwormhole.starter4j.base.StarterException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

final class PromiseInner<T> {

    private final PromiseContext mPC;
    private final List<Promise.Task<T>> mTasks;
    private final List<Promise.ResultHandler<T>> mThenHandlers;
    private final List<Promise.ResultHandler<T>> mCatchHandlers;
    private final List<Promise.ResultHandler<T>> mFinallyHandlers;

    public PromiseInner() {
        this.mPC = new PromiseContext();
        this.mTasks = new ArrayList<>();
        this.mThenHandlers = new ArrayList<>();
        this.mCatchHandlers = new ArrayList<>();
        this.mFinallyHandlers = new ArrayList<>();
    }

    public void init(Context ctx) {
        PromiseContext pc = new PromiseContext();
        pc.setContext(ctx);
        //   pc.setHandler(new Handler());
        // pc.setExecutor(new SimplePromiseExecutor());

        this.mPC.set(pc);
    }

    public void init(PromiseContext pc) {


        //     pc.setContext(ctx);
        //   pc.setHandler(new Handler());
        // pc.setExecutor(new SimplePromiseExecutor());

        this.mPC.set(pc);
    }


    public void addTask(Promise.Task<T> task) {
        if (task == null) {
            return;
        }
        this.mTasks.add(task);
    }

    public void addThenHandler(Promise.ResultHandler<T> callback) {
        if (callback == null) {
            return;
        }
        this.mThenHandlers.add(callback);
    }

    public void addCatchHandler(Promise.ResultHandler<T> callback) {
        if (callback == null) {
            return;
        }
        this.mCatchHandlers.add(callback);
    }

    public void addFinallyHandler(Promise.ResultHandler<T> callback) {
        if (callback == null) {
            return;
        }
        this.mFinallyHandlers.add(callback);
    }


    private class MyRunner<T2> implements Runnable {

        void invokeTasks(Promise.Task<T2>[] tasks, Promise.Result out) {
        }

        void invokeResultHandlers(Promise.ResultHandler[] tasks, Promise.Result out) {
        }


        void dispatchResult(Promise.Result<T2> res) {
            Executor front = mPC.getForeground();
            front.execute(() -> {
                Promise.ResultHandler<T2>[] rh1s = mThenHandlers.toArray(new Promise.ResultHandler[0]);
                Promise.ResultHandler<T2>[] rh2s = mCatchHandlers.toArray(new Promise.ResultHandler[0]);
                Promise.ResultHandler<T2>[] rh3s = mFinallyHandlers.toArray(new Promise.ResultHandler[0]);


                try {
                    invokeResultHandlers(rh1s, res);
                } catch (Exception ex) {
                    res.error = ex;
                    invokeResultHandlers(rh2s, res);
                } finally {
                    invokeResultHandlers(rh3s, res);
                }
            });
        }


        @Override
        public void run() {
            Promise.Result<T2> res = new Promise.Result<>();
            Promise.Task<T2>[] tasks;
            tasks = new Promise.Task[0];
            tasks = mTasks.toArray(tasks);
            try {
                this.invokeTasks(tasks, res);
            } catch (Exception ex) {
                res.error = ex;
            } finally {
                this.dispatchResult(res);
            }
        }
    }

    public void start() {
        Executor bg = this.mPC.getBackground();
        if (bg == null) {
            throw new StarterException("Promise-background-Executor is null");
        }
        MyRunner<T> runner = new MyRunner<>();
        bg.execute(runner);
    }
}
