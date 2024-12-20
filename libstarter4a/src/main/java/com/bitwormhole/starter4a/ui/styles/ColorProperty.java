package com.bitwormhole.starter4a.ui.styles;

import com.bitwormhole.starter4a.ui.boxes.B2Property;
import com.bitwormhole.starter4a.ui.boxes.B2PropertyHandler;
import com.bitwormhole.starter4a.ui.boxes.B2PropertyHandlerProvider;
import com.bitwormhole.starter4a.ui.boxes.B2PropertyQuery;

public class ColorProperty implements B2Property, B2PropertyHandlerProvider {

    private int color;

    public ColorProperty(int value) {
        this.color = value;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    @Override
    public Object value() {
        return this.color;
    }


    private class MyHandler implements B2PropertyHandler {
        @Override
        public boolean query(B2PropertyQuery q) {
            q.ok = false;
            if (q.property == null) {
                q.property = ColorProperty.this;
                q.ok = true;
            } else if (q.property instanceof ColorProperty) {
                ColorProperty dst = (ColorProperty) q.property;
                ColorProperty src = ColorProperty.this;
                dst.setColor(src.getColor());
                q.ok = true;
            }
            return q.ok;
        }
    }


    @Override
    public B2PropertyHandler getHandler() {
        return new MyHandler();
    }
}
