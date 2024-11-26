package com.bitwormhole.starter4a;


import com.bitwormhole.starter4j.application.ApplicationContext;
import com.bitwormhole.starter4j.application.Life;
import com.bitwormhole.starter4j.application.LifeCycle;
import com.bitwormhole.starter4j.base.StarterException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class APIManagerImpl implements APIManager, LifeCycle {

    final static Logger logger = LoggerFactory.getLogger(APIManagerImpl.class);

    private ApplicationContext context;

    private final Map<String, MyItem> table;

    public APIManagerImpl() {
        Map<String, MyItem> t = new HashMap<>();
        this.table = Collections.synchronizedMap(t);
    }


    private static class MyItem {
        String name;
        Class<?> type;
        LocalAPI local;
        RemoteAPI remote;

        public APIRegistration toAPIRegistration(APIRegistration out) {
            if (out == null) {
                out = new APIRegistration();
            }
            out.setName(name);
            out.setType(type);
            out.setRemote(remote);
            out.setLocal(local);
            return out;
        }

        public void setWith(APIRegistration src) {
            if (src == null) {
                return;
            }
            this.name = src.getName();
            this.type = src.getType();
            this.local = src.getLocal();
            this.remote = src.getRemote();
        }
    }

    private static String keyFor(String id, Class<?> t) {
        if (id != null) {
            return "id:" + id;
        }
        if (t != null) {
            return "class:" + t.getName();
        }
        return null;
    }


    /****
     * @param kind : (local|remote|na)
     * */
    private static StarterException makeNoApiException(String name, Class<?> t, String kind) {
        StringBuilder msg = new StringBuilder();
        msg.append("no api ").append('[');
        if (kind != null) {
            msg.append(kind);
        }
        if (name != null) {
            msg.append(" name:").append(name);
        }
        if (t != null) {
            msg.append(" type:").append(t.getName());
        }
        msg.append(']');
        return new StarterException(msg.toString());
    }


    public ApplicationContext getContext() {
        return context;
    }

    public void setContext(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public APIRegistration[] listAPIs() {
        List<APIRegistration> dst = new ArrayList<>();
        List<String> keys = new ArrayList<>(table.keySet());
        Set<String> have = new HashSet<>();
        Collections.sort(keys);
        for (String key : keys) {
            MyItem item = table.get(key);
            if (item == null) {
                continue;
            }
            String key2 = keyFor(null, item.type);
            if (have.contains(key2)) {
                continue;
            }
            have.add(key2);
            dst.add(item.toAPIRegistration(null));
        }
        return dst.toArray(new APIRegistration[0]);
    }

    @Override
    public APIRegistration getAPI(String name, APIRegistration out) {
        final String key = keyFor(name, null);
        final MyItem item = this.table.get(key);
        if (item == null) {
            throw makeNoApiException(name, null, "");
        }
        if (item.remote == null && item.local == null) {
            throw makeNoApiException(name, null, "");
        }
        return item.toAPIRegistration(out);
    }

    @Override
    public APIRegistration getAPI(Class<?> t, APIRegistration out) {
        final String key = keyFor(null, t);
        final MyItem item = this.table.get(key);
        if (item == null) {
            throw makeNoApiException(null, t, "");
        }
        if (item.remote == null && item.local == null) {
            throw makeNoApiException(null, t, "");
        }
        return item.toAPIRegistration(out);
    }


    /**
     * @noinspection unchecked
     */
    @Override
    public <T extends RemoteAPI> T findRemoteAPI(String name, Class<T> t) {
        final String key = keyFor(name, t);
        final MyItem item = this.table.get(key);
        if (item == null) {
            throw makeNoApiException(name, t, "remote");
        }
        Object remote = item.remote;
        if (remote == null) {
            throw makeNoApiException(name, t, "remote");
        }
        return (T) remote;
    }

    /**
     * @noinspection unchecked
     */
    @Override
    public <T extends LocalAPI> T findLocalAPI(String name, Class<T> t) {
        final String key = keyFor(name, t);
        final MyItem item = this.table.get(key);
        if (item == null) {
            throw makeNoApiException(name, t, "local");
        }
        T l = (T) item.local;
        if (l == null) {
            throw makeNoApiException(name, t, "local");
        }
        return l;
    }

    private void put(APIRegistration r) {
        if (r == null) {
            return;
        }
        final MyItem item = new MyItem();
        item.setWith(r);
        final String key1 = keyFor(item.name, null);
        final String key2 = keyFor(null, item.type);
        final String[] keys = {key1, key2};
        for (String key : keys) {
            if (key == null) {
                continue;
            }
            MyItem older = table.get(key);
            if (older != null) {
                throw new StarterException("api(s) with key are duplicated, key=" + key);
            }
            this.table.put(key, item);
        }
    }


    private void reload() {
        this.table.clear();
        final String clazz = APIRegistry.class.getName();
        final List<Object> list = this.context.selectComponents("." + clazz, null);
        for (Object item : list) {
            final APIRegistry r1 = (APIRegistry) item;
            APIRegistration[] r2list = r1.listAPIs();
            for (APIRegistration r2 : r2list) {
                logger.warn("todo: handle api-reg for: {}", r2.getType());
                this.put(r2);
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
