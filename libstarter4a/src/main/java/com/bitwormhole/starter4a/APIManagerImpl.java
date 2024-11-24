package com.bitwormhole.starter4a;

import com.bitwormhole.starter4a.settings.SettingManagerImpl;
import com.bitwormhole.starter4a.settings.SettingRegistration;
import com.bitwormhole.starter4a.settings.SettingRegistry;
import com.bitwormhole.starter4j.application.ApplicationContext;
import com.bitwormhole.starter4j.application.Life;
import com.bitwormhole.starter4j.application.LifeCycle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class APIManagerImpl implements APIManager, LifeCycle {

    final static Logger logger = LoggerFactory.getLogger(SettingManagerImpl.class);

    private ApplicationContext context;

    public APIManagerImpl() {
    }

    public ApplicationContext getContext() {
        return context;
    }

    public void setContext(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public APIRegistration[] listAPIs() {
        return new APIRegistration[0];
    }

    @Override
    public APIRegistration getAPI(String name, APIRegistration out) {
        return null;
    }

    @Override
    public APIRegistration getAPI(Class<?> t, APIRegistration out) {
        return null;
    }

    @Override
    public <T extends RemoteAPI> T findRemoteAPI(String name, Class<T> t) {
        return null;
    }

    @Override
    public <T extends LocalAPI> T findLocalAPI(String name, Class<T> t) {
        return null;
    }


    private void reload() {
        final String clazz = APIRegistry.class.getName();
        final List<Object> list = this.context.selectComponents("." + clazz, null);
        for (Object item : list) {
            final APIRegistry r1 = (APIRegistry) item;
            APIRegistration[] r2list = r1.listAPIs();
            for (APIRegistration r2 : r2list) {
                logger.warn("todo: handle api-reg for: {}", r2.getType());
            }
        }
    }

    @Override
    public Life life() {
        Life l = new Life();
        l.onCreate = this::reload;
        return l;
    }
}
