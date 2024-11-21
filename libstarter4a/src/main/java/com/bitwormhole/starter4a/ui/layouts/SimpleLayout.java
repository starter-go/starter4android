package com.bitwormhole.starter4a.ui.layouts;

import com.bitwormhole.starter4a.ui.boxes.B2Container;
import com.bitwormhole.starter4a.ui.boxes.B2LayoutThis;
import com.bitwormhole.starter4a.ui.boxes.B2Layout;
import com.bitwormhole.starter4a.ui.boxes.B2View;

import java.util.List;

public class SimpleLayout implements B2Layout {


    private static final SimpleLayout theInst = new SimpleLayout();

    public static SimpleLayout getInstance() {
        return theInst;
    }

    @Override
    public void apply(B2Container container, B2LayoutThis self) {
        float w = container.width;
        float h = container.height;
        List<B2View> children = container.listChildren();
        for (B2View child : children) {
            child.x = 0;
            child.y = 0;
            child.width = w;
            child.height = h;
        }
    }
}
