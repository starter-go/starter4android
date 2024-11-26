package com.bitwormhole.starter4a.contexts;

import com.bitwormhole.starter4a.users.LocalUserInfo;
import com.bitwormhole.starter4j.base.SafeMode;
import com.bitwormhole.starter4j.base.StarterException;

import java.io.File;
import java.nio.file.Path;

final class ScopeContextFactoryImpl implements ScopeContextFactory {


    public ScopeContextFactoryImpl() {
    }


    private Path getAppBaseDir(Current current) {
        File dir = current.getContext().getFilesDir();
        if (dir.isDirectory()) {
            return dir.toPath();
        }
        throw new StarterException("app_files_dir is NOT a directory: " + dir.getPath());
    }


    @Override
    public Framework createFramework(Current current, SafeMode mode) {
        if (mode == null) {
            mode = SafeMode.Safe;
        }
        Path parentDir = this.getAppBaseDir(current);
        Framework self = new Framework(mode);
        self.setFolder(parentDir.resolve(".starter"));
        self.setKeyPair(null); // NA
        self.setSecretKey(null); // NA
        return self;
    }

    @Override
    public App createApp(Current current) {

        Framework parent = current.getFramework();
        SafeMode mode = parent.getAttributes().mode();
        Path parentDir = this.getAppBaseDir(current);

        App self = new App(parent, mode);
        self.setFolder(parentDir.resolve(".app"));
        self.setKeyPair(null); // load app kp
        self.setSecretKey(null); // load app sk
        return self;
    }

    @Override
    public User createUser(Current current, String name) {

        LocalUserInfo info = new LocalUserInfo();
        info.setName(name);

        App parent = current.getApp();
        SafeMode mode = parent.getAttributes().mode();
        Path parentDir = this.getAppBaseDir(current);

        User self = new User(parent, mode);
        self.setLocalUserInfo(info);
        self.setFolder(parentDir.resolve("home/" + name));
        self.setKeyPair(null); // load user kp
        self.setSecretKey(null); // load user sk
        return self;
    }

    @Override
    public Document createDocument(Current current, String name) {
        Project parent = current.getProject();
        SafeMode mode = parent.getAttributes().mode();
        Document self = new Document(parent, mode);
        Path parentDir = parent.getFolder();
        self.setName(name);
        self.setFolder(parentDir.resolve("docs/" + name));
        self.setKeyPair(parent.getKeyPair());
        self.setSecretKey(parent.getSecretKey());
        return self;
    }

    @Override
    public Project createProject(Current current, String name) {
        User parent = current.getUser();
        SafeMode mode = parent.getAttributes().mode();
        Project self = new Project(parent, mode);
        Path parentDir = parent.getFolder();
        self.setName(name);
        self.setFolder(parentDir.resolve("projects/" + name));
        self.setKeyPair(parent.getKeyPair());
        self.setSecretKey(parent.getSecretKey());
        return self;
    }
}
