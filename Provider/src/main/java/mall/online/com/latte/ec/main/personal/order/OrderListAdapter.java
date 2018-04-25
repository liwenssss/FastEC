package mall.online.com.latte.ec.main.personal.order;

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
import mall.online.com.latte.net.RestClient;
import mall.online.com.latte.net.callback.ISuccess;
import mall.online.com.latte.ui.recycler.MultipleFields;
import mall.online.com.latte.ui.recycler.MultipleItemEntity;
import mall.online.com.latte.ec.main.index.IndexRecyclerAdapter;
import mall.online.com.latte.ui.recycler.MultipleViewHolder;

/**
 * Created by liWensheng on 2018/3/2.
 */

public class OrderListAdapter extends IndexRecyclerAdapter {

    private LatteDelegate delegate = null;

    protected OrderListAdapter(List<MultipleItemEntity> data, LatteDelegate latteDelegate) {
        super(data);
        delegate = latteDelegate;
        addItemType(OrderListItemType.ITEM_ORDER_LIST, R.layout.item_order_list);
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        super.convert(holder, entity);
        switch (holder.getItemViewType()) {
            case  OrderListItemType.ITEM_ORDER_LIST:
                final AppCompatTextView time = holder.getView(R.id.tv_order_list_time);
                final AppCompatTextView position = holder.getView(R.id.tv_order_list_position);
                final AppCompatTextView count = holder.getView(R.id.tv_order_count);
                final AppCompatTextView price = holder.getView(R.id.tv_order_list_price);
                final AppCompatTextView phone = holder.getView(R.id.tv_order_list_phone);
                final AppCompatTextView name = holder.getView(R.id.tv_order_list_name);
                final AppCompatTextView receive = holder.getView(R.id.order_receive_status);

                final String timeVal = entity.getField(MultipleFields.TIME);
                final String nameVal = entity.getField(MultipleFields.NAME);
                final String phoneVal = entity.getField(MultipleFields.PHONE);
                final String diqu = entity.getField(MultipleFields.DIQU);
                final String xiangxi = entity.getField(MultipleFields.XIANGXI);
                final int countVal = entity.getField(MultipleFields.COUNT);
                final double priceVal = entity.getField(MultipleFields.PRICE);
                final String ids = entity.getField(MultipleFields.IDS);
                final Boolean flag = entity.getField(MultipleFields.FLAG);
                final String objectId = entity.getField(MultipleFields.OBJECTID);

                time.setText(timeVal);
                position.setText(diqu+"\n"+xiangxi);
                count.setText(Integer.toString(countVal));
                price.setText(String.format("%.2f", priceVal));
                phone.setText(phoneVal);
                name.setText(nameVal);

                final String[] stringArray = ids.split(",");
                final ArrayList<String> arrayList = new ArrayList<>();
                for (int i = 0; i < stringArray.length; i++) {
                    arrayList.add(stringArray[i]);
                }

                if (!flag) {
                    receive.setTextColor(Color.parseColor("#00cc99"));
                    receive.setText("确认收货");
                    receive.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            RestClient.builder()
                                    .loader(v.getContext())
                                    .url("http://139.199.5.153:3000/ec/orders/receive?id="+objectId)
                                    .success(new ISuccess() {
                                        @Override
                                        public void onSuceess(String response) {
                                            receive.setTextColor(Color.GRAY);
                                            receive.setText("已收货");
                                        }
                                    }).build().get();
                        }
                    });
                } else {
                    receive.setTextColor(Color.GRAY);
                    receive.setText("已收货");
                }

                final LinearLayoutCompat linearLayoutCompat = holder.getView(R.id.order_list);
                linearLayoutCompat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        OrderDetailDelegate orderDetailDelegate = OrderDetailDelegate.create(arrayList);
                        delegate.getSupportDelegate().start(orderDetailDelegate);
                    }
                });

                break;
            default:
                break;
        }
    }
}
