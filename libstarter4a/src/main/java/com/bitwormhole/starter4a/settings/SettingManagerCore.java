package com.bitwormhole.starter4a.settings;

import com.bitwormhole.starter4a.DataScope;
import com.bitwormhole.starter4a.contexts.Current;
import com.bitwormhole.starter4a.contexts.CurrentHolder;
import com.bitwormhole.starter4a.contexts.ScopeContext;
import com.bitwormhole.starter4j.application.ApplicationContext;
import com.bitwormhole.starter4j.application.Life;
import com.bitwormhole.starter4j.application.LifeCycle;
import com.bitwormhole.starter4j.base.StarterException;
import com.google.gson.Gson;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

final class SettingManagerCore {

    final static Logger logger = LoggerFactory.getLogger(SettingManagerCore.class);

    private ApplicationContext context;
    private CurrentHolder currentHolder;
    private final Map<String, MyItem> table;

    public SettingManagerCore() {
        Map<String, MyItem> t = new HashMap<>();
        this.table = Collections.synchronizedMap(t);
    }

    public ApplicationContext getContext() {
        return context;
    }

    public void setContext(ApplicationContext context) {
        this.context = context;
    }

    public CurrentHolder getCurrentHolder() {
        return currentHolder;
    }

    public void setCurrentHolder(CurrentHolder currentHolder) {
        this.currentHolder = currentHolder;
    }

    public Current getCurrent(Current c) {
        if (c == null) {
            c = this.currentHolder.getCurrent();
        }
        return c;
    }


    public void load(SettingHolder sh) throws IOException {

        MyLocation location = new MyLocation(sh);
        location.readonly = true;
        location.item = findItem(sh.getType(), false);
        MyLocator.locate(location);

        byte[] data = Files.readAllBytes(location.file);
        String text = new String(data, StandardCharsets.UTF_8);
        Gson gs = new Gson();
        Class<? extends Setting> t = sh.getType();
        Setting obj = gs.fromJson(text, t);
        sh.setSetting(obj);
    }


    public void store(SettingHolder sh) throws IOException {

        MyLocation location = new MyLocation(sh);
        location.readonly = false;
        location.item = findItem(sh.getType(), false);
        MyLocator.locate(location);

        Gson gs = new Gson();
        String text = gs.toJson(sh.getSetting());
        byte[] data = text.getBytes(StandardCharsets.UTF_8);
        OpenOption oo;
        Path file = location.file;
        Path dir = file.getParent();
        if (!Files.exists(dir)) {
            Files.createDirectories(dir);
        }
        if (Files.exists(file)) {
            oo = StandardOpenOption.TRUNCATE_EXISTING;
        } else {
            oo = StandardOpenOption.CREATE_NEW;
        }
        Files.write(file, data, oo);
    }

    private MyItem findItem(Class<? extends Setting> t, boolean create) {
        String key = t.getName();
        MyItem item = this.table.get(key);
        if (item == null && create) {
            item = new MyItem(t);
            table.put(key, item);
        }
        return item;
    }


    private static class MyItem {
        final Class<? extends Setting> type;

        boolean hasScopeDocument;
        boolean hasScopeProject;
        boolean hasScopeUser;
        boolean hasScopeApp;
        boolean hasScopeFramework;

        MyItem(Class<? extends Setting> t) {
            this.type = t;
        }

        public boolean hasScope(DataScope scope) {
            if (scope == null) {
                return false;
            }
            switch (scope) {
                case APP:
                    return this.hasScopeApp;
                case USER:
                    return this.hasScopeUser;
                case DOCUMENT:
                    return this.hasScopeDocument;
                case PROJECT:
                    return this.hasScopeProject;
                case FRAMEWORK:
                    return this.hasScopeFramework;
            }
            return false;
        }
    }

    private static class MyLocation extends SettingHolder {

        final SettingHolder parent;
        Path file;
        MyItem item;
        boolean readonly; // R/W

        public MyLocation(SettingHolder p) {
            super(p);
            this.parent = p;
        }

        boolean exists() {
            Path f = file;
            if (f == null) {
                return false;
            }
            return Files.exists(f);
        }
    }

    private static class MyLocator {

        static void locate(MyLocation location) {
            if (location.readonly) {
                locate4r(location);
            } else {
                locate4w(location);
            }
            if (location.file == null) {
                String coo = stringifyCoordinate(location);
                throw new StarterException("cannot locate setting file for " + coo);
            }
        }


        static void locate4r(MyLocation location) {
            MyLocationFilter f = MyLocation::exists;
            locateAuto(location, f);
        }

        static void locate4w(MyLocation location) {
            MyLocationFilter f = (l) -> {
                return true;
            };
            locateAuto(location, f);
        }

        static String stringifyCoordinate(SettingHolder sh) {
            if (sh == null) {
                return "null";
            }
            Class<? extends Setting> type = sh.getType();
            DataScope scope = sh.getScope();
            StringBuilder b = new StringBuilder();
            b.append('[');
            b.append("Setting");
            if (scope != null) {
                b.append(" scope:").append(scope);
            }
            if (type != null) {
                b.append(" type:").append(type.getName());
            }
            b.append(']');
            return b.toString();
        }


        static void locateAuto(MyLocation location, MyLocationFilter filter) {
            DataScope scope1 = location.getScope();
            if (scope1 != null && scope1 != DataScope.AUTO) {
                location.setScope(scope1);
                location.file = locateWithScope(location, scope1);
                return;
            }
            DataScope[] list = {DataScope.PROJECT, DataScope.USER, DataScope.APP, DataScope.FRAMEWORK};
            MyItem item = location.item;
            for (DataScope scope2 : list) {
                if (!item.hasScope(scope2)) {
                    continue;
                }
                location.setScope(scope2);
                location.file = locateWithScope(location, scope2);
                if (location.file == null) {
                    continue;
                }
                if (filter.accept(location)) {
                    return;
                }
            }
            location.file = null;
        }


        static Path locateWithScope(MyLocation location, DataScope scope) {
            Current current = location.getCurrent();
            Class<? extends Setting> type = location.getType();
            switch (scope) {
                case PROJECT:
                    return getSettingsFile(current.getProject(), type, scope);
                case USER:
                    return getSettingsFile(current.getUser(), type, scope);
                case APP:
                    return getSettingsFile(current.getApp(), type, scope);
                case FRAMEWORK:
                    return getSettingsFile(current.getFramework(), type, scope);
                case DOCUMENT:
                default:
            }
            return null;
        }

        static Path getSettingsFile(ScopeContext ctx, Class<? extends Setting> type, DataScope scope) {
            if (ctx == null) {
                return null;
            }
            Path dir = ctx.getFolder();
            if (dir == null) {
                return null;
            }
            return dir.resolve(".settings/" + type.getName());
        }
    }

    private interface MyLocationFilter {
        boolean accept(MyLocation l);
    }


    private void putPrototype(SettingRegistration sr) {
        if (sr == null) {
            return;
        }
        Class<? extends Setting> t = sr.getType();
        DataScope scope = sr.getScope();
        if (t == null || scope == null) {
            return;
        }
        MyItem item = this.findItem(t, true);
        switch (scope) {
            case APP:
                item.hasScopeApp = true;
                break;
            case DOCUMENT:
                item.hasScopeDocument = true;
                break;
            case FRAMEWORK:
                item.hasScopeFramework = true;
                break;
            case PROJECT:
                item.hasScopeProject = true;
                break;
            case USER:
                item.hasScopeUser = true;
                break;
        }
    }

    private void reload() {
        final String clazz = SettingRegistry.class.getName();
        final List<Object> list = this.context.selectComponents("." + clazz, null);
        for (Object item : list) {
            final SettingRegistry r1 = (SettingRegistry) item;
            SettingRegistration[] r2list = r1.listSettingRegistrations();
            for (SettingRegistration r2 : r2list) {
                logger.warn("todo: handle setting-reg for: {}", r2.getType());
                putPrototype(r2);
            }
        }
    }

    public Life life() {
        Life l = new Life();
        l.onCreate = this::reload;
        return l;
    }
}
