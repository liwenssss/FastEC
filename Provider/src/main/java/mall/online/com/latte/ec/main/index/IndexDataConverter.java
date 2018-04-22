package mall.online.com.latte.ec.main.index;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;

import mall.online.com.latte.ui.recycler.DataConverter;
import mall.online.com.latte.ui.recycler.ItemType;
import mall.online.com.latte.ui.recycler.MultipleFields;
import mall.online.com.latte.ui.recycler.MultipleItemEntity;
import mall.online.com.latte.ui.recycler.MultipleItemEntityBuilder;

/**
 * Created by liWensheng on 2018/2/23.
 */

public class IndexDataConverter extends DataConverter {

    /**
     * 1 banner
     * 2 section
     * 3 title
     * 4 img
     * 5 img_text
     * 6 bar
     */

    @Override
    public ArrayList<MultipleItemEntity> convert() {
        final JSONArray dataArray = JSON.parseObject(getJsonData()).getJSONArray("data");
        final int size = dataArray.size();
        for (int i = 0; i < size; i++) {
            final JSONObject data = dataArray.getJSONObject(i);
            final String imageUrl = data.getString("imageUrl");
            final String text = data.getString("text");
            final int spanSize = data.getInteger("spanSize");
            final String id = data.getString("goodsId");
            final String price = data.getString("price");
            final String desc = data.getString("desc");
            final String icon = data.getString("icon");
            final JSONArray banners = data.getJSONArray("banners");
            final int typeId = data.getInteger("type");

            final ArrayList<String> bannerImages = new ArrayList<>();
            int type = 0;

            if (typeId == 3) {
                type = ItemType.TEXT;
            } else if (typeId == 4) {
                type = ItemType.IMAGE;
            } else if (typeId == 5) {
                type = ItemType.TEXT_IMAGE;
            } else if (typeId == 1) {
                type = ItemType.BANNER;
                // banner初始化
                final int bannerSize = banners.size();
                for (int j = 0; j < bannerSize; j++) {
                    final String banner = banners.getString(j);
                    bannerImages.add(banner);
                }
            } else if (typeId == 2) {
                type = ItemType.SECTION;
            } else if (typeId == 6) {
                type = ItemType.BAR;
            }
            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setField(MultipleFields.ITEM_TYPE, type)
                    .setField(MultipleFields.SPAN_SIZE, spanSize)
                    .setField(MultipleFields.ID, id)
                    .setField(MultipleFields.TEXT, text)
                    .setField(MultipleFields.IMAGE_URL, imageUrl)
                    .setField(MultipleFields.BANNERS, bannerImages)
                    .setField(MultipleFields.PRICE, price)
                    .setField(MultipleFields.DESC, desc)
                    .setField(MultipleFields.ICON, icon)
                    .build();

            ENTITIES.add(entity);
        }

        return ENTITIES;
    }
}
