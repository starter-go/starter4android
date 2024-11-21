package com.bitwormhole.starter4a.contexts;

import com.bitwormhole.starter4j.application.attributes.Attributes;

public final class AppCurrentHolder implements CurrentHolder {

    private final Current current;

    public AppCurrentHolder(Current c) {
        this.current = c;
    }

    @Override
    public Current getCurrent() {
        return this.current;
    }


    public static void bind(Attributes attrs, CurrentHolder h) {
        if (attrs == null || h == null) {
            return;
        }
        final String key = CurrentHolder.class.getName();
        attrs.setAttr(key, h);
    }

    public static CurrentHolder getInstance(Attributes attrs) {
        if (attrs == null) {
            return null;
        }
        final String key = CurrentHolder.class.getName();
        return attrs.getAttr(key, CurrentHolder.class);
    }
}
