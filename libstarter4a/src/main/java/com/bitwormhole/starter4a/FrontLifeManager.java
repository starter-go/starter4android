package com.bitwormhole.starter4a;

import android.os.Bundle;

import androidx.annotation.Nullable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public final class FrontLifeManager {

    final static Logger logger = LoggerFactory.getLogger(FrontLifeManager.class);

    private final List<FrontLife> mItems;
    private List<FrontLife> mOnCreateBuffer;
    private MyOnCreateCaller mOnCreateCaller;
    private FrontLife mMainLife;

    public FrontLifeManager() {
        mItems = new ArrayList<>();
        mOnCreateBuffer = new ArrayList<>();
        mOnCreateCaller = null;
    }

    public void add(FrontLife l) {
        if (l == null) {
            return;
        }
        if (this.mItems.contains(l)) {
            return; // 避免重复
        }
        MyOnCreateCaller caller = mOnCreateCaller;
        List<FrontLife> buffer = mOnCreateBuffer;
        if (caller != null) {
            caller.invoke(l);
        }
        if (buffer != null) {
            buffer.add(l);
        }
        mItems.add(l);
    }


    private void invokeItem(FrontLife item, FrontLifeAbs.OnLifeListener listener) {
        try {
            listener.OnLife(item);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void forEachItem(boolean reverse, FrontLifeAbs.OnLifeListener listener) {
        final List<FrontLife> all = new ArrayList<>(mItems);
        if (!reverse) {
            // forward
            for (FrontLife item : all) {
                invokeItem(item, listener);
            }
        } else {
            // backward
            for (int i = all.size(); i > 0; ) {
                i--;
                FrontLife item = all.get(i);
                invokeItem(item, listener);
            }
        }
    }

    private static class MyOnCreateCaller {

        final Bundle savedInstanceState;

        MyOnCreateCaller(Bundle b) {
            this.savedInstanceState = b;
        }

        public void invoke(FrontLife l) {
            l.onCreate(this.savedInstanceState);
        }
    }


    private class MyMainLife implements FrontLife {

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            MyOnCreateCaller caller = new MyOnCreateCaller(savedInstanceState);
            List<FrontLife> buffer = mOnCreateBuffer;
            if (buffer != null) {
                for (FrontLife l : buffer) {
                    caller.invoke(l);
                }
            }
            mOnCreateBuffer = null;
            mOnCreateCaller = caller;
        }

        @Override
        public void onStart() {
            mOnCreateCaller = null;
            mOnCreateBuffer = null;
            forEachItem(false, FrontLife::onStart);
        }

        @Override
        public void onRestart() {
            mOnCreateCaller = null;
            mOnCreateBuffer = null;
            forEachItem(false, FrontLife::onRestart);
        }

        @Override
        public void onPause() {
            forEachItem(true, FrontLife::onPause);
        }

        @Override
        public void onResume() {
            forEachItem(false, FrontLife::onResume);
        }

        @Override
        public void onStop() {
            forEachItem(true, FrontLife::onStop);
        }

        @Override
        public void onDestroy() {
            forEachItem(true, FrontLife::onDestroy);
        }
    }

    public FrontLife getMain() {
        FrontLife ml = mMainLife;
        if (ml == null) {
            ml = new MyMainLife();
            mMainLife = ml;
        }
        return ml;
    }
}
