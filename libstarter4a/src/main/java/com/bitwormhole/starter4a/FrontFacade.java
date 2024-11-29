package com.bitwormhole.starter4a;

import android.app.Activity;

import com.bitwormhole.starter4j.application.tasks.PromiseBuilder;
import com.bitwormhole.starter4j.application.tasks.PromiseContext;

final class FrontFacade implements IFront {

    private final FrontContext context;

    public FrontFacade(FrontContext fc) {
        this.context = fc;
    }

    public final <T> PromiseBuilder<T> createNewPromise(Class<T> t) {
        PromiseContext pc = createPromiseContext(context.getActivity());
        PromiseBuilder<T> b = new PromiseBuilder<>();
        b.setContext(pc);
        b.setType(t);
        return b;
    }

    private static PromiseContext createPromiseContext(Activity ctx) {
        PromiseContext pc = new PromiseContext();
        pc.setBackground(new DefaultPromiseBackground(ctx));
        pc.setForeground(new DefaultPromiseForeground(ctx));
        return pc;
    }
}
