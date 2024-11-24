// package com.bitwormhole.starter4a.ui.elements.b2;

package com.bitwormhole.starter4a.ui.elements;

import android.graphics.Paint;
import android.util.Log;

import com.bitwormhole.starter4a.StarterLogger;
import com.bitwormhole.starter4a.ui.boxes.B2OnTouchContext;
import com.bitwormhole.starter4a.ui.boxes.B2OnTouchThis;
import com.bitwormhole.starter4a.ui.boxes.B2RenderThis;
import com.bitwormhole.starter4a.ui.boxes.B2State;
import com.bitwormhole.starter4a.ui.boxes.B2Style;
import com.bitwormhole.starter4a.ui.boxes.ICanvas;
import com.bitwormhole.starter4a.ui.styles.B2StyleReader;

public class B2Button extends B2TextView {

    public B2Button() {
        this.interactive = true;
        this.setState(B2State.AUTO);
    }

    @Override
    public B2State getState() {
        return super.getState();
    }

    @Override
    protected void onTouchBefore(B2OnTouchThis self) {
        super.onTouchBefore(self);
        int action = self.context.action;
        if (action == B2OnTouchContext.ACTION_DOWN || action == B2OnTouchContext.ACTION_POINTER_DOWN) {
            String str = this.getText();
            String msg = "B2Button.onTouchBefore, action:" + action + " at:" + str;
            Log.i(StarterLogger.TAG, msg);
            this.pressed = true;
        } else if (action == B2OnTouchContext.ACTION_UP || action == B2OnTouchContext.ACTION_POINTER_UP) {
            this.pressed = false;
        }
    }

    @Override
    protected void onTouchChildren(B2OnTouchThis self) {
        super.onTouchChildren(self);
    }

    @Override
    protected void onTouchAfter(B2OnTouchThis self) {
        super.onTouchAfter(self);
    }
}
