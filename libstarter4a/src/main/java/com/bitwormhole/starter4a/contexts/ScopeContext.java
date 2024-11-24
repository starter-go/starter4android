package com.bitwormhole.starter4a.contexts;

import com.bitwormhole.starter4j.application.attributes.Attributes;
import com.bitwormhole.starter4j.base.SafeMode;
import com.bitwormhole.starter4a.DataScope;

public class ScopeContext {

    private final ScopeContext parent;
    private final Attributes attributes;
    private final DataScope scope;

    public ScopeContext(ScopeContext _parent, DataScope _scope, SafeMode mode) {
        this.parent = _parent;
        this.attributes = Attributes.Table.create(mode);
        this.scope = _scope;
    }

    public ScopeContext getParent() {
        return parent;
    }

    public  DataScope getScope() {
        return scope;
    }


    public Attributes getAttributes() {
        return attributes;
    }
}
