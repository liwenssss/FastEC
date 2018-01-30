package mall.online.com.latte.delegate;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import mall.online.com.latte.activities.ProxyActivity;
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
        final View rootView;
        if (setLayout() instanceof  Integer) {
            // 传入layout的id
            // 动态加载布局
            // 无父布局
            // 将xml布局转换成view对象
            rootView = inflater.inflate((int) setLayout(), container, false);
        } else if (setLayout() instanceof View) {
            // 传入View
            // 直接赋值
            rootView = (View) setLayout();
        } else {
            throw new ClassCastException("setLayout() type must be int or view");
        }

        // 绑定视图
        mUnbinder = ButterKnife.bind(this, rootView);
        onBindView(savedInstanceState, rootView);

        return rootView;
    }

    public final ProxyActivity getProxyActivity() {
        return (ProxyActivity) _mActivity;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder != null) { // 解绑
            mUnbinder.unbind();
        }
    }
}
