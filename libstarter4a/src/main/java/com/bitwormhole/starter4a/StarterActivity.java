package com.bitwormhole.starter4a;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

public class StarterActivity extends Activity {

    private FrontContext mFrontContext;

    public StarterActivity() {
    }

    public FrontContext getFrontContext() {
        FrontContext fc = mFrontContext;
        if (fc == null) {
            throw new RuntimeException("getFrontContext without init");
        }
        return fc;
    }

    private void forMainLife(FrontLifeAbs.OnLifeListener listener) {
        FrontLife ml = mFrontContext.getLifeManager().getMain();
        listener.OnLife(ml);
    }

    private void init() {
        FrontContext fc = mFrontContext;
        if (fc != null) {
            return;
        }
        FrontContextBuilder b = new FrontContextBuilder();
        b.setActivity(this);
        fc = b.create();
        mFrontContext = fc;
    }

    // handlers

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        this.init();
        super.onCreate(savedInstanceState);
        forMainLife((ml) -> {
            ml.onCreate(savedInstanceState);
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        forMainLife(FrontLife::onStart);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        forMainLife(FrontLife::onRestart);
    }

    @Override
    protected void onPause() {
        super.onPause();
        forMainLife(FrontLife::onPause);
    }

    @Override
    protected void onResume() {
        super.onResume();
        forMainLife(FrontLife::onResume);
    }

    @Override
    protected void onStop() {
        super.onStop();
        forMainLife(FrontLife::onStop);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        forMainLife(FrontLife::onDestroy);
    }
}
