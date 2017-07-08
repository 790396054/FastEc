package com.app.gmm.fastec;

import com.app.gmm.latte.activities.ProxyActivity;
import com.app.gmm.latte.delegates.LatteDelegate;

public class ExampleActivity extends ProxyActivity {

    @Override
    public LatteDelegate setRootDelegate() {
        return new ExampleDelegate();
    }
}
