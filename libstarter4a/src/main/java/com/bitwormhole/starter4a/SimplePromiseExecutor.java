package com.bitwormhole.starter4a;

import java.util.concurrent.Executor;

public class SimplePromiseExecutor implements Executor {
    @Override
    public void execute(Runnable runnable) {
        Thread th = new Thread(runnable);
        th.start();
    }
}
