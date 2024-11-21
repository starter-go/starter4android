package com.bitwormhole.starter4a.contexts;

import com.bitwormhole.starter4j.base.SafeMode;

import java.nio.file.Path;

public final class Document extends ScopeContext {

    private final Project ownerProject;
    private Path file;
    private String name;
    private String title;
    private String description;

    public Document(Project _owner, SafeMode _mode) {
        super(_owner, _mode);
        this.ownerProject = _owner;
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

    public Path getFile() {
        return file;
    }

    public void setFile(Path file) {
        this.file = file;
    }

    public Project getOwnerProject() {
        return ownerProject;
    }
}
