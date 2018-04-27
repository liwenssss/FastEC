package mall.online.com.latte.ec.main.index.search.ans;

import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import mall.online.com.latte.ec.R;
import mall.online.com.latte.delegate.LatteDelegate;
import mall.online.com.latte.ec.detail.GoodsDetailDelegate;
import mall.online.com.latte.ec.main.index.IndexRecyclerAdapter;
import mall.online.com.latte.ui.recycler.MultipleFields;
import mall.online.com.latte.ui.recycler.MultipleItemEntity;
import mall.online.com.latte.ui.recycler.MultipleViewHolder;

/**
 * Created by liWensheng on 2018/2/24.
 */

public class AnsRecyclerAdapter extends IndexRecyclerAdapter{

    private static LatteDelegate DELEGATE = null;

    protected AnsRecyclerAdapter(List<MultipleItemEntity> data, LatteDelegate delegate) {
        super(data);
        DELEGATE = delegate;
        init();
    }



    /**
     * 设置不同的item布局
     */
    private void init() {
        addItemType(AnsType.SEARCHANS, R.layout.item_search_ans);

    }

    @Override
    protected MultipleViewHolder createBaseViewHolder(View view) {
        return MultipleViewHolder.create(view);
    }

    @Override
    protected void convert(final MultipleViewHolder holder, final MultipleItemEntity entity) {

        final String id;
        final String text;
        final String imageUrl;
        final String price;
        final String desc;


        switch (holder.getItemViewType()) {
            case AnsType.SEARCHANS:
                id = entity.getField(MultipleFields.ID);
                text = entity.getField(MultipleFields.TEXT);
                imageUrl = entity.getField(MultipleFields.IMAGE_URL);
                price = entity.getField(MultipleFields.PRICE);
                desc = entity.getField(MultipleFields.DESC);

                holder.setText(R.id.search_title, text);
                holder.setText(R.id.search_desc, desc);
                holder.setText(R.id.search_price, price);

                Glide.with(mContext)
                        .load(imageUrl)
                        .diskCacheStrategy((DiskCacheStrategy.ALL))
                        .dontAnimate()
                        .centerCrop()
                        .into((ImageView) holder.getView(R.id.search_img));

                holder.getView(R.id.search_ans_item).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        GoodsDetailDelegate goodsDetailDelegate = GoodsDetailDelegate.create(id);
                        DELEGATE.getSupportDelegate().start(goodsDetailDelegate);
                    }
                });
                break;
            default:
                break;
        }
    }

    @Override
    public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
        return getData().get(position).getField(MultipleFields.SPAN_SIZE);
    }

}
