package mall.online.com.latte.ec.main.cart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import mall.online.com.latte.delegate.bottom.BottomItemDelagate;
import mall.online.com.latte.ec.R;
import mall.online.com.latte.ec.R2;
import mall.online.com.latte.net.RestClient;
import mall.online.com.latte.net.callback.ISuccess;
import mall.online.com.latte.ui.recycler.MultipleItemEntity;

/**
 * Created by liWensheng on 2018/2/28.
 */

public class ShopCartDelegate extends BottomItemDelagate {

    @BindView(R2.id.rv_shop_cart)
    RecyclerView recyclerView = null;

    private ShopCartAdapter mAdapter = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_shop_cart;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        RestClient.builder()
                .url("http://114.67.145.163/RestServer/data/shop_cart_data.json")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuceess(String response) {
                        final ArrayList<MultipleItemEntity> data =
                                new ShopCartDateConverter().setJsonData(response).convert();

                        mAdapter = new ShopCartAdapter(data);

                        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
                        recyclerView.setLayoutManager(manager);

                        recyclerView.setAdapter(mAdapter);
                    }
                }).build().get();
    }
}
