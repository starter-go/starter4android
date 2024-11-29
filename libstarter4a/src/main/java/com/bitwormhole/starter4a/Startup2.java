package com.bitwormhole.starter4a;

import android.content.Context;

import com.bitwormhole.starter4a.contexts.CurrentWrapper;
import com.bitwormhole.starter4j.application.tasks.Promise;
import com.bitwormhole.starter4j.application.tasks.PromiseContext;
import com.bitwormhole.starter4j.application.tasks.Result;
import com.bitwormhole.starter4j.base.Time;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Startup2 {

    final static Logger logger = LoggerFactory.getLogger(Startup2.class);

    private Startup2() {
    }

    public static Promise<StarterBinder> start(StarterActivity activity) {
        return start(activity.getFrontContext());
    }

    public static Promise<StarterBinder> start(FrontContext fc) {
        final StarterBinderHolder holder = new StarterBinderHolder();
        final InnerProc ip = new InnerProc(holder);
        final PromiseContext pc = fc.getPromiseContext();
        fc.getClient().addObserver(ip.getObserver());
        final Promise<StarterBinder> promise = Promise.init(pc, StarterBinder.class).Try(() -> {
            ip.run(fc.getActivity());
            return new Result<>(holder.getBinder());
        });
        promise.start();
        return promise;
    }

    private static class InnerProc {

        final StarterBinderHolder holder;

        public InnerProc(StarterBinderHolder h) {
            this.holder = h;
        }

        private StarterBinder waitForStarterBinderReady(int timeout) {
            final int step = 333;
            for (int ttl = timeout; ttl > 0; ttl -= step) {
                StarterBinder binder = this.holder.getBinder();
                if (binder != null) {
                    return binder;
                }
                Time.sleep(step);
            }
            throw new RuntimeException("timeout: " + this + ".waitForStarterBinderReady()");
        }

        void run(Context ctx) {
            StarterBinder binder = this.waitForStarterBinderReady(10 * 1000);
            CurrentWrapper cw = new CurrentWrapper(binder.current());
            if (cw.isAppReady()) {
                logger.info("Startup: ready");
                return;
            }
            cw.initFramework();
            cw.initApp();
            try {
                cw.initUserSession();
                logger.info("Startup: ok");
            } catch (Exception e) {
                Errors.handleError(ctx, e);
            }
            logger.info("Startup: done");
        }

        StarterBinderObserver getObserver() {
            StarterBinderObserver obs = new StarterBinderObserver();
            obs.setOnBegin((binderHolder) -> {
                this.holder.setBinder(binderHolder.getBinder());
            });
            return obs;
        }
    }
}
