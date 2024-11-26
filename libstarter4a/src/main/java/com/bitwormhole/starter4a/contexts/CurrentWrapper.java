package com.bitwormhole.starter4a.contexts;

import android.content.Context;

import com.bitwormhole.starter4j.Initializer;
import com.bitwormhole.starter4j.Starter;
import com.bitwormhole.starter4j.application.Module;
import com.bitwormhole.starter4j.base.SafeMode;
import com.bitwormhole.starter4j.base.StarterException;

public class CurrentWrapper {

    private final Current current;

    public CurrentWrapper(Current c) {
        if (c == null) {
            c = new Current();
        }
        this.current = c;
    }

    public static CurrentWrapper getInstance(Context ctx) {
        Current cu = Current.getInstance(ctx);
        return new CurrentWrapper(cu);
    }

    public Current getCurrent() {
        return current;
    }

    public boolean isFrameworkReady() {
        Framework fw = this.current.getFramework();
        if (fw == null) {
            return false;
        }
        return (fw.getMainModuleProvider() != null && fw.getMainModule() != null);
    }

    public boolean isAppReady() {
        App app = this.current.getApp();
        if (app == null) {
            return false;
        }
        return app.getContext() != null;
    }

    public boolean isUserSessionReady() {
        User user = this.current.getUser();
        if (user == null) {
            return false;
        }
        return user.isAuthenticated();
    }

    public void initFramework() {

        Framework framework = this.current.getFramework();
        if (framework == null) {
            framework = current.getFactory().createFramework(current, SafeMode.Safe);
            this.current.setFramework(framework);
        }

        MainModuleProvider mmp = framework.getMainModuleProvider();
        Module mod = framework.getMainModule();
        Customizer cust = framework.getCustomizer();

        if (mod != null && mmp != null && cust != null) {
            return;
        }

        Context ctx = this.current.getContext().getApplicationContext();
        cust = (Customizer) ctx;
        mmp = (MainModuleProvider) ctx;
        mod = mmp.getMainModule();

        framework.setMainModule(mod);
        framework.setMainModuleProvider(mmp);
        framework.setCustomizer(cust);
    }

    public void initApp() {
        AppCurrentHolder ch = new AppCurrentHolder(this.current);
        AppCoreRunner runner = new AppCoreRunner(ch);
        runner.start();
        runner.waitForReady(2 * 60 * 1000);
    }

    public void initUserSession() {
    }
}
