package com.bitwormhole.starter4a;

import android.app.Activity;

import com.bitwormhole.starter4j.application.tasks.PromiseContext;
import com.bitwormhole.starter4j.base.StarterException;

public class FrontContextBuilder {

    private Activity activity;
    private StarterServiceClient client;
    private FrontLifeManager lifeManager;
    private PromiseContext promiseContext;

    public FrontContextBuilder() {
    }

    public FrontContext create() {

        if (activity == null) {
            throw new StarterException("FrontContextBuilder: activity is null");
        }

        if (client == null) {
            client = new StarterServiceClient(activity);
        }

        if (lifeManager == null) {
            lifeManager = new FrontLifeManager();
        }

        if (promiseContext == null) {
            AndroidPromiseContextBuilder pcb = new AndroidPromiseContextBuilder();
            pcb.setActivity(this.activity);
            promiseContext = pcb.create();
        }

        lifeManager.add(client);
        return new FrontContext(this);
    }


    public FrontLifeManager getLifeManager() {
        return lifeManager;
    }

    public void setLifeManager(FrontLifeManager lifeManager) {
        this.lifeManager = lifeManager;
    }

    public PromiseContext getPromiseContext() {
        return promiseContext;
    }

    public void setPromiseContext(PromiseContext promiseContext) {
        this.promiseContext = promiseContext;
    }

    public StarterServiceClient getClient() {
        return client;
    }

    public void setClient(StarterServiceClient client) {
        this.client = client;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }
}
