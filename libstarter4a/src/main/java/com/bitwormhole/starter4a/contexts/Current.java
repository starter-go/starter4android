package com.bitwormhole.starter4a.contexts;

import android.content.Context;

import com.bitwormhole.starter4j.base.StarterException;

public class Current {

    private Context context;
    private Framework framework;
    private App app;
    private User user;
    private Project project;
    private Document document;

    public Current() {
    }

    public static Current getInstance(Context ctx) {
        Context c2 = ctx.getApplicationContext();
        CurrentHolder holder = c2.getSystemService(CurrentHolder.class);
        if (holder == null) {
            throw new StarterException("cannot getSystemService with name: " + CurrentHolder.class.getName());
        }
        return holder.getCurrent();
    }


    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public Framework getFramework() {
        return framework;
    }

    public void setFramework(Framework framework) {
        this.framework = framework;
    }

    public App getApp() {
        return app;
    }

    public void setApp(App app) {
        this.app = app;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }
}
