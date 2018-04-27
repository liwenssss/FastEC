package mall.online.com.latte.ec.main.index.bar.Mall;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import mall.online.com.latte.delegate.LatteDelegate;
import mall.online.com.latte.ec.R;

/**
 * Created by liWensheng on 2018/4/19.
 */

public class MallDelegate extends LatteDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_mall;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

    }
}
