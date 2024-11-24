package com.bitwormhole.starter4a.ui.layouts;

import com.bitwormhole.starter4a.ui.boxes.B2Container;
import com.bitwormhole.starter4a.ui.boxes.B2LayoutThis;
import com.bitwormhole.starter4a.ui.boxes.B2Layout;

public class NOPLayout implements B2Layout {

    private static final NOPLayout theInst = new NOPLayout();

    public static NOPLayout getInstance() {
        return theInst;
    }

    @Override
    public void apply(B2Container container, B2LayoutThis self) {
    }
}
