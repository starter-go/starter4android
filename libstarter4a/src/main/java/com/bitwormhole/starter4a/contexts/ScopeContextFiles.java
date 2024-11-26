package com.bitwormhole.starter4a.contexts;

import java.io.IOException;
import java.nio.file.Path;

public final class ScopeContextFiles {

    private final ScopeContext context;

    public ScopeContextFiles(ScopeContext ctx) {
        this.context = ctx;
    }

    public void writeBinary(byte[] bin, Path file) throws IOException {
        //todo ...
    }

    public void writeText(String text, Path file) throws IOException {
        //todo ...
    }

    public byte[] readBinary(Path file) throws IOException {
        return null; //todo ...
    }

    public String readText(Path file) throws IOException {
        return null; //todo ...
    }

}
