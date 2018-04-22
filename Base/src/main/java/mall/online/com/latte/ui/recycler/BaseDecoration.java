package mall.online.com.latte.ui.recycler;

import com.choices.divider.DividerItemDecoration;

/**
 * Created by liWensheng on 2018/2/25.
 * 分割线
 */

public class BaseDecoration extends DividerItemDecoration {
    public BaseDecoration(int color, int size) {
        setDividerLookup(new DividerLookupImpl(color, size));
    }

    public static BaseDecoration create(int color, int size) {
        return new BaseDecoration(color, size);
    }
}
