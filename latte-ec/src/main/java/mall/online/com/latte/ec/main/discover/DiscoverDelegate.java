package mall.online.com.latte.ec.main.discover;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import mall.online.com.latte.delegate.bottom.BottomItemDelagate;
import mall.online.com.latte.delegate.web.WebDelegateImpl;
import mall.online.com.latte.ec.R;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * Created by liWensheng on 2018/2/26.
 */

public class DiscoverDelegate extends BottomItemDelagate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_discover;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        final WebDelegateImpl delegate = WebDelegateImpl.create("index.html");
        delegate.setTopDelegate(this.getParentDelegate());
        loadRootFragment(R.id.web_discovery_container, delegate);
    }

    @Override
    protected FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }
}
