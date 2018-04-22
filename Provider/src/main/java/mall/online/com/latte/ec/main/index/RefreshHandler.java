package mall.online.com.latte.ec.main.index;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;

import mall.online.com.latte.app.Latte;
import mall.online.com.latte.delegate.LatteDelegate;
import mall.online.com.latte.ec.main.EcBottomDelegate;
import mall.online.com.latte.net.RestClient;
import mall.online.com.latte.net.callback.ISuccess;
import mall.online.com.latte.ui.recycler.DataConverter;

/**
 * Created by liWensheng on 2018/2/23.
 */

public class RefreshHandler implements
        SwipeRefreshLayout.OnRefreshListener,
        BaseQuickAdapter.RequestLoadMoreListener  {

    private final SwipeRefreshLayout REFRESH_LAYOUT;
    private final PagingBean BEAN;
    private final RecyclerView RECYCLERVIEW;
    private IndexRecyclerAdapter mAdapter = null;
    private final DataConverter CONVERTER;
    private final LatteDelegate DELEGATE;

    public RefreshHandler(SwipeRefreshLayout swipeRefreshLayout,
                          RecyclerView recyclerView,
                          DataConverter dataConverter,
                          PagingBean bean,
                          LatteDelegate delegate) {
        this.REFRESH_LAYOUT = swipeRefreshLayout;
        this.RECYCLERVIEW = recyclerView;
        this.CONVERTER = dataConverter;
        this.BEAN = bean;
        this.DELEGATE = delegate;
        REFRESH_LAYOUT.setOnRefreshListener(this);
    }

    public static RefreshHandler create(SwipeRefreshLayout swipeRefreshLayout,
                                        RecyclerView recyclerView,
                                        DataConverter dataConverter,
                                        LatteDelegate delegate) {
        return new RefreshHandler(swipeRefreshLayout, recyclerView, dataConverter, new PagingBean(), delegate);
    }

    private void refresh() {
        REFRESH_LAYOUT.setRefreshing(true);
        Latte.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 进行网络请求
                REFRESH_LAYOUT.setRefreshing(false);
            }
        }, 2000);
    }

    public void firstPage(String url) {
        BEAN.setDelayed(1000);
        RestClient.builder()
                .url(url)
                .success(new ISuccess() {
                    @Override
                    public void onSuceess(String response) {
                        final JSONObject object = JSON.parseObject(response);
                        BEAN.setTotal(object.getInteger("total"))
                                .setPageSize(object.getInteger("page_size"));
                        // 设置adapter

                        mAdapter = IndexRecyclerAdapter.create(CONVERTER.setJsonData(response), DELEGATE);
                        mAdapter.setOnLoadMoreListener(RefreshHandler.this, RECYCLERVIEW);
                        RECYCLERVIEW.setAdapter(mAdapter);
                        BEAN.addIndex();
                    }
                })
                .build()
                .get();
    }

    @Override
    public void onRefresh() {
        refresh();
    }

    @Override
    public void onLoadMoreRequested() {

    }
}
