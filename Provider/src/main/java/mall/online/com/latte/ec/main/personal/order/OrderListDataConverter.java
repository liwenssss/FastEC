package mall.online.com.latte.ec.main.personal.order;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;

import mall.online.com.latte.ui.recycler.DataConverter;
import mall.online.com.latte.ui.recycler.MultipleFields;
import mall.online.com.latte.ui.recycler.MultipleItemEntity;

/**
 * Created by liWensheng on 2018/3/2.
 */

public class OrderListDataConverter extends DataConverter {
    @Override
    public ArrayList<MultipleItemEntity> convert() {
        final JSONArray array = JSON.parseObject(getJsonData()).getJSONArray("data");

        final int size = array.size();
        for (int i = 0; i < size; i++) {
            final JSONObject data = array.getJSONObject(i);
            final String userphone = data.getString("userphone");
            final String name = data.getString("name");
            final String phone = data.getString("phone");
            final String diqu = data.getString("diqu");
            final String xiangxi = data.getString("xiangxi");
            final String ids = data.getString("ids");
            final int count = data.getInteger("count");
            final double price = data.getDouble("price");
            final String time = data.getString("time");
            final boolean flag = data.getBoolean("flag");
            final String objectId = data.getString("_id");

            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setItemType(OrderListItemType.ITEM_ORDER_LIST)
                    .setField(MultipleFields.USERPHONE, userphone)
                    .setField(MultipleFields.NAME, name)
                    .setField(MultipleFields.PHONE, phone)
                    .setField(MultipleFields.DIQU, diqu)
                    .setField(MultipleFields.XIANGXI, xiangxi)
                    .setField(MultipleFields.IDS, ids)
                    .setField(MultipleFields.COUNT, count)
                    .setField(MultipleFields.PRICE, price)
                    .setField(MultipleFields.TIME, time)
                    .setField(MultipleFields.FLAG, flag)
                    .setField(MultipleFields.OBJECTID, objectId)
                    .build();

            ENTITIES.add(entity);
        }
        return ENTITIES;
    }
}
