package com.bitwormhole.starter4android.example.com;

import com.bitwormhole.starter4a.APIRegistration;
import com.bitwormhole.starter4a.APIRegistry;
import com.bitwormhole.starter4a.LocalAPI;
import com.bitwormhole.starter4a.RemoteAPI;
import com.bitwormhole.starter4j.base.StarterException;

public class ExampleLocalApi1 implements APIRegistry, LocalAPI {

    @Override
    public APIRegistration[] listAPIs() {

        APIRegistration r2 = new APIRegistration();
        r2.setLocal(this);
        r2.setType(ExampleLocalApi1.class);
        r2.setName("/api/local1");

        return new APIRegistration[]{r2};
    }

    public String hello(String word) {
        return "hello, " + word + "! from " + this;
    }

    public void bad(long now) {
        throw new StarterException(this + ": a example exception with timestamp:" + now);
    }
}
