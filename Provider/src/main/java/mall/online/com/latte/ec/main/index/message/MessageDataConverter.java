package mall.online.com.latte.ec.main.index.message;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;

import mall.online.com.latte.ec.main.personal.order.OrderListItemType;
import mall.online.com.latte.ui.recycler.DataConverter;
import mall.online.com.latte.ui.recycler.MultipleFields;
import mall.online.com.latte.ui.recycler.MultipleItemEntity;

/**
 * Created by liWensheng on 2018/4/24.
 */

public class MessageDataConverter extends DataConverter {
    @Override
    public ArrayList<MultipleItemEntity> convert() {
        final JSONArray array = JSON.parseObject(getJsonData()).getJSONArray("data");

        final int size = array.size();
        for (int i = 0; i < size; i++) {
            final JSONObject data = array.getJSONObject(i);
            final String time = data.getString("time");
            final String objectId = data.getString("_id");

            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setItemType(MessageItemType.MESITEM)
                    .setField(MultipleFields.TIME, time)
                    .setField(MultipleFields.OBJECTID, objectId)
                    .build();

            ENTITIES.add(entity);
        }
        return ENTITIES;
    }
}
