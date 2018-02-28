package mall.online.com.latte.ec.main.cart;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;

import com.bumptech.glide.Glide;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.List;

import mall.online.com.latte.ec.R;
import mall.online.com.latte.ui.recycler.ItemType;
import mall.online.com.latte.ui.recycler.MultipleFields;
import mall.online.com.latte.ui.recycler.MultipleItemEntity;
import mall.online.com.latte.ui.recycler.MultipleRecyclerAdapter;
import mall.online.com.latte.ui.recycler.MultipleViewHolder;

/**
 * Created by liWensheng on 2018/2/28.
 */

public class ShopCartAdapter extends MultipleRecyclerAdapter {

    protected ShopCartAdapter(List<MultipleItemEntity> data) {
        super(data);
        //添加购物车item布局
        addItemType(ItemType.SHOP_CART_ITEM, R.layout.item_shop_cart);
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        super.convert(holder, entity);
        switch (holder.getItemViewType()) {
            case ItemType.SHOP_CART_ITEM:

                // 取值
                final String title = entity.getField(MultipleFields.TITLE);
                final String desc = entity.getField(MultipleFields.DESC);
                final int id = entity.getField(MultipleFields.ID);
                final int count = entity.getField(MultipleFields.COUNT);
                final double price = entity.getField(MultipleFields.PRICE);
                final String thumb = entity.getField(MultipleFields.IMAGE_URL);

                // 取控件
                final AppCompatTextView tvTitle = holder.getView(R.id.tv_item_shop_cart_title);
                final AppCompatTextView tvDesc = holder.getView(R.id.tv_item_shop_cart_des);
                final AppCompatTextView tvtvPrice = holder.getView(R.id.tv_item_shop_cart_price);
                final IconTextView iconMinus = holder.getView(R.id.icon_item_minus);
                final IconTextView iconPlus = holder.getView(R.id.icon_item_plus);
                final AppCompatTextView tvCount = holder.getView(R.id.tv_item_shop_cart_count);
                final AppCompatImageView imageThumb = holder.getView(R.id.image_item_shop_cart);

                // 赋值
                tvTitle.setText(title);
                tvDesc.setText(desc);
                tvtvPrice.setText(String.valueOf(price));
                tvCount.setText(String.valueOf(count));
                Glide.with(mContext)
                        .load(thumb)
                        .into(imageThumb);

                break;
            default:
                break;
        }
    }
}
