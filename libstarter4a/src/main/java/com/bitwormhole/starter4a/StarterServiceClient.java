package com.bitwormhole.starter4a;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class StarterServiceClient extends FrontLifeBase {

    private final Activity context;
    private final MyConnection conn;
    private BinderListener binderListener;
    private StarterBinder binder;

    public StarterServiceClient(Activity activity) {
        this.context = activity;
        this.conn = new MyConnection();
    }

    private class MyConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder iBinder) {
            StarterBinder b = (StarterBinder) iBinder;
            BinderListener l = binderListener;
            if (b != null) {
                b.getLocalAPI("/starter/service/binder/connected");
            }
            if (b != null && l != null && name != null) {
                l.onBind(name, b);
            }
            binder = b;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            BinderListener l = binderListener;
            StarterBinder b = binder;
            if (b != null) {
                b.getLocalAPI("/starter/service/binder/disconnected");
            }
            if (l != null && name != null) {
                l.onUnbind(name);
            }
            binder = null;
        }
    }

    public interface BinderListener {
        void onBind(ComponentName componentName, StarterBinder b);

        void onUnbind(ComponentName componentName);
    }

    public BinderListener getBinderListener() {
        return binderListener;
    }

    public void setBinderListener(BinderListener binderListener) {
        this.binderListener = binderListener;
    }

    public StarterBinder getBinder() {
        return binder;
    }

    public void setBinder(StarterBinder binder) {
        this.binder = binder;
    }

    @Override
    public void onStart() {
        super.onStart();
        Intent i = new Intent(this.context, StarterService.class);
        //     this.context.startService(i);
        this.context.bindService(i, this.conn, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onStop() {
        super.onStop();
        this.context.unbindService(this.conn);
    }
}
