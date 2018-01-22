package mall.online.com.fastec.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import mall.online.com.latte.delegate.LatteDelegate;

/**
 * Created by liWensheng on 2018/1/22.
 */

public class MallDelegate extends LatteDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
