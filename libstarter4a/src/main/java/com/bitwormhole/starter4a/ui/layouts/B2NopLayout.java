package com.bitwormhole.starter4a.ui.layouts;

import android.graphics.RectF;

import com.bitwormhole.starter4a.ui.boxes.B2Container;
import com.bitwormhole.starter4a.ui.boxes.B2Layout;
import com.bitwormhole.starter4a.ui.boxes.B2LayoutThis;

public class B2NopLayout implements B2Layout {


    private static final B2NopLayout theInst = new B2NopLayout();

    public static B2NopLayout getInstance() {
        return theInst;
    }

    @Override
    public void apply( B2Container container, B2LayoutThis self ) {

    }

}
