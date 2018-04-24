package mall.online.com.latte.ec.main.cart.order;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import mall.online.com.latte.delegate.LatteDelegate;
import mall.online.com.latte.ec.R;
import mall.online.com.latte.ec.R2;
import mall.online.com.latte.net.RestClient;
import mall.online.com.latte.net.callback.ISuccess;
import mall.online.com.latte.ui.recycler.MultipleItemEntity;

/**
 * Created by liWensheng on 2018/4/22.
 */

public class OrderDetailDelegate extends LatteDelegate {

    @BindView(R2.id.rv_order_list)
    RecyclerView mRecyclerView = null;

    private static final String ORDERS = "ORDERS";
    private ArrayList<String> OrderList = new ArrayList<>();

    public static OrderDetailDelegate create(ArrayList<String> orders) {
        final Bundle args = new Bundle();
        args.putStringArrayList(ORDERS, orders);
        final OrderDetailDelegate delegate = new OrderDetailDelegate();
        delegate.setArguments(args);
        return delegate;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        if (args != null) {
            OrderList = args.getStringArrayList(ORDERS);
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_order_detail;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        String params = "";
        final int size = OrderList.size();
        for (int i = 0; i < size; i++) {
            if (i!=size-1)
                params += OrderList.get(i) + ',';
            else
                params += OrderList.get(i);
        }

        RestClient.builder()
                .loader(getContext())
                .url("http://139.199.5.153:3000/ec/cart/order?id="+params)
                .success(new ISuccess() {
                    @Override
                    public void onSuceess(String response) {
                        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
                        mRecyclerView.setLayoutManager(manager);
                        final List<MultipleItemEntity> data
                                = new OrderDetailDataConverter().setJsonData(response).convert();
                        final OrderDetailAdapter orderDetailAdapter = new OrderDetailAdapter(data);
                        mRecyclerView.setAdapter(orderDetailAdapter);
                    }
                }).build().get();
    }
}
