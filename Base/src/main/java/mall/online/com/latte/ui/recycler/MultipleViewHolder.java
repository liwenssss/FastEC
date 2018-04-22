package mall.online.com.latte.ui.recycler;

import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Created by liWensheng on 2018/2/24.
 */

public class MultipleViewHolder extends BaseViewHolder {

    public MultipleViewHolder(View view) {
        super(view);
    }

    public static MultipleViewHolder create(View view) {
        return new MultipleViewHolder(view);
    }
}
