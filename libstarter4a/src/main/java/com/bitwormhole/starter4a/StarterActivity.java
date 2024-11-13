package com.bitwormhole.starter4a;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

public class StarterActivity extends Activity {

    private final FrontLifeManager mLifeManager;

    public StarterActivity() {
        mLifeManager = new FrontLifeManager();
    }

    public final FrontLifeManager getLifeManager() {
        return mLifeManager;
    }


    private void forMainLife(FrontLifeAbs.OnLifeListener listener) {
        FrontLife ml = mLifeManager.getMain();
        listener.OnLife(ml);
    }


    // handlers

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
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
