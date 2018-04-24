package mall.online.com.latte.ec.main.cart.order;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;

import mall.online.com.latte.ec.main.personal.order.OrderListItemType;
import mall.online.com.latte.ui.recycler.DataConverter;
import mall.online.com.latte.ui.recycler.MultipleFields;
import mall.online.com.latte.ui.recycler.MultipleItemEntity;

/**
 * Created by liWensheng on 2018/4/23.
 */

public class OrderDetailDataConverter extends DataConverter {
    @Override
    public ArrayList<MultipleItemEntity> convert() {
        final JSONArray array = JSON.parseObject(getJsonData()).getJSONArray("data");

        final int size = array.size();
        for (int i = 0; i < size; i++) {
            final JSONObject data = array.getJSONObject(i);
            final String id = data.getString("id");
            final String thumb = data.getString("thumb");
            final String title = data.getString("title");
            final String desc = data.getString("desc");
            final String priceStr = data.getString("price");
            final double price = Double.parseDouble(priceStr);
            final int count = data.getInteger("count");

            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setItemType(CartOrderItemType.ITEM_ORDER_LIST)
                    .setField(MultipleFields.ID, id)
                    .setField(MultipleFields.IMAGE_URL, thumb)
                    .setField(MultipleFields.TITLE, title)
                    .setField(MultipleFields.DESC, desc)
                    .setField(MultipleFields.PRICE, price)
                    .setField(MultipleFields.COUNT, count)
                    .build();

            ENTITIES.add(entity);
        }
        return ENTITIES;
    }
}
