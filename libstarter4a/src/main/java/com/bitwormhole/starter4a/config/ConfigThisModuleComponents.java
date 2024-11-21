package com.bitwormhole.starter4a.config;

import com.bitwormhole.starter4a.BackLifeManager;
import com.bitwormhole.starter4a.contexts.AppCurrentHolderCom;
import com.bitwormhole.starter4a.contexts.CurrentHolder;
import com.bitwormhole.starter4j.application.ComponentRegistry;
import com.bitwormhole.starter4j.application.ComponentRegistryFunc;
import com.bitwormhole.starter4j.application.ComponentTemplate;
import com.bitwormhole.starter4j.application.components.ComponentSelector;
import com.bitwormhole.starter4j.base.StarterException;

public final class ConfigThisModuleComponents implements ComponentRegistryFunc {

    private ConfigThisModuleComponents() {
    }

    public static ComponentRegistryFunc func() {
        return new ConfigThisModuleComponents();
    }

    @Override
    public void invoke(ComponentRegistry cr) throws StarterException {
        this.comCurrentHolder(cr);
        this.comBackLifeManager(cr);
    }

    private void comCurrentHolder(final ComponentRegistry cr1) {
        final String id = CurrentHolder.class.getName();
        ComponentTemplate ct = new ComponentTemplate(cr1);
        ComponentTemplate.RegistrationT<AppCurrentHolderCom> rt = ct.component(AppCurrentHolderCom.class);
        rt.setId(id);
        rt.onNew(() -> {
            return new AppCurrentHolderCom();
        });
        rt.onInject((ext, com) -> {
            com.setAc(ext.getContext());
        });
        rt.register();
    }

    private void comBackLifeManager(ComponentRegistry cr) {
        final String id = BackLifeManager.class.getName();
        ComponentSelector cs = ComponentSelector.getInstance();
        ComponentTemplate ct = new ComponentTemplate(cr);
        ComponentTemplate.RegistrationT<BackLifeManager> rt = ct.component(BackLifeManager.class);
        rt.setId(id);
        rt.onNew(() -> {
            return new BackLifeManager();
        });
        rt.onInject((ext, com) -> {
            CurrentHolder holder = (CurrentHolder) ext.getComponent(cs.ID(CurrentHolder.class));
            com.setCurrentHolder(holder);
            com.setContext(ext.getContext());
        });
        rt.register();
    }
}
