package com.bitwormhole.starter4a;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.bitwormhole.starter4j.application.tasks.Promise;

public class StarterServiceClient extends FrontLifeBase {

    private final Activity mContext;
    private final MyConnection mConn;
    private final StarterBinderHolder mBinderHolder;

    private Track mTrackCurrent;
    private Track mTrackCD; // for onCreate & onDestroy
    private Track mTrackSS; // for start & stop
    private Track mTrackRP; // for resume & pause

    private Runnable mTaskBind;

    public StarterServiceClient(Activity activity) {
        this.mContext = activity;
        this.mConn = new MyConnection();
        this.mBinderHolder = new StarterBinderHolder();
    }


    private class MyConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder iBinder) {
            final Track tr = StarterServiceClient.this.mTrackCurrent;
            final StarterBinderHolder holder = StarterServiceClient.this.mBinderHolder;
            if (holder != null && tr != null) {
                holder.setBinder((StarterBinder) iBinder);
                StateEvent evt = new StateEvent();
                evt.begin = true;
                evt.holder = holder;
                StateEventDispatcher.dispatch(evt, tr);
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            final Track tr = StarterServiceClient.this.mTrackCurrent;
            final StarterBinderHolder holder = StarterServiceClient.this.mBinderHolder;
            if (holder != null && tr != null) {
                StateEvent evt = new StateEvent();
                evt.end = true;
                evt.holder = holder;
                StateEventDispatcher.dispatch(evt, tr);
                holder.setBinder(null);
            }
        }
    }


    private static class Track {

        private final Track mParent;
        private final ObserverChain mObserverChain;

        public Track(Track parent) {
            this.mParent = parent;
            this.mObserverChain = new ObserverChain();
        }

        void addObserver(StarterBinderObserver observer) {
            this.mObserverChain.add(observer);
        }
    }

    static class ObserverChain {
        ObserverNode first;
        ObserverNode last;

        void add(ObserverNode node) {
            if (node == null) {
                return;
            }
            final ObserverNode p1 = this.first;
            final ObserverNode p2 = this.last;
            if (p1 == null || p2 == null) {
                this.first = node;
            } else {
                p2.next = node;
            }
            this.last = node;
        }

        void add(StarterBinderObserver obs) {
            if (obs == null) {
                return;
            }
            this.add(new ObserverNode(obs));
        }
    }

    static class ObserverNode {
        ObserverNode next;
        final StarterBinderObserver observer;

        ObserverNode(StarterBinderObserver obs) {
            this.observer = obs;
        }
    }

    static class StateEvent {

        StarterBinderHolder holder;
        Throwable error;
        boolean begin;
        boolean end;
    }

    static class StateEventDispatcher {

        static void dispatch(StateEvent event, Track to) {
            try {
                dispatchToTracks(event, to);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        static void dispatchToTracks(StateEvent event, Track to) {
            for (Track tr = to; tr != null; tr = tr.mParent) {
                dispatchToTrack(event, tr);
            }
        }

        static void dispatchToTrack(StateEvent event, final Track to) {
            final ObserverChain chain = to.mObserverChain;
            for (ObserverNode node = chain.first; node != null; node = node.next) {
                dispatchToNode(event, node);
            }
        }

        static void dispatchToNode(StateEvent event, final ObserverNode to) {
            if (event.begin) {
                to.observer.invokeOnBegin(event.holder);
            }
            if (event.end) {
                to.observer.invokeOnEnd(event.holder);
            }
        }
    }


    private void bind(Intent i) {
        this.mContext.bindService(i, this.mConn, Context.BIND_AUTO_CREATE);
    }

    private void unbind() {
        this.mContext.unbindService(this.mConn);
    }

    private void initService(Intent i) {
    }

    public void addObserver(StarterBinderObserver obs) {
        this.mTrackCurrent.addObserver(obs);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Track tr = new Track(null);
        this.mTrackCurrent = tr;
        this.mTrackCD = tr;
    }

    @Override
    public void onStart() {
        super.onStart();

        // tracks
        Track tr = new Track(this.mTrackCD);
        this.mTrackCurrent = tr;
        this.mTrackSS = tr;

        // connect service
        this.mTaskBind = () -> {
            Intent i = new Intent(this.mContext, StarterService.class);
            this.initService(i);
            this.bind(i);
        };
    }

    @Override
    public void onResume() {
        super.onResume();
        Track tr = new Track(this.mTrackSS);
        this.mTrackCurrent = tr;
        this.mTrackRP = tr;

        Runnable task = this.mTaskBind;
        this.mTaskBind = null;
        if (task != null) {
            task.run();
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        final Track self = this.mTrackRP;
        this.mTrackCurrent = self.mParent;

        StateEvent evt = new StateEvent();
        evt.holder = this.mBinderHolder;
        evt.end = true;
        StateEventDispatcher.dispatchToTrack(evt, self);
    }

    @Override
    public void onStop() {
        super.onStop();

        final Track self = this.mTrackSS;
        this.mTrackCurrent = self.mParent;

        StateEvent evt = new StateEvent();
        evt.holder = this.mBinderHolder;
        evt.end = true;
        StateEventDispatcher.dispatchToTrack(evt, self);

        this.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        final Track self = this.mTrackCD;
        this.mTrackCurrent = self;

        StateEvent evt = new StateEvent();
        evt.holder = this.mBinderHolder;
        evt.end = true;
        StateEventDispatcher.dispatchToTrack(evt, self);
    }
}
