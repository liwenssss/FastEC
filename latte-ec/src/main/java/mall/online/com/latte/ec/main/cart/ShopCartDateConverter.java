package mall.online.com.latte.ec.main.cart;

import android.widget.ArrayAdapter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;

import mall.online.com.latte.ui.recycler.DataConverter;
import mall.online.com.latte.ui.recycler.ItemType;
import mall.online.com.latte.ui.recycler.MultipleFields;
import mall.online.com.latte.ui.recycler.MultipleItemEntity;

/**
 * Created by liWensheng on 2018/2/28.
 */

public class ShopCartDateConverter extends DataConverter {
    @Override
    public ArrayList<MultipleItemEntity> convert() {
        final ArrayList<MultipleItemEntity> dataList = new ArrayList<>();
        final JSONArray dataArray = JSON.parseObject(getJsonData()).getJSONArray("data");

        final int size = dataArray.size();
        for (int i = 0; i < size; i++) {
            final JSONObject data = dataArray.getJSONObject(i);
            final String title = data.getString("title");
            final int id = data.getInteger("id");
            final double price = data.getDouble("price");
            final int count = data.getInteger("count");
            final String desc = data.getString("desc");
            final String thumb = data.getString("thumb");

            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setField(MultipleFields.ITEM_TYPE, ItemType.SHOP_CART_ITEM)
                    .setField(MultipleFields.TITLE, title)
                    .setField(MultipleFields.DESC, desc)
                    .setField(MultipleFields.ID, id)
                    .setField(MultipleFields.PRICE, price)
                    .setField(MultipleFields.COUNT, count)
                    .setField(MultipleFields.IMAGE_URL, thumb)
                    .setField(MultipleFields.IS_SELECTED, false)
                    .build();

            dataList.add(entity);
        }

        return dataList;
    }
}
