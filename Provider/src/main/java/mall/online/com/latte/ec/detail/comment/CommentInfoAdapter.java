package mall.online.com.latte.ec.detail.comment;

import android.annotation.SuppressLint;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import mall.online.com.latte.ec.R;
import mall.online.com.latte.ec.main.cart.order.CartOrderItemType;
import mall.online.com.latte.ec.main.index.IndexRecyclerAdapter;
import mall.online.com.latte.ui.recycler.MultipleFields;
import mall.online.com.latte.ui.recycler.MultipleItemEntity;
import mall.online.com.latte.ui.recycler.MultipleViewHolder;

/**
 * Created by liWensheng on 2018/4/26.
 */

public class CommentInfoAdapter extends IndexRecyclerAdapter {
    protected CommentInfoAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(CommentInfoItemType.COMMENT, R.layout.item_detail_comment);
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        super.convert(holder, entity);
        switch (holder.getItemViewType()) {
            case  CommentInfoItemType.COMMENT:
                final TextView desc = holder.getView(R.id.comment_desc);
                final TextView time = holder.getView(R.id.comment_time);


                final String descVal = entity.getField(MultipleFields.DESC);
                final String timeVal = entity.getField(MultipleFields.TIME);

                desc.setText(descVal);
                time.setText(timeVal);

                break;
            default:
                break;
        }
    }


}
