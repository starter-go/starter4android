package com.bitwormhole.starter4a.contexts;

import com.bitwormhole.starter4j.base.SafeMode;

public final class Project extends ScopeContext {

    private final User owner;

    public Project(User _owner, SafeMode _mode) {
        super(_owner, _mode);
        this.owner = _owner;
    }

    public User getOwner() {
        return owner;
    }
}
