package mall.online.com.latte.ec.main.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import mall.online.com.latte.delegate.bottom.BottomItemDelagate;
import mall.online.com.latte.ec.R;

/**
 * Created by liWensheng on 2018/2/22.
 */

public class IndexDelegate extends BottomItemDelagate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
