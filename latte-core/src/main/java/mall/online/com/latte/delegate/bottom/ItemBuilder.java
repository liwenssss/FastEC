package mall.online.com.latte.delegate.bottom;

import java.util.LinkedHashMap;

/**
 * Created by liWensheng on 2018/2/22.
 * 将底部tab与每个子fragment相关联
 */

public final class ItemBuilder {
    private final LinkedHashMap<BottomTabBean, BottomItemDelagate> ITEMS = new LinkedHashMap<>();

    static ItemBuilder builder() {
        return new ItemBuilder();
    }

    public final ItemBuilder addItem(BottomTabBean bean, BottomItemDelagate delagate) {
        ITEMS.put(bean, delagate);
        return this;
    }

    public final ItemBuilder addItems(LinkedHashMap<BottomTabBean, BottomItemDelagate> items) {
        ITEMS.putAll(items);
        return this;
    }

    public final LinkedHashMap<BottomTabBean, BottomItemDelagate> build() {
        return ITEMS;
    }
}
