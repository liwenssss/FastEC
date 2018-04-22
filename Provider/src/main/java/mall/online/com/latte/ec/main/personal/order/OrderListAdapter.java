package mall.online.com.latte.ec.main.personal.order;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;

import com.bumptech.glide.Glide;

import java.util.List;

import mall.online.com.latte.ec.R;
import mall.online.com.latte.ui.recycler.MultipleFields;
import mall.online.com.latte.ui.recycler.MultipleItemEntity;
import mall.online.com.latte.ec.main.index.IndexRecyclerAdapter;
import mall.online.com.latte.ui.recycler.MultipleViewHolder;

/**
 * Created by liWensheng on 2018/3/2.
 */

public class OrderListAdapter extends IndexRecyclerAdapter {

    protected OrderListAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(OrderListItemType.ITEM_ORDER_LIST, R.layout.item_order_list);

    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        super.convert(holder, entity);
        switch (holder.getItemViewType()) {
            case  OrderListItemType.ITEM_ORDER_LIST:
                final AppCompatImageView imageView = holder.getView(R.id.image_order_list);
                final AppCompatTextView title = holder.getView(R.id.tv_order_list_title);
                final AppCompatTextView price = holder.getView(R.id.tv_order_list_price);
                final AppCompatTextView time = holder.getView(R.id.tv_order_list_time);

                final String imageUrl = entity.getField(MultipleFields.IMAGE_URL);
                final String titleVal = entity.getField(MultipleFields.TITLE);
                final String timeVal = entity.getField(MultipleFields.TIME);
                final double priceVal = entity.getField(MultipleFields.PRICE);

                title.setText(titleVal);
                time.setText("时间：" + timeVal);
                price.setText("价格：" + String.valueOf(priceVal));

                Glide.with(mContext)
                        .load(imageUrl)
                        .into(imageView);
                break;
            default:
                break;
        }
    }
}
