package com.bitwormhole.starter4a.contexts;

import com.bitwormhole.starter4j.base.SafeMode;

public interface ScopeContextFactory {

    Framework createFramework(Current current, SafeMode mode);

    App createApp(Current current);

    User createUser(Current current, String name);

    Document createDocument(Current current, String name);

    Project createProject(Current current, String name);

}
