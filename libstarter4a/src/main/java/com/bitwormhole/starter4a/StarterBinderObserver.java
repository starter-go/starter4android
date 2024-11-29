package com.bitwormhole.starter4a;

public final class StarterBinderObserver {

    private StarterBinderHolder.Listener mOnBegin;
    private StarterBinderHolder.Listener mOnEnd;
    private final StarterBinderHolder.Listener mNOP;

    public StarterBinderObserver() {
        final StarterBinderHolder.Listener nop = (l) -> {
        };
        this.mNOP = nop;
        this.mOnBegin = nop;
        this.mOnEnd = nop;
    }


    public StarterBinderObserver setOnBegin(StarterBinderHolder.Listener l) {
        if (l == null) {
            l = this.mNOP;
        }
        this.mOnBegin = l;
        return this;
    }

    public StarterBinderObserver setOnEnd(StarterBinderHolder.Listener l) {
        if (l == null) {
            l = this.mNOP;
        }
        this.mOnEnd = l;
        return this;
    }

    public void invokeOnBegin(StarterBinderHolder holder) {
        if (holder == null) {
            return;
        }
        this.mOnBegin.callback(holder);
    }

    public void invokeOnEnd(StarterBinderHolder holder) {
        if (holder == null) {
            return;
        }
        this.mOnEnd.callback(holder);
    }


    public static StarterBinderObserver observe(FrontContext fc) {
        StarterBinderObserver obs = new StarterBinderObserver();
        fc.getClient().addObserver(obs);
        return obs;
    }

    public static StarterBinderObserver observe(StarterActivity activity) {
        return observe(activity.getFrontContext());
    }
}
