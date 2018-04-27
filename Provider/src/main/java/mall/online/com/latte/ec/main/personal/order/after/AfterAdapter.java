package mall.online.com.latte.ec.main.personal.order.after;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import mall.online.com.latte.delegate.LatteDelegate;
import mall.online.com.latte.ec.R;
import mall.online.com.latte.ec.main.cart.order.OrderDetailDelegate;
import mall.online.com.latte.ec.main.index.IndexRecyclerAdapter;
import mall.online.com.latte.ec.main.personal.order.OrderListItemType;
import mall.online.com.latte.net.RestClient;
import mall.online.com.latte.net.callback.ISuccess;
import mall.online.com.latte.ui.recycler.MultipleFields;
import mall.online.com.latte.ui.recycler.MultipleItemEntity;
import mall.online.com.latte.ui.recycler.MultipleViewHolder;
import mall.online.com.latte.utils.log.LogUtil;

/**
 * Created by liWensheng on 2018/4/26.
 */

public class AfterAdapter extends IndexRecyclerAdapter {
    private LatteDelegate delegate = null;

    public AfterAdapter(List<MultipleItemEntity> data, LatteDelegate latteDelegate) {
        super(data);
        delegate = latteDelegate;
        addItemType(OrderListItemType.ITEM_ORDER_LIST, R.layout.item_after);
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        super.convert(holder, entity);
        switch (holder.getItemViewType()) {
            case  OrderListItemType.ITEM_ORDER_LIST:
                final AppCompatTextView title = holder.getView(R.id.tv_item_after_thing_title);
                final AppCompatTextView desc = holder.getView(R.id.tv_item_after_thing_des);
                final AppCompatTextView count = holder.getView(R.id.tv_item_after_thing_count);
                final AppCompatImageView imageView = holder.getView(R.id.image_item_after);
                final AppCompatTextView time = holder.getView(R.id.after_time);
                final AppCompatTextView button = holder.getView(R.id.after_button);

                final String titleVal = entity.getField(MultipleFields.TITLE);
                final String descVal = entity.getField(MultipleFields.DESC);
                final int countVal = entity.getField(MultipleFields.COUNT);
                final String thumb = entity.getField(MultipleFields.IMAGE_URL);
                final String timeVal = entity.getField(MultipleFields.TIME);
                final Boolean flag = entity.getField(MultipleFields.FLAG);
                final String objectId = entity.getField(MultipleFields.OBJECTID);

                title.setText(titleVal);
                desc.setText(descVal);
                count.setText(Integer.toString(countVal));

                Glide.with(mContext)
                        .load(thumb)
                        .into(imageView);

                time.setText(timeVal);

                if (!flag) {
                    button.setTextColor(Color.parseColor("#00cc99"));
                    button.setText("申请退货");
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            RestClient.builder()
                                    .loader(v.getContext())
                                    .url("http://139.199.5.153:3000/ec//after/receive?id="+objectId)
                                    .success(new ISuccess() {
                                        @Override
                                        public void onSuceess(String response) {
                                            LogUtil.i("申请售后","成功");
                                            button.setTextColor(Color.GRAY);
                                            button.setText("已申请");
                                        }
                                    }).build().get();
                        }
                    });
                } else {
                    button.setTextColor(Color.GRAY);
                    button.setText("已申请");
                }

                break;
            default:
                break;
        }
    }
}
