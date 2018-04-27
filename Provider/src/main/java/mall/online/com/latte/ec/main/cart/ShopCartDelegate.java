package mall.online.com.latte.ec.main.cart;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ViewStubCompat;
import android.view.View;
import android.widget.Toast;

import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import mall.online.com.latte.app.Latte;
import mall.online.com.latte.delegate.bottom.BottomItemDelagate;
import mall.online.com.latte.ec.R;
import mall.online.com.latte.ec.R2;
import mall.online.com.latte.ec.main.EcBottomDelegate;
import mall.online.com.latte.ec.main.cart.order.OrderDelegate;
import mall.online.com.latte.ec.pay.FastPay;
import mall.online.com.latte.net.RestClient;
import mall.online.com.latte.net.callback.ISuccess;
import mall.online.com.latte.ui.recycler.MultipleFields;
import mall.online.com.latte.ui.recycler.MultipleItemEntity;
import mall.online.com.latte.utils.log.LogUtil;
import mall.online.com.latte.utils.storage.PreferenceUtils;

/**
 * Created by liWensheng on 2018/2/28.
 */

public class ShopCartDelegate extends BottomItemDelagate implements ISuccess, ICartItemLister {

    @BindView(R2.id.rv_shop_cart)
    RecyclerView recyclerView = null;


    @BindView(R2.id.tv_shop_cart_total_price)
    AppCompatTextView mTotalPrice = null;


    private ShopCartAdapter mAdapter = null;

    private boolean flag = false;



    /**
     * 点击删除
     */
    @OnClick(R2.id.tv_top_shop_remove_selected)
    void onClickRemoveSelected() {
        String id = "";
        if (mAdapter.getItemCount() != 0) {
            final List<MultipleItemEntity> data = mAdapter.getData();

            List<MultipleItemEntity> deleteEntities = new ArrayList<>();
            for (MultipleItemEntity entity: data) {
                final boolean isSelected = entity.getField(MultipleFields.IS_SELECTED);
                if (isSelected) {
                    String cid = entity.getField(MultipleFields.OBJECTID);
                    id = id + cid + ",";
                    deleteEntities.add(entity);
                }
            }

            for (int i = 0; i < deleteEntities.size(); i++) {
                int DataCount = data.size();
                int entityPosition = deleteEntities.get(i).getField(MultipleFields.POSITION);
                if (entityPosition < DataCount) {
                    mAdapter.remove(entityPosition);
                    if (deleteEntities.get(i).getField(MultipleFields.IS_SELECTED)) {
                        deleteEntities.get(i).setField(MultipleFields.IS_SELECTED, false);
                    }
                    for (; entityPosition < DataCount - 1; entityPosition++) {
                        int pos = data.get(entityPosition).getField(MultipleFields.POSITION);
                        data.get(entityPosition).setField(MultipleFields.POSITION, pos-1);
                    }
                }
            }
            mAdapter.clearTotal();
            mAdapter.notifyDataSetChanged();
            setTotalPrice();
        }
        RestClient.builder()
                .url("http://139.199.5.153:3000/ec/cart/delete?id="+id)
                .success(new ISuccess() {
                    @Override
                    public void onSuceess(String response) {

                    }
                }).build().post();
    }

    public void delete(){
        String id = "";
        if (mAdapter.getItemCount() != 0) {
            final List<MultipleItemEntity> data = mAdapter.getData();

            List<MultipleItemEntity> deleteEntities = new ArrayList<>();
            for (MultipleItemEntity entity: data) {
                final boolean isSelected = entity.getField(MultipleFields.IS_SELECTED);
                if (isSelected) {
                    String cid = entity.getField(MultipleFields.OBJECTID);
                    id = id + cid + "|";
                    deleteEntities.add(entity);
                }
            }

            for (int i = 0; i < deleteEntities.size(); i++) {
                int DataCount = data.size();
                int entityPosition = deleteEntities.get(i).getField(MultipleFields.POSITION);
                if (entityPosition < DataCount) {
                    mAdapter.remove(entityPosition);
                    if (deleteEntities.get(i).getField(MultipleFields.IS_SELECTED)) {
                        deleteEntities.get(i).setField(MultipleFields.IS_SELECTED, false);
                    }
                    for (; entityPosition < DataCount - 1; entityPosition++) {
                        int pos = data.get(entityPosition).getField(MultipleFields.POSITION);
                        data.get(entityPosition).setField(MultipleFields.POSITION, pos-1);
                    }
                }
            }
            mAdapter.clearTotal();
            mAdapter.notifyDataSetChanged();
            setTotalPrice();
        }
        RestClient.builder()
                .url("http://139.199.5.153:3000/ec/cart/delete?id="+id)
                .success(new ISuccess() {
                    @Override
                    public void onSuceess(String response) {

                    }
                }).build().post();
    }


    /**
     * 点击支付
     */
    @OnClick(R2.id.tv_shop_cart_pay)
    void onClickPay() {
        ArrayList<String> orders = new ArrayList<>();
        double totalPrice = 0;
        if (mAdapter.getItemCount() != 0) {
            final List<MultipleItemEntity> data = mAdapter.getData();

            for (MultipleItemEntity entity: data) {
                final boolean isSelected = entity.getField(MultipleFields.IS_SELECTED);
                if (isSelected) {
                    String cid = entity.getField(MultipleFields.OBJECTID);
                    orders.add(cid);
                    totalPrice += (double)entity.getField(MultipleFields.PRICE);
                }
            }
        }
        if (orders.size() == 0) {
            Toast.makeText(getContext(), "您还未选择商品", Toast.LENGTH_SHORT).show();
        } else {
            EcBottomDelegate ecBottomDelegate = getParentDelegate();
            OrderDelegate orderDelegate = OrderDelegate.create(orders, mAdapter.getTotalPrice(), ecBottomDelegate);
            this.onPause();
            ecBottomDelegate.getSupportDelegate().start(orderDelegate);
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_shop_cart;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
    }

    @Override
    public void onResume() {
        super.onResume();
        RestClient.builder()
                .url("http://139.199.5.153:3000/ec/cart?phone="+ PreferenceUtils.getCustomAppProfile("current"))
                .loader(getContext())
                .success(this).build().get();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onSuceess(String response) {
        final ArrayList<MultipleItemEntity> data =
                new ShopCartDateConverter().setJsonData(response).convert();
        final EcBottomDelegate ecBottomDelegate = getParentDelegate();

        mAdapter = new ShopCartAdapter(data, ecBottomDelegate);
        mAdapter.setItemCartListener(this);

        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);

        recyclerView.setAdapter(mAdapter);

        final double price = mAdapter.getTotalPrice();
        mTotalPrice.setText(String.valueOf(price));

//        checkItemCount();
    }

    @Override
    public void onItemClick(double itemTotalPrice) {
        setTotalPrice();
    }

    @SuppressLint("DefaultLocale")
    private void setTotalPrice() {
        final double price = mAdapter.getTotalPrice();
        mTotalPrice.setText(String.format("%.2f",price));
    }

    @Override
    public void onNewBundle(Bundle args) {
        super.onNewBundle(args);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            LogUtil.i("where", "setUserVisibleHint VisibleToUser");
            super.onResume();
        }else {
            LogUtil.i("where", "setUserVisibleHint not VisibleToUser");
            super.onPause();
        }
    }
}
