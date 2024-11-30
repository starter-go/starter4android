package com.bitwormhole.starter4a.ui.styles;

import com.bitwormhole.starter4a.ui.boxes.B2Style;

public class StyleHolder {

    private B2Style style;
    private StyleSelector selector;

    public StyleHolder() {
    }

    public B2Style getStyle() {
        return style;
    }

    public void setStyle(B2Style style) {
        this.style = style;
    }

    public StyleSelector getSelector() {
        return selector;
    }

    public void setSelector(StyleSelector selector) {
        this.selector = selector;
    }
}
