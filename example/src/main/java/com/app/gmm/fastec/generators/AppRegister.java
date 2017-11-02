package com.app.gmm.fastec.generators;

import com.app.gmm.latte.wechat.templates.AppRegisterTemplate;
import com.gmm.latte.AppRegisterGenerator;

/**
 * Created by gmm on 2017/11/2.
 */
@AppRegisterGenerator(
        packageName = "com.app.gmm.fastec",
        registerTemplate = AppRegisterTemplate.class
)
public interface AppRegister {
}
