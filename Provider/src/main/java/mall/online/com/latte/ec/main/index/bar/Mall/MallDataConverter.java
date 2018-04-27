package mall.online.com.latte.ec.main.index.bar.Mall;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;

import mall.online.com.latte.ui.recycler.DataConverter;
import mall.online.com.latte.ui.recycler.MultipleFields;
import mall.online.com.latte.ui.recycler.MultipleItemEntity;

/**
 * Created by liWensheng on 2018/4/25.
 */

public class MallDataConverter extends DataConverter {
    @Override
    public ArrayList<MultipleItemEntity> convert() {
        final JSONArray dataArray = JSON.parseObject(getJsonData()).getJSONArray("data");
        final int size = dataArray.size();
        for (int i = 0; i < size; i++) {
            final JSONObject data = dataArray.getJSONObject(i);
            final String imageUrl = data.getString("imageUrl");
            final int spanSize = data.getInteger("spanSize");
            final String id = data.getString("goodsId");
            final int typeId = data.getInteger("type");

            int type = 0;

            if (typeId == 0) {
                type = MallItemType.SINGLE_IMG;
            } else if (typeId == 1) {
                type = MallItemType.DOUBLE_IMG;
            } else if (typeId == 2) {
                type = MallItemType.HIGH_IMG;
            } else if (typeId == 3) {
                type = MallItemType.BAR_VIEW;
            }

            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setField(MultipleFields.ITEM_TYPE, type)
                    .setField(MultipleFields.SPAN_SIZE, spanSize)
                    .setField(MultipleFields.ID, id)
                    .setField(MultipleFields.IMAGE_URL, imageUrl)
                    .build();

            ENTITIES.add(entity);
        }

        return ENTITIES;
    }
}
