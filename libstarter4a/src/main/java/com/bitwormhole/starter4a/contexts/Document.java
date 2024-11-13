package com.bitwormhole.starter4a.contexts;

import com.bitwormhole.starter4j.base.SafeMode;

public final class Document extends ScopeContext {

    private final Project ownerProject;

    public Document(Project _owner, SafeMode _mode) {
        super(_owner, _mode);
        this.ownerProject = _owner;
    }

    public Project getOwnerProject() {
        return ownerProject;
    }
}
