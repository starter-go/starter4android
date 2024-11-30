package com.bitwormhole.starter4a.ui.styles;

import androidx.annotation.NonNull;

public class StyleSelector {

    public String id;
    public String clazz;
    public String state;

    public StyleSelector() {
    }

    @NonNull
    @Override
    public String toString() {
        final StringBuilder b = new StringBuilder();
        final String i = id;
        final String c = clazz;
        final String s = state;
        if (i != null) {
            b.append('#').append(i);
        }
        if (c != null) {
            b.append('.').append(c);
        }
        if (s != null) {
            b.append(':').append(s);
        }
        return b.toString();
    }
}
