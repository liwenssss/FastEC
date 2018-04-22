package mall.online.com.latte.ec.detail;

import android.support.v7.widget.AppCompatImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import mall.online.com.latte.ec.R;
import mall.online.com.latte.ui.recycler.ItemType;
import mall.online.com.latte.ui.recycler.MultipleFields;
import mall.online.com.latte.ui.recycler.MultipleItemEntity;
import mall.online.com.latte.ec.main.index.IndexRecyclerAdapter;
import mall.online.com.latte.ui.recycler.MultipleViewHolder;

/**
 * Created by liWensheng on 2018/4/6.
 */

public class RecyclerImageAdapter extends IndexRecyclerAdapter {
    protected RecyclerImageAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(ItemType.SINGLE_BIG_IMAGE, R.layout.item_image);
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        super.convert(holder, entity);
        final int type = holder.getItemViewType();
        switch (type) {
            case ItemType.SINGLE_BIG_IMAGE:
                final AppCompatImageView imageView = holder.getView(R.id.image_rv_item);
                final String url = entity.getField(MultipleFields.IMAGE_URL);
                Glide.with(mContext)
                        .load(url)
                        .into(imageView);
                break;
            default:
                break;
        }
    }
}
