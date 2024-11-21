package com.bitwormhole.starter4android.example.res.m;

import com.bitwormhole.starter4j.application.resources.EmbeddedRes;
import com.bitwormhole.starter4j.application.resources.EmbeddedResources;
import com.bitwormhole.starter4j.application.resources.Resources;

import java.util.List;

public final class S4AExampleModuleRes {

    private S4AExampleModuleRes() {
    }

    public static Resources res() {
        List<EmbeddedRes> all = EmbeddedResMain.all();
        return EmbeddedResources.create(all);
    }
}
