package mall.online.com.latte.ec.main.personal.order;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import butterknife.BindView;
import mall.online.com.latte.delegate.LatteDelegate;
import mall.online.com.latte.ec.R;
import mall.online.com.latte.ec.R2;
import mall.online.com.latte.ec.main.personal.PersonalDelegate;
import mall.online.com.latte.ec.main.personal.order.after.AfterAdapter;
import mall.online.com.latte.ec.main.personal.order.after.AfterDataConverter;
import mall.online.com.latte.ec.main.personal.order.comments.CommentsAdapter;
import mall.online.com.latte.ec.main.personal.order.comments.CommentsDataConverter;
import mall.online.com.latte.net.RestClient;
import mall.online.com.latte.net.callback.ISuccess;
import mall.online.com.latte.ui.recycler.MultipleItemEntity;
import mall.online.com.latte.utils.storage.PreferenceUtils;

/**
 * Created by liWensheng on 2018/3/2.
 */

public class OrderListDelegate extends LatteDelegate {

    private String mType = null;

    private static String MTYPE = "MTYPE";

    @BindView(R2.id.toolbar_title)
    AppCompatTextView TITLE = null;

    @BindView(R2.id.rv_order_list)
    RecyclerView mRecyclerView = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_order_list;
    }

    public static OrderListDelegate create(String type) {
        final Bundle args = new Bundle();
        args.putString(MTYPE, type);
        final OrderListDelegate delegate = new OrderListDelegate();
        delegate.setArguments(args);
        return delegate;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        mType = args.getString(PersonalDelegate.ORDER_TYPE);
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        if (mType.equals("all")) {
            TITLE.setText("订单列表");
            showAllOrders();
        } else if (mType.equals("after")){
            TITLE.setText("售后");
            showAfter();
        } else if (mType.equals("evaluate")) {
            TITLE.setText("待评价");
            showComments();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mType.equals("all")) {
            TITLE.setText("订单列表");
            showAllOrders();
        } else if (mType.equals("after")){
            TITLE.setText("售后");
            showAfter();
        } else if (mType.equals("evaluate")) {
            TITLE.setText("待评价");
            showComments();
        }
    }

    private OrderListDelegate getDelegate() {
        return this;
    }

    private void showAllOrders() {
        RestClient.builder()
                .loader(getContext())
                .url("http://139.199.5.153:3000/ec/orders?userphone=" + PreferenceUtils.getCustomAppProfile("current"))
                .success(new ISuccess() {
                    @Override
                    public void onSuceess(String response) {
                        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
                        manager.setStackFromEnd(true);
                        manager.setReverseLayout(true);
                        mRecyclerView.setLayoutManager(manager);
                        final List<MultipleItemEntity> data =
                                new OrderListDataConverter().setJsonData(response).convert();
                        final OrderListAdapter adapter = new OrderListAdapter(data, getDelegate());
                        mRecyclerView.setAdapter(adapter);

                    }
                }).build().get();
    }

    private void showAfter() {
        RestClient.builder()
                .loader(getContext())
                .url("http://139.199.5.153:3000/ec/after?phone=" + PreferenceUtils.getCustomAppProfile("current"))
                .success(new ISuccess() {
                    @Override
                    public void onSuceess(String response) {
                        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
                        manager.setStackFromEnd(true);
                        manager.setReverseLayout(true);
                        mRecyclerView.setLayoutManager(manager);
                        final List<MultipleItemEntity> data =
                                new AfterDataConverter().setJsonData(response).convert();
                        final AfterAdapter afterAdapter = new AfterAdapter(data, getDelegate());
                        mRecyclerView.setAdapter(afterAdapter);
                    }
                })
                .build().get();
    }

    private void showComments() {
        RestClient.builder()
                .loader(getContext())
                .url("http://139.199.5.153:3000/ec/after?phone=" + PreferenceUtils.getCustomAppProfile("current"))
                .success(new ISuccess() {
                    @Override
                    public void onSuceess(String response) {
                        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
                        manager.setStackFromEnd(true);
                        manager.setReverseLayout(true);
                        mRecyclerView.setLayoutManager(manager);
                        final List<MultipleItemEntity> data =
                                new CommentsDataConverter().setJsonData(response).convert();
                        final CommentsAdapter afterAdapter = new CommentsAdapter(data, getDelegate());
                        mRecyclerView.setAdapter(afterAdapter);
                    }
                })
                .build().get();
    }

    @Override
    public void onNewBundle(Bundle args) {
        super.onNewBundle(args);
        this.onResume();
    }
}
