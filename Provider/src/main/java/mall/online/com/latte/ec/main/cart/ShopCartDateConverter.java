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
import mall.online.com.latte.utils.storage.PreferenceUtils;

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
            final String objectId = data.getString("_id");
            String phone = data.getString("phone");
            final String title = data.getString("title");
            final String id = data.getString("id");
            final String priceStr = data.getString("price");
            final double price = Double.parseDouble(priceStr);
            final int count = data.getInteger("count");
            final String desc = data.getString("desc");
            final String thumb = data.getString("thumb");

            if (phone.equals(PreferenceUtils.getCustomAppProfile("current"))) {

                final MultipleItemEntity entity = MultipleItemEntity.builder()
                        .setField(MultipleFields.ITEM_TYPE, ItemType.SHOP_CART_ITEM)
                        .setField(MultipleFields.TITLE, title)
                        .setField(MultipleFields.DESC, desc)
                        .setField(MultipleFields.ID, id)
                        .setField(MultipleFields.PRICE, price)
                        .setField(MultipleFields.COUNT, count)
                        .setField(MultipleFields.IMAGE_URL, thumb)
                        .setField(MultipleFields.IS_SELECTED, false)
                        .setField(MultipleFields.POSITION, i)
                        .setField(MultipleFields.OBJECTID, objectId)
                        .build();

                dataList.add(entity);
            }
        }

        return dataList;
    }
}
