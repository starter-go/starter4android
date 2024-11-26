package com.bitwormhole.starter4android.example.com;

import com.bitwormhole.starter4a.APIRegistration;
import com.bitwormhole.starter4a.APIRegistry;
import com.bitwormhole.starter4a.LocalAPI;
import com.bitwormhole.starter4a.RemoteAPI;
import com.bitwormhole.starter4j.base.StarterException;

public class ExampleRemoteApi1 implements APIRegistry, RemoteAPI {

    @Override
    public APIRegistration[] listAPIs() {

        APIRegistration r1 = new APIRegistration();
        r1.setRemote(this);
        r1.setType(ExampleRemoteApi1.class);
        r1.setName("/api/remote1");

        return new APIRegistration[]{r1};
    }


    public String hello(String word) {
        return "hello, " + word + "! from " + this;
    }

    public void bad(long now) {
        throw new StarterException(this + ": a example exception with timestamp:" + now);
    }
}
