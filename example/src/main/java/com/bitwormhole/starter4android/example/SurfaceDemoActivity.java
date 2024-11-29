package com.bitwormhole.starter4android.example;

import android.graphics.Color;
import android.os.Bundle;
import android.view.SurfaceView;

import androidx.annotation.Nullable;

import com.bitwormhole.starter4a.FrontLifeManager;
import com.bitwormhole.starter4a.StarterActivity;
import com.bitwormhole.starter4a.ui.boxes.B2Align;
import com.bitwormhole.starter4a.ui.boxes.B2Container;
import com.bitwormhole.starter4a.ui.boxes.B2State;
import com.bitwormhole.starter4a.ui.boxes.B2Style;
import com.bitwormhole.starter4a.ui.boxes.B2StyleBuilder;
import com.bitwormhole.starter4a.ui.surfaces.SurfaceContext;
import com.bitwormhole.starter4a.ui.surfaces.SurfaceContextBuilder;
import com.bitwormhole.starter4a.ui.elements.B2Button;
import com.bitwormhole.starter4a.ui.layouts.SimpleLayout;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SurfaceDemoActivity extends StarterActivity {

    final static Logger logger = LoggerFactory.getLogger(SurfaceDemoActivity.class);

    private SurfaceView mSurfaceView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_surface_demo);

        mSurfaceView = findViewById(R.id.surface_view1);

        FrontLifeManager lm = this.getFrontContext().getLifeManager();

        SurfaceContextBuilder builder = new SurfaceContextBuilder();
        SurfaceContext sc = builder.getSurfaceContext();
        sc.setParent(this);
        sc.setView(mSurfaceView);
        sc.setView2(new MyUI());
        sc.setLifeManager(lm);

        sc = builder.create();
        lm.add(sc.getLife());
    }

    private static class MyUI extends B2Container {

        public MyUI() {
            this.init();
        }

        public void init() {

            B2StyleBuilder sb = new B2StyleBuilder();

            sb.setState(B2State.NORMAL);
            sb.putColor(B2Style.background_color, Color.RED);
            sb.putColor(B2Style.color, Color.GREEN);
            sb.putColor(B2Style.text_color, Color.BLUE);
            sb.putSize(B2Style.font_size, 38);
            sb.putAlign(B2Style.align, B2Align.CENTER);

            sb.setState(B2State.PRESSED);
            sb.putColor(B2Style.background_color, Color.rgb(200, 0, 0));
            sb.putColor(B2Style.text_color, Color.rgb(0, 0, 200));
            sb.putSize(B2Style.font_size, 33);


            B2Style style = sb.create();
            B2Button btn = new B2Button();
            btn.setText("hello,btn");
            btn.setStyle(style);
            this.add(btn);
            this.setLayout(new SimpleLayout());
        }
    }
}
