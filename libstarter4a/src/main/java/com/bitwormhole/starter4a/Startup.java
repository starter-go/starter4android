package com.bitwormhole.starter4a;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import com.bitwormhole.starter4a.contexts.Current;
import com.bitwormhole.starter4a.contexts.CurrentWrapper;
import com.bitwormhole.starter4j.application.tasks.Result;
import com.bitwormhole.starter4j.base.StarterException;
import com.bitwormhole.starter4j.base.Time;

public final class Startup {

    // private final MyInnerStarting mInner;

    // private Startup(StarterActivity sa) {
    //     mInner = new MyInnerStarting(sa);
    // }

    // public static Startup init(StarterActivity ctx) {
    //     return new Startup(ctx);
    // }

    // private static final class MyInnerStarting implements StarterServiceClient.BinderListener {

    //     private final StarterActivity mActivity;
    //     private final StarterServiceClient mSSClient;
    //     private final Context mContext;


    //     private StarterBinder mBinder;
    //     private Current mCurrent;


    //     MyInnerStarting(StarterActivity sa) {
    //         this.mActivity = sa;
    //         this.mContext = sa;
    //         this.mSSClient = new StarterServiceClient(sa);
    //     }

    //     @Override
    //     public void onBind(ComponentName componentName, StarterBinder b) {
    //         this.mCurrent = b.current();
    //         this.mBinder = b;
    //     }

    //     @Override
    //     public void onUnbind(ComponentName componentName) {
    //         this.mBinder = null;
    //         this.mCurrent = null;
    //     }


    //     public void onStartupBegin() {
    //         mActivity.runOnUiThread(() -> {
    //             Intent i = new Intent(mContext, StarterService.class);
    //             mActivity.startService(i);
    //             mSSClient.setBinderListener(this);
    //             mSSClient.onStart();
    //         });
    //     }

    //     private Current waitForCurrent(int timeout) {
    //         final int step = 100; // ms
    //         for (int ttl = timeout; ttl > 0; ttl -= step) {
    //             Current cur = this.mCurrent;
    //             if (cur != null) {
    //                 return cur;
    //             }
    //             Time.sleep(step);
    //         }
    //         throw new StarterException("waitForCurrent: timeout");
    //     }


    //     public Current runStartup() {

    //         CurrentWrapper cw = CurrentWrapper.getInstance(this.mContext);
    //         if (cw.isAppReady()) {
    //             return this.waitForCurrent(5000);
    //         }
    //         cw.initFramework();
    //         cw.initApp();
    //         try {
    //             cw.initUserSession();
    //         } catch (Exception e) {
    //             Errors.handleError(this.mContext, e);
    //         }
    //         return this.waitForCurrent(5000);
    //     }

    //     public void onStartupEnd() {
    //         mActivity.runOnUiThread(() -> {
    //             mSSClient.onStop();
    //         });
    //     }
    // }


    // public Result<Current> boot() {
    //     try {
    //         this.mInner.onStartupBegin();
    //         Current cur = this.mInner.runStartup();
    //         return new Result<>(cur);
    //     } catch (Exception e) {
    //         return new Result<>(e);
    //     } finally {
    //         this.mInner.onStartupEnd();
    //     }
    // }
}
