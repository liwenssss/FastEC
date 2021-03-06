package mall.online.com.latte.ec.main.index;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.library.ChooseEditText;
import com.library.OnChooseEditTextListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import mall.online.com.latte.delegate.bottom.BottomItemDelagate;
import mall.online.com.latte.ec.R;
import mall.online.com.latte.ec.R2;
import mall.online.com.latte.ec.main.EcBottomDelegate;
import mall.online.com.latte.ec.main.index.message.MessageDelegate;
import mall.online.com.latte.ec.main.index.search.SearchDelegate;
import mall.online.com.latte.ui.banner.BannerCreator;
import mall.online.com.latte.ui.recycler.BaseDecoration;
import retrofit2.http.DELETE;

/**
 * Created by liWensheng on 2018/2/22.
 */

public class IndexDelegate extends BottomItemDelagate implements View.OnFocusChangeListener {

    @BindView(R2.id.rv_index)
    RecyclerView mRecyclerView = null;
    @BindView(R2.id.srl_index)
    SwipeRefreshLayout mRefreshLayout = null;
    @BindView(R2.id.tb_index)
    Toolbar mToolbar = null;
    @BindView(R2.id.et_search_view)
    EditText mSearchView = null;



    private RefreshHandler mRefreshHandler = null;

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        final EcBottomDelegate ecBottomDelegate = getParentDelegate();
        mRefreshHandler = RefreshHandler.create(mRefreshLayout, mRecyclerView, new IndexDataConverter(), ecBottomDelegate);
        mSearchView.setOnFocusChangeListener(this);
    }

    private void initRefreshLayout() {
        mRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );
        mRefreshLayout.setProgressViewOffset(true, 120, 300);

        // 得到父级元素
        final EcBottomDelegate ecBottomDelegate = getParentDelegate();
        mRecyclerView.addOnItemTouchListener(IndexItemClickListener.create(ecBottomDelegate));
    }

    private void initRecyclerView() {
        final GridLayoutManager manager = new GridLayoutManager(getContext(), 4);
        mRecyclerView.setLayoutManager(manager);
        // 添加分割线
        mRecyclerView.addItemDecoration(BaseDecoration.create(ContextCompat.getColor(getContext(), R.color.app_background), 2));
    }

    @OnClick(R2.id.icon_mes)
    public void getMes() {
        EcBottomDelegate ecBottomDelegate = getParentDelegate();
        ecBottomDelegate.getSupportDelegate().start(new MessageDelegate());
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initRefreshLayout();
        initRecyclerView();
        mRefreshHandler.firstPage("http://139.199.5.153:3000/ec/main");

    }


    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }


    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            getParentDelegate().getSupportDelegate().start(new SearchDelegate());
        }
    }
}
