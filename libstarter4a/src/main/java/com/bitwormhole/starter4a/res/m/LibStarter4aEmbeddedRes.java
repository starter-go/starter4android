package com.bitwormhole.starter4a.res.m;

import com.bitwormhole.starter4j.application.resources.EmbeddedRes;
import com.bitwormhole.starter4j.application.resources.EmbeddedResources;
import com.bitwormhole.starter4j.application.resources.Resources;

import java.util.List;

public final class LibStarter4aEmbeddedRes {

    private LibStarter4aEmbeddedRes() {
    }

    public static Resources res() {
        List<EmbeddedRes> all = EmbeddedResMain.all();
        return EmbeddedResources.create(all);
    }
}
