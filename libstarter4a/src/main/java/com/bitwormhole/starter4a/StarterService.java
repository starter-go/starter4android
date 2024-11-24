package com.bitwormhole.starter4a;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.bitwormhole.starter4a.contexts.Current;
import com.bitwormhole.starter4a.settings.SettingManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class StarterService extends Service {

    final static Logger logger = LoggerFactory.getLogger(StarterService.class);

    private int mCountOnCreate;
    private int mCountOnDestroy;
    private Current mCurrent;
    private final MyBinder mBinder;

    public StarterService() {
        this.mBinder = new MyBinder();
    }

    private class MyBinder extends Binder implements StarterBinder {
        @Override
        public IBinder binder() {
            return mBinder;
        }

        @Override
        public <T extends LocalAPI> T getLocalAPI(Class<T> t) {
            return null;
        }

        @Override
        public <T extends RemoteAPI> T getRemoteAPI(Class<T> t) {
            return null;
        }

        @Override
        public SettingManager getSettingManager() {
            return null;
        }




        @Override
        public Current current() {
            Current c = mCurrent;
            if (c == null) {
                c = Current.getInstance(StarterService.this);
                mCurrent = c;
            }
            return c;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return this.mBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    private void logCount() {
        StringBuilder b = new StringBuilder();
        b.append("[" + this);
        b.append(" count_on_create:").append(this.mCountOnCreate);
        b.append(" count_on_destroy:").append(this.mCountOnDestroy);
        b.append(']');
        logger.info(b.toString());
    }


    @Override
    public void onCreate() {
        super.onCreate();
        this.mCountOnCreate++;
        logger.info(this + ".onCreate()");
        this.getApplicationContext();
        this.logCount();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mCountOnDestroy++;
        logger.info(this + ".onDestroy()");
        this.logCount();
    }
}
