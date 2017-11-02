package com.app.gmm.fastec.generators;

import com.app.gmm.latte.wechat.templates.WXEntryTemplate;
import com.gmm.latte.EntryGenerator;

/**
 * Created by gmm on 2017/11/2.
 */
@EntryGenerator(
        packageName = "com.app.gmm.fastec",
        entryTemplate = WXEntryTemplate.class
)
public interface WeChatEntry {
}
