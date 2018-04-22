package mall.online.com.latte.ec.main.sort;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import mall.online.com.latte.delegate.bottom.BottomItemDelagate;
import mall.online.com.latte.ec.R;
import mall.online.com.latte.ec.main.sort.content.ContentDelegate;
import mall.online.com.latte.ec.main.sort.list.VerticalListDelegate;

/**
 * Created by liWensheng on 2018/2/22.
 */

public class SortDelegate extends BottomItemDelagate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_sort;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        final VerticalListDelegate listDelegate = new VerticalListDelegate();
        getSupportDelegate().loadRootFragment(R.id.vertical_list_container, listDelegate);
        // 设置右侧默认显示第一个分类
        getSupportDelegate().loadRootFragment(R.id.sort_content_container, ContentDelegate.newInstance(1));
    }

}
