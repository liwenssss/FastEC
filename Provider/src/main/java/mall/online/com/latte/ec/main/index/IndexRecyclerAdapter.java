package mall.online.com.latte.ec.main.index;

import android.graphics.Typeface;
import android.support.v7.widget.GridLayoutManager;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import mall.online.com.latte.R;
import mall.online.com.latte.app.Latte;
import mall.online.com.latte.delegate.LatteDelegate;
import mall.online.com.latte.ec.R2;
import mall.online.com.latte.ec.detail.GoodsDetailDelegate;
import mall.online.com.latte.ec.main.EcBottomDelegate;
import mall.online.com.latte.ec.main.index.bar.MallDelegate;
import mall.online.com.latte.ui.banner.BannerCreator;
import mall.online.com.latte.ui.recycler.DataConverter;
import mall.online.com.latte.ui.recycler.ItemType;
import mall.online.com.latte.ui.recycler.MultipleFields;
import mall.online.com.latte.ui.recycler.MultipleItemEntity;
import mall.online.com.latte.ui.recycler.MultipleViewHolder;
import mall.online.com.latte.utils.log.LogUtil;
import retrofit2.http.DELETE;

/**
 * Created by liWensheng on 2018/2/24.
 */

public class IndexRecyclerAdapter
        extends BaseMultiItemQuickAdapter<MultipleItemEntity, MultipleViewHolder>
        implements BaseQuickAdapter.SpanSizeLookup {

    private boolean mIsInitBanner = false;
    private Typeface typeface = null;
    private static LatteDelegate DELEGATE = null;

    protected IndexRecyclerAdapter(List<MultipleItemEntity> data) {
        super(data);
        init();
    }

    public static IndexRecyclerAdapter create(List<MultipleItemEntity> data) {
        return new IndexRecyclerAdapter(data);
    }

    public static IndexRecyclerAdapter create(DataConverter converter, LatteDelegate delegate) {
        DELEGATE = delegate;
        return new IndexRecyclerAdapter(converter.convert());
    }


    /**
     * 设置不同的item布局
     */
    private void init() {
        addItemType(ItemType.TEXT, R.layout.item_multiple_text);
        addItemType(ItemType.IMAGE, R.layout.item_multiple_image);
        addItemType(ItemType.TEXT_IMAGE, R.layout.item_multiple_image_text);
        addItemType(ItemType.BANNER, R.layout.item_multiple_banner);
        addItemType(ItemType.SECTION, R.layout.item_multiple_section);
        addItemType(ItemType.BAR, R.layout.item_multiple_bar);

        //设置宽度监听
        setSpanSizeLookup(this);
        openLoadAnimation();
        isFirstOnly(false);
    }

    @Override
    protected MultipleViewHolder createBaseViewHolder(View view) {
        return MultipleViewHolder.create(view);
    }

    @Override
    protected void convert(final MultipleViewHolder holder, final MultipleItemEntity entity) {
        final String text;
        final String icon;
        final String imageUrl;
        final String price;
        final String desc;
        final ArrayList<String> bannerImages;


        switch (holder.getItemViewType()) {
            case ItemType.TEXT:
                text = entity.getField(MultipleFields.TEXT);
                icon = entity.getField(MultipleFields.ICON);
                TextView textView = holder.getView(R.id.text_single);
                typeface = Typeface.createFromAsset(
                        holder.getView(R.id.text_single).getContext().getAssets(),
                        "font.ttf");
                textView.setTypeface(typeface);
                holder.setText(R.id.text_single, text);
                holder.setText(R.id.text_single_icon, icon);
                break;
            case ItemType.IMAGE:
                imageUrl = entity.getField(MultipleFields.IMAGE_URL);
                Glide.with(mContext)
                        .load(imageUrl)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .dontAnimate()
                        .centerCrop()
                        .into((ImageView) holder.getView(R.id.img_single));
                break;
            case ItemType.TEXT_IMAGE:
                text = entity.getField(MultipleFields.TEXT);
                imageUrl = entity.getField(MultipleFields.IMAGE_URL);
                price = entity.getField(MultipleFields.PRICE);
                desc = entity.getField(MultipleFields.DESC);
                Glide.with(mContext)
                        .load(imageUrl)
                        .diskCacheStrategy((DiskCacheStrategy.ALL))
                        .dontAnimate()
                        .centerCrop()
                        .into((ImageView) holder.getView(R.id.img_multiple));
                holder.setText(R.id.tv_multiple, text);
                holder.setText(R.id.tv_multiple_price, price);
                holder.setText(R.id.tv_multiple_desc, desc);
                break;
            case ItemType.BANNER:
                if (!mIsInitBanner) {
                    bannerImages = entity.getField(MultipleFields.BANNERS);
                    final ConvenientBanner<String> convenientBanner = holder.getView(R.id.banner_recycler_item);
                    final String[] strings = entity.getField(MultipleFields.ID).toString().split("/");
                    BannerCreator.setDefault(convenientBanner, bannerImages, new com.bigkoo.convenientbanner.listener.OnItemClickListener() {
                        @Override
                        public void onItemClick(int position) {
                            final GoodsDetailDelegate delegate = GoodsDetailDelegate.create(strings[position]);
                            DELEGATE.getSupportDelegate().start(delegate);
                        }
                    });
                    mIsInitBanner = true;
                }
                break;
            case ItemType.SECTION:
                text = entity.getField(MultipleFields.TEXT);
                desc = entity.getField(MultipleFields.DESC);
                imageUrl = entity.getField(MultipleFields.IMAGE_URL);
                Glide.with(mContext)
                        .load(imageUrl)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .dontAnimate()
                        .centerCrop()
                        .into((ImageView) holder.getView(R.id.section_img_1));
                holder.setText(R.id.section_desc, desc);
                holder.setText(R.id.section_title, text);
                break;
            case ItemType.BAR:
                LinearLayout mall =  holder.getView(R.id.rv_bar_mall);
                mall.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DELEGATE.getSupportDelegate().start(new MallDelegate());
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
