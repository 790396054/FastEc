package com.app.gmm.fastec.generators;

import com.app.gmm.latte.wechat.templates.WXPayEntryTemplate;
import com.gmm.latte.PayEntryGenerator;

/**
 * Created by gmm on 2017/11/2.
 */
@PayEntryGenerator(
        packageName = "com.app.gmm.fastec",
        payEntryTemplate = WXPayEntryTemplate.class
)
public interface WeChatPayEntry {
}
