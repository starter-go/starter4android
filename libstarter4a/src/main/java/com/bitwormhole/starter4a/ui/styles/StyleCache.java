package com.bitwormhole.starter4a.ui.styles;

import com.bitwormhole.starter4a.ui.boxes.B2Style;

public class StyleCache {

    private B2Style style; // 已经缓存的样式
    private StyleHolder holder; // 样式来源
    private long revision;

    public StyleCache() {
    }

    public long getRevision() {
        return revision;
    }

    public void setRevision(long revision) {
        this.revision = revision;
    }

    public B2Style getStyle() {
        return style;
    }

    public void setStyle(B2Style style) {
        this.style = style;
    }
}
