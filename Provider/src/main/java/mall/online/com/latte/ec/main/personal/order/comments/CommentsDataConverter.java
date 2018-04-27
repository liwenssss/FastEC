package mall.online.com.latte.ec.main.personal.order.comments;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;

import mall.online.com.latte.ec.main.personal.order.OrderListItemType;
import mall.online.com.latte.ui.recycler.DataConverter;
import mall.online.com.latte.ui.recycler.MultipleFields;
import mall.online.com.latte.ui.recycler.MultipleItemEntity;

/**
 * Created by liWensheng on 2018/4/26.
 */

public class CommentsDataConverter extends DataConverter {
    @Override
    public ArrayList<MultipleItemEntity> convert() {
        final JSONArray array = JSON.parseObject(getJsonData()).getJSONArray("data");

        final int size = array.size();
        for (int i = 0; i < size; i++) {
            final JSONObject data = array.getJSONObject(i);
            final String phone = data.getString("phone");
            final String title = data.getString("title");
            final String desc = data.getString("desc");
            final int count = data.getInteger("count");
            final String thumb = data.getString("thumb");
            final String time = data.getString("time");
            final String id = data.getString("id");
            final boolean flag = data.getBoolean("comment");
            final String objectId = data.getString("_id");

            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setItemType(OrderListItemType.ITEM_ORDER_LIST)
                    .setField(MultipleFields.PHONE, phone)
                    .setField(MultipleFields.TITLE, title)
                    .setField(MultipleFields.DESC, desc)
                    .setField(MultipleFields.COUNT, count)
                    .setField(MultipleFields.IMAGE_URL, thumb)
                    .setField(MultipleFields.TIME, time)
                    .setField(MultipleFields.ID, id)
                    .setField(MultipleFields.FLAG, flag)
                    .setField(MultipleFields.OBJECTID, objectId)
                    .build();

            ENTITIES.add(entity);
        }
        return ENTITIES;
    }
}
