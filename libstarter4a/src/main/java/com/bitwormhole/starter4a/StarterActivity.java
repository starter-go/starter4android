package com.bitwormhole.starter4a;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.bitwormhole.starter4j.application.tasks.PromiseBuilder;
import com.bitwormhole.starter4j.application.tasks.PromiseContext;

import java.util.concurrent.Executor;

public class StarterActivity extends Activity {

    private FrontLifeManager mLifeManager;
    private PromiseContext mPromiseContext;
    private StarterServiceClient mSSClient;

    public StarterActivity() {
    }

    // 必须在 onCreate 执行之后，才能调用这个方法
    public final StarterServiceClient getStarterServiceClient() {
        return this.mSSClient;
    }

    public final FrontLifeManager getLifeManager() {
        return mLifeManager;
    }

    public final <T> PromiseBuilder<T> createNewPromise(Class<T> t) {
        PromiseBuilder<T> b = new PromiseBuilder<>();
        b.setContext(new PromiseContext(this.mPromiseContext));
        b.setType(t);
        return b;
    }

    private static PromiseContext createPromiseContext() {
        PromiseContext pc = new PromiseContext();
        pc.setBackground(new DefaultPromiseBackground());
        pc.setForeground(new DefaultPromiseForeground());
        return pc;
    }

    private void forMainLife(FrontLifeAbs.OnLifeListener listener) {
        FrontLife ml = mLifeManager.getMain();
        listener.OnLife(ml);
    }


    // handlers

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        mPromiseContext = createPromiseContext();
        mLifeManager = new FrontLifeManager();
        mSSClient = new StarterServiceClient(this);

        mLifeManager.add(mSSClient);

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
