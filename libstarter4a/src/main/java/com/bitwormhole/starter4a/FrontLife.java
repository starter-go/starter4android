package com.bitwormhole.starter4a;

import android.os.Bundle;
import androidx.annotation.Nullable;

public interface FrontLife {

    void onCreate(@Nullable Bundle savedInstanceState);

    void onStart();

    void onRestart();

    void onPause();

    void onResume();

    void onStop();

    void onDestroy();

}
