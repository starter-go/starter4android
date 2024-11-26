package com.bitwormhole.starter4a.contexts;

import com.bitwormhole.starter4j.application.attributes.Attributes;
import com.bitwormhole.starter4j.base.SafeMode;
import com.bitwormhole.starter4a.DataScope;

import java.nio.file.Path;
import java.security.KeyPair;

import javax.crypto.SecretKey;

public class ScopeContext {

    private final ScopeContext parent;
    private final Attributes attributes;
    private final DataScope scope;
    private final ScopeContextFiles files;
    private Path folder;
    private SecretKey secretKey;
    private KeyPair keyPair;


    public ScopeContext(ScopeContext _parent, DataScope _scope, SafeMode mode) {
        this.parent = _parent;
        this.attributes = Attributes.Table.create(mode);
        this.scope = _scope;
        this.files = new ScopeContextFiles(this);
    }

    public Path getFolder() {
        return folder;
    }

    public void setFolder(Path folder) {
        this.folder = folder;
    }

    public ScopeContext getParent() {
        return parent;
    }

    public DataScope getScope() {
        return scope;
    }


    public KeyPair getKeyPair() {
        return keyPair;
    }

    public void setKeyPair(KeyPair keyPair) {
        this.keyPair = keyPair;
    }

    public SecretKey getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(SecretKey secretKey) {
        this.secretKey = secretKey;
    }

    public ScopeContextFiles getFiles() {
        return files;
    }

    public Attributes getAttributes() {
        return attributes;
    }
}
