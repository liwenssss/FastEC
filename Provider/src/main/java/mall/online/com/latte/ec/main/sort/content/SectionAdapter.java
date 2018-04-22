package mall.online.com.latte.ec.main.sort.content;

import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import mall.online.com.latte.ec.R;

/**
 * Created by liWensheng on 2018/2/26.
 */

public class SectionAdapter extends BaseSectionQuickAdapter<SectionBean, BaseViewHolder>{


    public SectionAdapter(int layoutResId, int sectionHeadResId, List<SectionBean> data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, SectionBean item) {
        helper.setText(R.id.header, item.header);
    }

    @Override
    protected void convert(BaseViewHolder helper, SectionBean item) {
        final String thumb = item.t.getMgoodsThumb();
        final String name = item.t.getmGoodsName();
        final String goodsId = item.t.getmGoodsId();
        final SectionContentItemEntity entity = item.t;

        helper.setText(R.id.tv, name);
        final AppCompatImageView goodsImageView = helper.getView(R.id.iv);

        Glide.with(mContext)
                .load(thumb)
                .into(goodsImageView);

        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), goodsId, Toast.LENGTH_LONG).show();
            }
        });

    }
}
