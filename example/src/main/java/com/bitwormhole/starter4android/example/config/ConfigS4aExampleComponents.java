package com.bitwormhole.starter4android.example.config;

import com.bitwormhole.starter4a.APIRegistry;
import com.bitwormhole.starter4a.settings.SettingRegistry;
import com.bitwormhole.starter4android.example.com.ExampleApi1;
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
            cfg.comExampleAPI(ct);
            cfg.comExampleSetting(ct);
        };
        return fn;
    }

    private void comExampleAPI(ComponentTemplate ct) {
        ComponentTemplate.RegistrationT<ExampleApi1> rt = ct.component(ExampleApi1.class);
        rt.addClass(APIRegistry.class);
        rt.onNew(ExampleApi1::new);
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
