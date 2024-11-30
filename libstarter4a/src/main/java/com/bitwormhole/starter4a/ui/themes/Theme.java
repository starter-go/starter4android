package com.bitwormhole.starter4a.ui.themes;

import com.bitwormhole.starter4a.ui.boxes.B2Style;
import com.bitwormhole.starter4a.ui.boxes.B2StyleBuilder;
import com.bitwormhole.starter4a.ui.styles.StyleHolder;
import com.bitwormhole.starter4a.ui.styles.StyleSelector;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Theme {

    private final Map<String, StyleHolder> styles;

    private static StyleHolder styleNil;

    public Theme() {
        Map<String, StyleHolder> tab = new HashMap<>();
        this.styles = Collections.synchronizedMap(tab);
    }

    private static String keyFor(StyleSelector sel) {
        if (sel == null) {
            return "null";
        }
        return sel.toString();
    }

    private static StyleHolder getStyleNil() {
        StyleHolder sh = styleNil;
        if (sh == null) {
            sh = initStyleNil();
            styleNil = sh;
        }
        return sh;
    }

    private static StyleHolder initStyleNil() {
        StyleHolder sh = new StyleHolder();
        StyleSelector sel = new StyleSelector();
        B2StyleBuilder sb = new B2StyleBuilder();
        sh.setStyle(sb.create());
        sh.setSelector(sel);
        return sh;
    }

    public StyleHolder findStyle(StyleSelector sel) {
        String key = keyFor(sel);
        StyleHolder sh = this.styles.get(key);
        if (sh == null) {
            sh = getStyleNil();
        }
        return sh;
    }

    public void put(StyleHolder holder) {
        if (holder == null) {
            return;
        }
        B2Style style = holder.getStyle();
        StyleSelector sel = holder.getSelector();
        if (style == null || sel == null) {
            return;
        }
        String key = keyFor(sel);
        this.styles.put(key, holder);
    }
}
