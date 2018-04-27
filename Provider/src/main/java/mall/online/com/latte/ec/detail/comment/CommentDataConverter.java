package mall.online.com.latte.ec.detail.comment;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;

import mall.online.com.latte.ui.recycler.DataConverter;
import mall.online.com.latte.ui.recycler.MultipleFields;
import mall.online.com.latte.ui.recycler.MultipleItemEntity;

/**
 * Created by liWensheng on 2018/4/26.
 */

public class CommentDataConverter extends DataConverter {
    @Override
    public ArrayList<MultipleItemEntity> convert() {
        final JSONArray array = JSON.parseObject(getJsonData()).getJSONArray("data");

        final int size = array.size();
        for (int i = 0; i < size; i++) {
            final JSONObject data = array.getJSONObject(i);
            final String desc = data.getString("desc");
            final String time = data.getString("time");

            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setItemType(CommentInfoItemType.COMMENT)
                    .setField(MultipleFields.DESC, desc)
                    .setField(MultipleFields.TIME, time)
                    .build();

            ENTITIES.add(entity);
        }
        return ENTITIES;
    }
}
