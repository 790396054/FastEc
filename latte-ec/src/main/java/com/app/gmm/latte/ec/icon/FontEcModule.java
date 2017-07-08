package com.app.gmm.latte.ec.icon;

import com.joanzapata.iconify.Icon;
import com.joanzapata.iconify.IconFontDescriptor;

/**
 * Created by gmm on 2017/7/8.
 */

public class FontEcModule implements IconFontDescriptor {

    @Override
    public String ttfFileName() {
        return "iconfont";
    }

    @Override
    public Icon[] characters() {
        return EcIcons.values();
    }
}
