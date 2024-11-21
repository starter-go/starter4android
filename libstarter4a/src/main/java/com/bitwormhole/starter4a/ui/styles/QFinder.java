package com.bitwormhole.starter4a.ui.styles;

import com.bitwormhole.starter4a.ui.boxes.B2PropertyHandler;
import com.bitwormhole.starter4a.ui.boxes.B2PropertyHolder;
import com.bitwormhole.starter4a.ui.boxes.B2PropertyQuery;
import com.bitwormhole.starter4a.ui.boxes.B2State;
import com.bitwormhole.starter4a.ui.boxes.B2Style;

class QFinder implements B2PropertyHandler {

    private final B2Style style;

    public QFinder(B2Style s) {
        this.style = s;
    }


    @Override
    public boolean query(B2PropertyQuery q) {
        B2State state = q.state;
        if (state != null) {
            if (!state.equals(B2State.NORMAL)) {
                boolean ok = tryQueryWithState(q, state);
                if (ok) {
                    return ok;
                }
            }
        }
        return tryQueryWithState(q, null);
    }

    public boolean tryQueryWithState(B2PropertyQuery q, B2State state) {
        for (String name : q.names) {
            if (name == null) {
                continue;
            }
            B2PropertyHolder holder = style.get(name, state);
            if (holder == null) {
                continue;
            }
            B2PropertyHandler handler = holder.handler;
            if (handler == null) {
                continue;
            }
            if (handler.query(q)) {
                return true;
            }
        }
        return false;
    }
}
