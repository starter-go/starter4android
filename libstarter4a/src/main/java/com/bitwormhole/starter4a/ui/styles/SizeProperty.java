package com.bitwormhole.starter4a.ui.styles;

import com.bitwormhole.starter4a.ui.boxes.B2Property;
import com.bitwormhole.starter4a.ui.boxes.B2PropertyHandler;
import com.bitwormhole.starter4a.ui.boxes.B2PropertyHandlerProvider;
import com.bitwormhole.starter4a.ui.boxes.B2PropertyQuery;

public class SizeProperty implements B2Property, B2PropertyHandlerProvider {

    private float size; // the size

    public SizeProperty(float value) {
        this.size = value;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    @Override
    public Object value() {
        return this.size;
    }

    private class MyHandler implements B2PropertyHandler {
        @Override
        public boolean query(B2PropertyQuery q) {
            q.ok = false;
            if (q.property == null) {
                q.property = SizeProperty.this;
                q.ok = true;
            } else if (q.property instanceof SizeProperty) {
                SizeProperty dst = (SizeProperty) q.property;
                SizeProperty src = SizeProperty.this;
                dst.setSize(src.getSize());
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
