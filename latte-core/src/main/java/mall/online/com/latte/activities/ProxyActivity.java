package mall.online.com.latte.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;

import mall.online.com.latte.R;
import mall.online.com.latte.delegate.LatteDelegate;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by liWensheng on 2018/1/22.
 * 容器类
 * 传入根Activity
 */

public abstract class ProxyActivity extends SupportActivity{


    /**
     * set root delegate
     * @return
     */
    public abstract LatteDelegate setRootDelegate();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContainer(savedInstanceState);
    }

    /**
     * 初始化容器
     * @param savedInstanceState
     */
    private void initContainer(@Nullable Bundle savedInstanceState) {
        final ContentFrameLayout container = new ContentFrameLayout(this);
        container.setId(R.id.delegate_container);
        setContentView(container);
        if (savedInstanceState == null) {
            // 如果第一次加载
            loadRootFragment(R.id.delegate_container, setRootDelegate());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 垃圾回收
        System.gc();
        System.runFinalization();
    }
}
