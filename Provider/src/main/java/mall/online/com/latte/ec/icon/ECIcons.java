package mall.online.com.latte.ec.icon;

import com.joanzapata.iconify.Icon;

/**
 * Created by liWensheng on 2018/1/20.
 */

public enum ECIcons implements Icon {
    icon_scan('\ue623'),
    icon_pay('\ue64b')
    ;

    private char character;

    ECIcons(char character) {
        this.character = character;
    }

    @Override
    public String key() {
        return name().replace('_', '-');
    }

    @Override
    public char character() {
        return character;
    }
}
