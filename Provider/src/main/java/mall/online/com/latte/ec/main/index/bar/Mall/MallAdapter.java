package mall.online.com.latte.ec.main.index.bar.Mall;

import android.graphics.Typeface;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import mall.online.com.latte.delegate.LatteDelegate;
import mall.online.com.latte.ec.R;
import mall.online.com.latte.ec.detail.GoodsDetailDelegate;
import mall.online.com.latte.ec.main.index.IndexRecyclerAdapter;
import mall.online.com.latte.ui.recycler.MultipleFields;
import mall.online.com.latte.ui.recycler.MultipleItemEntity;
import mall.online.com.latte.ui.recycler.MultipleViewHolder;

/**
 * Created by liWensheng on 2018/4/25.
 */

public class MallAdapter extends IndexRecyclerAdapter {

    private static LatteDelegate DELEGATE = null;

    public MallAdapter(List<MultipleItemEntity> data, LatteDelegate delegate) {
        super(data);
        DELEGATE = delegate;
        init();
    }


    private void init() {
        addItemType(MallItemType.SINGLE_IMG, R.layout.item_cloth_single);
        addItemType(MallItemType.DOUBLE_IMG, R.layout.item_cloth_double);
        addItemType(MallItemType.HIGH_IMG, R.layout.item_cloth_high);
        addItemType(MallItemType.BAR_VIEW, R.layout.item_cloth_bar);

        //设置宽度监听
        setSpanSizeLookup(this);
        openLoadAnimation();
        isFirstOnly(false);
    }

    @Override
    protected MultipleViewHolder createBaseViewHolder(View view) {
        return super.createBaseViewHolder(view);
    }

    @Override
    protected void convert(MultipleViewHolder holder, final MultipleItemEntity entity) {
        final String mGoodsId;
        final String mImgUrl;
        final int mSpanSize;

        switch (holder.getItemViewType()){
            case MallItemType.SINGLE_IMG:
                mGoodsId = entity.getField(MultipleFields.ID);
                mImgUrl = entity.getField(MultipleFields.IMAGE_URL);

                Glide.with(mContext)
                        .load(mImgUrl)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .dontAnimate()
                        .centerCrop()
                        .into((ImageView) holder.getView(R.id.cloth_img_single));

                holder.getView(R.id.cloth_img_single).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        GoodsDetailDelegate goodsDetailDelegate = GoodsDetailDelegate.create((String) entity.getField(MultipleFields.ID));
                        DELEGATE.getSupportDelegate().start(goodsDetailDelegate);
                    }
                });
                break;
            case MallItemType.DOUBLE_IMG:
                mGoodsId = entity.getField(MultipleFields.ID);
                mImgUrl = entity.getField(MultipleFields.IMAGE_URL);


                Glide.with(mContext)
                        .load(mImgUrl)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .dontAnimate()
                        .centerCrop()
                        .into((ImageView) holder.getView(R.id.cloth_img_double));

                holder.getView(R.id.cloth_img_double).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        GoodsDetailDelegate goodsDetailDelegate = GoodsDetailDelegate.create((String) entity.getField(MultipleFields.ID));
                        DELEGATE.getSupportDelegate().start(goodsDetailDelegate);
                    }
                });
                break;
            case MallItemType.HIGH_IMG:
                mGoodsId = entity.getField(MultipleFields.ID);
                mImgUrl = entity.getField(MultipleFields.IMAGE_URL);

                Glide.with(mContext)
                        .load(mImgUrl)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .dontAnimate()
                        .centerCrop()
                        .into((ImageView) holder.getView(R.id.cloth_img_high));

                holder.getView(R.id.cloth_img_high).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        GoodsDetailDelegate goodsDetailDelegate = GoodsDetailDelegate.create(mGoodsId);
                        DELEGATE.getSupportDelegate().start(goodsDetailDelegate);
                    }
                });

                break;
            case MallItemType.BAR_VIEW:
                Typeface tf1 = Typeface.createFromAsset(holder.getView(R.id.cloth_title).getContext().getAssets(),
                        "font.ttf");
                Typeface tf2 = Typeface.createFromAsset(holder.getView(R.id.tv_anvantage).getContext().getAssets(),
                        "advantage.ttf");
                TextView title = holder.getView(R.id.cloth_title);
                TextView advantage = holder.getView(R.id.tv_anvantage);
                title.setTypeface(tf1);
                advantage.setTypeface(tf2);
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
