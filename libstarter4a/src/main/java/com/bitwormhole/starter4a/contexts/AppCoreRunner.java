package com.bitwormhole.starter4a.contexts;

import android.content.Context;

import com.bitwormhole.starter4a.Errors;
import com.bitwormhole.starter4j.Initializer;
import com.bitwormhole.starter4j.Starter;
import com.bitwormhole.starter4j.application.ApplicationContext;
import com.bitwormhole.starter4j.application.Module;
import com.bitwormhole.starter4j.application.attributes.Attributes;
import com.bitwormhole.starter4j.base.SafeMode;
import com.bitwormhole.starter4j.base.StarterException;
import com.bitwormhole.starter4j.base.Time;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AppCoreRunner implements Runnable {

    private final CurrentHolder currentHolder;

    public AppCoreRunner(CurrentHolder ch) {
        this.currentHolder = ch;
    }

    public void run() {

        final Current current = this.currentHolder.getCurrent();
        Framework framework = current.getFramework();
        App app = new App(framework, SafeMode.Safe);
        Module mm = framework.getMainModule();
        Customizer customizer = framework.getCustomizer();
        String[] args = {};
        current.setApp(app);
        app.setFolder(getAppPrivateDir());

        Initializer i = Starter.init(args);
        i.setMainModule(mm);
        this.bindAttrCurrentHolder(i);

        if (customizer != null) {
            customizer.customize(i);
        }

        try {
            Exception e = i.run();
            Errors.handleError(e);
        } catch (Exception e) {
            Errors.handleError(e);
        }
    }

    private Path getAppPrivateDir() {
        Context ctx = this.currentHolder.getCurrent().getContext();
        File dir = ctx.getFilesDir();
        return Paths.get(dir.toURI());
    }


    private void bindAttrCurrentHolder(Initializer i) {
        Attributes attrs = i.getAttributes();
        AppCurrentHolder.bind(attrs, this.currentHolder);
    }

    public void start() {
        Thread th = new Thread(this);
        th.start();
    }

    private boolean isReady() {
        App app = this.currentHolder.getCurrent().getApp();
        if (app == null) {
            return false;
        }
        ApplicationContext ac = app.getContext();
        return (ac != null);
    }

    public void waitForReady(int timeout) {
        final int step = 1000;
        for (int ttl = timeout; ttl > 0; ttl -= step) {
            if (isReady()) {
                return;
            }
            Time.sleep(step);
        }
        throw new StarterException("timeout: while waitForReady() at " + this);
    }
}
