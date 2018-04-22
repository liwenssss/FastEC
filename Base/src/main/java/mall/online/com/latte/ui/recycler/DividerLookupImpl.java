package mall.online.com.latte.ui.recycler;

import com.choices.divider.Divider;
import com.choices.divider.DividerItemDecoration;

/**
 * Created by liWensheng on 2018/2/25.
 * 分割线实现
 */

public class DividerLookupImpl implements DividerItemDecoration.DividerLookup {

    private final int COLOR;
    private final int SIZE;

    public DividerLookupImpl(int color, int size) {
        COLOR = color;
        SIZE = size;
    }

    @Override
    public Divider getVerticalDivider(int position) {
        return new Divider.Builder()
                .size(SIZE)
                .color(COLOR)
                .build();
    }

    @Override
    public Divider getHorizontalDivider(int position) {
        return new Divider.Builder()
                .size(SIZE)
                .color(COLOR)
                .build();
    }
}
