package mall.online.com.latte.ec.main.cart;

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
import mall.online.com.latte.net.RestClient;
import mall.online.com.latte.net.callback.ISuccess;
import mall.online.com.latte.ui.recycler.MultipleFields;
import mall.online.com.latte.ui.recycler.MultipleItemEntity;

/**
 * Created by liWensheng on 2018/2/28.
 */

public class ShopCartDelegate extends BottomItemDelagate implements ISuccess, ICartItemLister {

    @BindView(R2.id.rv_shop_cart)
    RecyclerView recyclerView = null;

    @BindView(R2.id.icon_shop_cart_select_all)
    IconTextView mIconSelectedAll = null;

    @BindView(R2.id.stub_no_item)
    ViewStubCompat mStubNoItem = null;

    @BindView(R2.id.tv_shop_cart_total_price)
    AppCompatTextView mTotalPrice = null;


    private ShopCartAdapter mAdapter = null;


    /**
     * 点击是否全选
     */
    @OnClick(R2.id.icon_shop_cart_select_all)
    void onClickSelectAll() { // 是否全选并更新view
        final int tag = (int) mIconSelectedAll.getTag();
        if (tag == 0) {// 全选
            mIconSelectedAll.setTextColor
                    (ContextCompat.getColor(Latte.getApplicationContext(), R.color.colorGreen));
            mIconSelectedAll.setTag(1);
            mAdapter.setIsSelectedAll(true);
            mAdapter.clearTotal();
            mAdapter.notifyItemRangeChanged(0, mAdapter.getItemCount());
        } else { // 反选
            mIconSelectedAll.setTextColor(Color.GRAY);
            mIconSelectedAll.setTag(0);
            mAdapter.setIsSelectedAll(false);
            mAdapter.notifyItemRangeChanged(0, mAdapter.getItemCount());
            mAdapter.clearTotal();
            setTotalPrice();
        }
    }

    /**
     * 点击删除
     */
    @OnClick(R2.id.tv_top_shop_remove_selected)
    void onClickRemoveSelected() {
        if (mAdapter.getItemCount() != 0) {
            final List<MultipleItemEntity> data = mAdapter.getData();

            List<MultipleItemEntity> deleteEntities = new ArrayList<>();
            for (MultipleItemEntity entity: data) {
                final boolean isSelected = entity.getField(MultipleFields.IS_SELECTED);
                if (isSelected) {
                    deleteEntities.add(entity);
                }
            }

            for (int i = 0; i < deleteEntities.size(); i++) {
                int DataCount = data.size();
                int entityPosition = deleteEntities.get(i).getField(MultipleFields.POSITION);
                if (entityPosition < DataCount) {
                    mAdapter.remove(entityPosition);
                    for (; entityPosition < DataCount - 1; entityPosition++) {
                        int pos = data.get(entityPosition).getField(MultipleFields.POSITION);
                        data.get(entityPosition).setField(MultipleFields.POSITION, pos-1);
                    }
                }
            }
//        mAdapter.notifyDataSetChanged();
            checkItemCount();
        }
    }

    /**
     * 点击清空购物车
     */
    @OnClick(R2.id.tv_top_shop_cart_clear)
    void onClickClear() {
        if (mAdapter.getItemCount() != 0) {
            mAdapter.getData().clear();
            mAdapter.notifyDataSetChanged();
            checkItemCount();
        }
    }

    /**
     * 点击支付
     */
    @OnClick(R2.id.tv_shop_cart_pay)
    void onClickPay() {

    }

    /**
     * 创建订单
     */
    private void createOrder() {

    }

    @SuppressWarnings("RestrictedApi")
    private void checkItemCount() {
        final int count = mAdapter.getItemCount();
        if (count == 0) {
            final View stubView = mStubNoItem.inflate();
            final AppCompatTextView tvToBuy = (AppCompatTextView) stubView.findViewById(R.id.tv_stub_to_buy);
            tvToBuy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), "请购物", Toast.LENGTH_LONG).show();
                }
            });
            recyclerView.setVisibility(View.GONE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_shop_cart;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mIconSelectedAll.setTag(0);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        RestClient.builder()
                .url("http://114.67.145.163/RestServer/data/shop_cart_data.json")
                .loader(getContext())
                .success(this).build().get();
    }


    @Override
    public void onSuceess(String response) {
        final ArrayList<MultipleItemEntity> data =
                new ShopCartDateConverter().setJsonData(response).convert();

        mAdapter = new ShopCartAdapter(data);
        mAdapter.setItemCartListener(this);

        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);

        recyclerView.setAdapter(mAdapter);

        final double price = mAdapter.getTotalPrice();
        mTotalPrice.setText(String.valueOf(price));

        checkItemCount();
    }

    @Override
    public void onItemClick(double itemTotalPrice) {
        setTotalPrice();
    }

    private void setTotalPrice() {
        final double price = mAdapter.getTotalPrice();
        mTotalPrice.setText(String.valueOf(price));
    }
}
