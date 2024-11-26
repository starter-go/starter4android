package com.bitwormhole.starter4android.example.config;

import com.bitwormhole.starter4a.APIRegistry;
import com.bitwormhole.starter4a.settings.SettingRegistry;
import com.bitwormhole.starter4android.example.com.ExampleLocalApi1;
import com.bitwormhole.starter4android.example.com.ExampleRemoteApi1;
import com.bitwormhole.starter4android.example.com.ExampleSetting1;
import com.bitwormhole.starter4j.application.ComponentRegistryFunc;
import com.bitwormhole.starter4j.application.ComponentTemplate;

public final class ConfigS4aExampleComponents {

    private ConfigS4aExampleComponents() {
    }

    public static ComponentRegistryFunc all() {
        final ConfigS4aExampleComponents cfg = new ConfigS4aExampleComponents();
        final ComponentRegistryFunc fn = (cr) -> {
            final ComponentTemplate ct = new ComponentTemplate(cr);
            cfg.comExampleRemoteAPI(ct);
            cfg.comExampleLocalAPI(ct);
            cfg.comExampleSetting(ct);
        };
        return fn;
    }

    private void comExampleLocalAPI(ComponentTemplate ct) {
        ComponentTemplate.RegistrationT<ExampleLocalApi1> rt = ct.component(ExampleLocalApi1.class);
        rt.addClass(APIRegistry.class);
        rt.onNew(ExampleLocalApi1::new);
        rt.onInject((ext, o) -> {
            // o.set
        });
        rt.register();
    }

    private void comExampleRemoteAPI(ComponentTemplate ct) {
        ComponentTemplate.RegistrationT<ExampleRemoteApi1> rt = ct.component(ExampleRemoteApi1.class);
        rt.addClass(APIRegistry.class);
        rt.onNew(ExampleRemoteApi1::new);
        rt.onInject((ext, o) -> {
            // o.set
        });
        rt.register();
    }

    private void comExampleSetting(ComponentTemplate ct) {
        ComponentTemplate.RegistrationT<ExampleSetting1> rt = ct.component(ExampleSetting1.class);
        rt.addClass(SettingRegistry.class);
        rt.onNew(ExampleSetting1::new);
        rt.onInject((ext, o) -> {
            // o.set;
        });
        rt.register();
    }
}
