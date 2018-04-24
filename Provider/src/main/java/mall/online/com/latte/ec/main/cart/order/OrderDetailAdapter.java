package mall.online.com.latte.ec.main.cart.order;

import android.annotation.SuppressLint;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;

import com.bumptech.glide.Glide;

import java.util.List;

import mall.online.com.latte.ec.R;
import mall.online.com.latte.ec.main.index.IndexRecyclerAdapter;
import mall.online.com.latte.ec.main.personal.order.OrderListItemType;
import mall.online.com.latte.ui.recycler.MultipleFields;
import mall.online.com.latte.ui.recycler.MultipleItemEntity;
import mall.online.com.latte.ui.recycler.MultipleViewHolder;

/**
 * Created by liWensheng on 2018/4/23.
 */

public class OrderDetailAdapter extends IndexRecyclerAdapter {
    protected OrderDetailAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(CartOrderItemType.ITEM_ORDER_LIST, R.layout.item_cart_order);
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        super.convert(holder, entity);
        switch (holder.getItemViewType()) {
            case  CartOrderItemType.ITEM_ORDER_LIST:
                final AppCompatImageView imageView = holder.getView(R.id.item_cart_order_img);
                final AppCompatTextView title = holder.getView(R.id.item_cart_order_title);
                final AppCompatTextView desc = holder.getView(R.id.item_cart_order_desc);
                final AppCompatTextView price = holder.getView(R.id.item_cart_order_price);
                final AppCompatTextView count = holder.getView(R.id.item_cart_order_num);


                final String idVal = entity.getField(MultipleFields.ID);
                final String imageUrl = entity.getField(MultipleFields.IMAGE_URL);
                final String titleVal = entity.getField(MultipleFields.TITLE);
                final String descVal = entity.getField(MultipleFields.DESC);
                final double priceVal = entity.getField(MultipleFields.PRICE);
                final int countVal = entity.getField(MultipleFields.COUNT);

                title.setText(titleVal);
                desc.setText(descVal);
                price.setText("价格：" + String.format("%.2f", priceVal));
                count.setText(Integer.toString(countVal));

                Glide.with(mContext)
                        .load(imageUrl)
                        .into(imageView);
                break;
            default:
                break;
        }
    }
}
