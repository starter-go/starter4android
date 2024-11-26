package com.bitwormhole.starter4a.config;

import com.bitwormhole.starter4a.APIManager;
import com.bitwormhole.starter4a.APIManagerImpl;
import com.bitwormhole.starter4a.BackLifeManager;
import com.bitwormhole.starter4a.contexts.AppCurrentHolderCom;
import com.bitwormhole.starter4a.contexts.CurrentHolder;
import com.bitwormhole.starter4a.settings.SettingManager;
import com.bitwormhole.starter4a.settings.SettingManagerFacade;
import com.bitwormhole.starter4j.application.ApplicationContext;
import com.bitwormhole.starter4j.application.ComponentRegistry;
import com.bitwormhole.starter4j.application.ComponentRegistryFunc;
import com.bitwormhole.starter4j.application.ComponentTemplate;
import com.bitwormhole.starter4j.application.components.ComponentSelector;
import com.bitwormhole.starter4j.base.StarterException;

public final class ConfigStarter4aComponents implements ComponentRegistryFunc {

    private ConfigStarter4aComponents() {
    }

    public static ComponentRegistryFunc func() {
        return new ConfigStarter4aComponents();
    }

    @Override
    public void invoke(ComponentRegistry cr) throws StarterException {

        ComponentTemplate ct = new ComponentTemplate(cr);

        this.comCurrentHolder(ct);
        this.comBackLifeManager(ct);
        this.comSettingManager(ct);
        this.comAPIManager(ct);
    }

    private void comCurrentHolder(ComponentTemplate ct) {
        ComponentTemplate.RegistrationT<AppCurrentHolderCom> rt = ct.component(AppCurrentHolderCom.class);
        rt.addAlias(CurrentHolder.class);
        rt.onNew(AppCurrentHolderCom::new);
        rt.onInject((ext, com) -> {
            com.setAc(ext.getContext());
        });
        rt.register();
    }

    private void comBackLifeManager(ComponentTemplate ct) {
        ComponentSelector cs = ComponentSelector.getInstance();
        ComponentTemplate.RegistrationT<BackLifeManager> rt = ct.component(BackLifeManager.class);
        // rt.addAlias(BackLifeManager.class);
        rt.onNew(BackLifeManager::new);
        rt.onInject((ext, com) -> {
            CurrentHolder holder = (CurrentHolder) ext.getComponent(cs.ID(CurrentHolder.class));
            com.setCurrentHolder(holder);
            com.setContext(ext.getContext());
        });
        rt.register();
    }

    private void comAPIManager(ComponentTemplate ct) {
        ComponentTemplate.RegistrationT<APIManagerImpl> rt = ct.component(APIManagerImpl.class);
        rt.addAlias(APIManager.class);
        rt.onNew(APIManagerImpl::new);
        rt.onInject((ext, o) -> {
            o.setContext(ext.getContext());
        });
        rt.register();
    }

    private void comSettingManager(ComponentTemplate ct) {
        final ComponentSelector cs = ComponentSelector.getInstance();
        final ComponentTemplate.RegistrationT<SettingManagerFacade> rt = ct.component(SettingManagerFacade.class);
        rt.addAlias(SettingManager.class);
        rt.onNew(SettingManagerFacade::new);
        rt.onInject((ext, o) -> {
            CurrentHolder ch = (CurrentHolder) ext.getComponent(cs.ID(CurrentHolder.class));
            ApplicationContext ctx = ext.getContext();
            o.init(ctx, ch);
        });
        rt.register();
    }
}
