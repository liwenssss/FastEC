package mall.online.com.latte.ec.main.personal.order;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import butterknife.BindView;
import mall.online.com.latte.delegate.LatteDelegate;
import mall.online.com.latte.ec.R;
import mall.online.com.latte.ec.R2;
import mall.online.com.latte.ec.main.personal.PersonalDelegate;
import mall.online.com.latte.net.RestClient;
import mall.online.com.latte.net.callback.ISuccess;
import mall.online.com.latte.ui.recycler.MultipleItemEntity;
import mall.online.com.latte.utils.log.LogUtil;
import mall.online.com.latte.utils.storage.PreferenceUtils;

/**
 * Created by liWensheng on 2018/3/2.
 */

public class OrderListDelegate extends LatteDelegate {

    private String mType = null;

    @BindView(R2.id.rv_order_list)
    RecyclerView mRecyclerView = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_order_list;
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
        RestClient.builder()
                .loader(getContext())
                .url("http://139.199.5.153:3000/ec/orders?userphone=" + PreferenceUtils.getCustomAppProfile("current"))
                .params("type", mType)
                .success(new ISuccess() {
                    @Override
                    public void onSuceess(String response) {
                        LogUtil.i("order", response);
                        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
                        manager.setStackFromEnd(true);
                        manager.setReverseLayout(true);
                        mRecyclerView.setLayoutManager(manager);
                        final List<MultipleItemEntity> data =
                                new  OrderListDataConverter().setJsonData(response).convert();
                        final OrderListAdapter adapter = new OrderListAdapter(data, getDelegate());
                        mRecyclerView.setAdapter(adapter);

                    }
                }).build().get();
    }

    private OrderListDelegate getDelegate() {
        return this;
    }
}
