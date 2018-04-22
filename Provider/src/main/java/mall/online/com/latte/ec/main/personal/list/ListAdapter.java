package mall.online.com.latte.ec.main.personal.list;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import mall.online.com.latte.ec.R;

/**
 * Created by liWensheng on 2018/3/2.
 */

public class ListAdapter extends BaseMultiItemQuickAdapter<ListBean, BaseViewHolder> {

    public ListAdapter(List<ListBean> data) {
        super(data);
        addItemType(ListItemType.ITEM_NORMAL, R.layout.arrow_item_layout);
        addItemType(ListItemType.ITEM_AVATAR, R.layout.arrow_item_avatar);
    }

    @Override
    protected void convert(BaseViewHolder helper, ListBean item) {
        switch (helper.getItemViewType()) {
            case ListItemType.ITEM_NORMAL:
                helper.setText(R.id.tv_arrow_text, item.getmText());
                helper.setText(R.id.tv_arrow_value, item.getmValue());
                break;

            case ListItemType.ITEM_AVATAR:
                Glide.with(mContext)
                        .load(item.getmImageUrl())
                        .into((ImageView) helper.getView(R.id.img_arrow_avatar));
                break;
            default:
                break;
        }
    }
}
