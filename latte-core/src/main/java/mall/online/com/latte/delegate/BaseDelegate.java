package mall.online.com.latte.delegate;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;

/**
 * Created by liWensheng on 2018/1/22.
 */

public abstract class BaseDelegate extends SwipeBackFragment {


    @SuppressWarnings("SpellCheckingInspection")
    private Unbinder mUnbinder = null;

    /**
     * set layout
     */
    public abstract Object setLayout();

    /**
     * bind view
     */
    public abstract void onBindView(@Nullable Bundle savedInstanceState, View rootView);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = null;
        if (setLayout() instanceof  Integer) {
            // 传入layout的id
            // 动态加载布局
            // 无父布局
            // 将xml布局转换成view对象
            rootView = inflater.inflate((Integer) setLayout(), container, false);
        } else if (setLayout() instanceof View) {
            // 传入View
            // 直接赋值
            rootView = (View) setLayout();
        }
        if (rootView != null) {
            // 绑定视图
            mUnbinder = ButterKnife.bind(this, rootView);
            onBindView(savedInstanceState, rootView);
        }

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder != null) { // 解绑
            mUnbinder.unbind();
        }
    }
}
