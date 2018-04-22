package mall.online.com.latte.ui.recycler;

import java.util.LinkedHashMap;
import java.util.WeakHashMap;

/**
 * Created by liWensheng on 2018/2/23.
 */

public class MultipleItemEntityBuilder {
    private static final LinkedHashMap<Object, Object> FIELDS = new LinkedHashMap<>();

    public MultipleItemEntityBuilder() {
        FIELDS.clear();
    }

    public final MultipleItemEntityBuilder setItemType(int type) {
        FIELDS.put(MultipleFields.ITEM_TYPE, type);
        return this;
    }

    public final MultipleItemEntityBuilder setField(Object key, Object value) {
        FIELDS.put(key, value);
        return this;
    }

    public final MultipleItemEntityBuilder setFields(WeakHashMap<?, ?> map) {
        FIELDS.putAll(map);
        return this;
    }

    public final MultipleItemEntity build() {
        return new MultipleItemEntity(FIELDS);
    }
}
