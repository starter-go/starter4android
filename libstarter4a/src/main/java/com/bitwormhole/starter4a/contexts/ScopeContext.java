package com.bitwormhole.starter4a.contexts;

import com.bitwormhole.starter4j.application.attributes.Attributes;
import com.bitwormhole.starter4j.base.SafeMode;

public class ScopeContext {

    private final ScopeContext parent;
    private final Attributes attributes;

    public ScopeContext(ScopeContext _parent, SafeMode mode) {
        this.parent = _parent;
        this.attributes = Attributes.Table.create(mode);
    }

    public ScopeContext getParent() {
        return parent;
    }

    public Attributes getAttributes() {
        return attributes;
    }
}
