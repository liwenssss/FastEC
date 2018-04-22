package mall.online.com.latte.ec.main.sort.content;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liWensheng on 2018/2/26.
 */

public class SectionDataConverter {

    final  List<SectionBean> converter(String json) {
        final List<SectionBean> dataList = new ArrayList<>();
        final JSONArray dataArray = JSON.parseObject(json).getJSONArray("data");

        final int size = dataArray.size();
        // 循环加载每个分类
        for (int i = 0; i < size; i++) {
            final JSONObject data = dataArray.getJSONObject(i);
            final int id = data.getInteger("id");
            final String title = data.getString("section");

            // 添加title
            final SectionBean sectionTitleBean = new SectionBean(true, title);
            sectionTitleBean.setId(id);
            sectionTitleBean.setIsMore(true);
            dataList.add(sectionTitleBean);

            final JSONArray goods = data.getJSONArray("goods");
            if (goods != null) {
                int goodsSize = goods.size();
                // 循环加载商品
                for (int j = 0; j < goodsSize; j++) {
                    final JSONObject contentItem = goods.getJSONObject(j);
                    final String goodsId = contentItem.getString("goodsId");
                    final String goodsName = contentItem.getString("name");
                    final String goodsThumb = contentItem.getString("thumb");

                    final SectionContentItemEntity itemEntity = new SectionContentItemEntity();
                    itemEntity.setmGoodsId(goodsId);
                    itemEntity.setmGoodsName(goodsName);
                    itemEntity.setMgoodsThumb(goodsThumb);
                    // 添加内容
                    dataList.add(new SectionBean(itemEntity));
                }
            }
        }
        return dataList;
    }

}
