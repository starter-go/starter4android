package com.bitwormhole.starter4android.example.com;

import com.bitwormhole.starter4a.APIRegistration;
import com.bitwormhole.starter4a.APIRegistry;
import com.bitwormhole.starter4a.LocalAPI;
import com.bitwormhole.starter4a.RemoteAPI;

public class ExampleApi1 implements APIRegistry {

    @Override
    public APIRegistration[] listAPIs() {

        APIRegistration r1 = new APIRegistration();
        r1.setRemote(new MyRemoteAPI());
        r1.setType(MyRemoteAPI.class);
        r1.setName("/api/remote1");

        APIRegistration r2 = new APIRegistration();
        r2.setLocal(new MyLocalAPI());
        r2.setType(MyLocalAPI.class);
        r2.setName("/api/local1");

        return new APIRegistration[]{r1, r2};
    }

    private static class MyLocalAPI implements LocalAPI {
    }

    private static class MyRemoteAPI implements RemoteAPI {
    }

}
