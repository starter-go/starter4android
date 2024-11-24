package com.bitwormhole.starter4a;

public interface APIManager {

    APIRegistration[] listAPIs();

    APIRegistration getAPI(String name, APIRegistration out);

    APIRegistration getAPI(Class<?> t, APIRegistration out);

    <T extends RemoteAPI> T findRemoteAPI(String name, Class<T> t);

    <T extends LocalAPI> T findLocalAPI(String name, Class<T> t);

}
