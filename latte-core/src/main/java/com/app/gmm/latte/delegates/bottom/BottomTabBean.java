package com.app.gmm.latte.delegates.bottom;

/**
 * Created by gmm on 2017/11/4.
 */

public final class BottomTabBean {
    private final CharSequence ICON;

    private final CharSequence TITLE;

    public BottomTabBean(CharSequence icon, CharSequence title) {
        this.ICON = icon;
        this.TITLE = title;
    }

    public CharSequence getIcon() {
        return ICON;
    }

    public CharSequence getTitle() {
        return TITLE;
    }
}
