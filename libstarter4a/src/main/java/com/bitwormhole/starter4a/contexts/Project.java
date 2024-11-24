package com.bitwormhole.starter4a.contexts;

import com.bitwormhole.starter4j.base.SafeMode;

import java.nio.file.Path;

import com.bitwormhole.starter4a.DataScope;

public final class Project extends ScopeContext {

    private final User owner;

    private String name;
    private String title;
    private String description;

    public Project(User _owner, SafeMode _mode) {
        super(_owner, DataScope.PROJECT, _mode);
        this.owner = _owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getOwner() {
        return owner;
    }
}
