package com.bitwormhole.starter4a;

import android.app.Activity;

import com.bitwormhole.starter4j.application.tasks.PromiseContext;

public final class FrontContext {

    private final FrontLifeManager lifeManager;
    private final PromiseContext promiseContext;
    private final StarterServiceClient client;
    private final Activity activity;
    private final IFront front;

    FrontContext(FrontContextBuilder b) {
        this.activity = b.getActivity();
        this.client = b.getClient();
        this.lifeManager = b.getLifeManager();
        this.promiseContext = b.getPromiseContext();

        this.front = new FrontFacade(this);
    }

    public FrontLifeManager getLifeManager() {
        return lifeManager;
    }

    public PromiseContext getPromiseContext() {
        return promiseContext;
    }

    public StarterServiceClient getClient() {
        return client;
    }

    public Activity getActivity() {
        return activity;
    }

    public IFront getFront() {
        return front;
    }
}
