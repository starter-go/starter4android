package com.bitwormhole.starter4a;


import android.content.Context;

import com.bitwormhole.starter4a.contexts.Customizer;
import com.bitwormhole.starter4j.Initializer;
import com.bitwormhole.starter4j.application.properties.Properties;

import java.io.File;


public class StarterApplicationCustomizer implements Customizer {

    private final Context context;

    public StarterApplicationCustomizer(Context ctx) {
        this.context = ctx;
    }

    private static File getStopperControlFile(Context ctx) {
        File dir = ctx.getFilesDir();
        return new File(dir, ".starter/stopper.control");
    }

    @Override
    public void customize(Initializer i) {

        File ctrl_file = getStopperControlFile(this.context);

        Properties props = i.getProperties();
        props.setProperty("starter.stopper.action", "start");
        props.setProperty("starter.stopper.control.file", ctrl_file.getAbsolutePath());
    }
}
