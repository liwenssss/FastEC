package mall.online.com.latte.ec.main;

import android.graphics.Color;

import java.util.LinkedHashMap;

import mall.online.com.latte.delegate.bottom.BaseBottomDelegate;
import mall.online.com.latte.delegate.bottom.BottomItemDelagate;
import mall.online.com.latte.delegate.bottom.BottomTabBean;
import mall.online.com.latte.delegate.bottom.ItemBuilder;
import mall.online.com.latte.ec.main.cart.ShopCartDelegate;
import mall.online.com.latte.ec.main.discover.DiscoverDelegate;
import mall.online.com.latte.ec.main.index.IndexDelegate;
import mall.online.com.latte.ec.main.personal.PersonalDelegate;
import mall.online.com.latte.ec.main.sort.SortDelegate;

/**
 * Created by liWensheng on 2018/2/22.
 */

public class EcBottomDelegate extends BaseBottomDelegate {
    @Override
    public LinkedHashMap<BottomTabBean, BottomItemDelagate> setItems(ItemBuilder builder) {
        final LinkedHashMap<BottomTabBean, BottomItemDelagate> items = new LinkedHashMap<>();
        items.put(new BottomTabBean("{fa-home}", "主页"), new IndexDelegate());
        items.put(new BottomTabBean("{fa-sort}", "分类"), new SortDelegate());
        items.put(new BottomTabBean("{fa-compass}", "发现"), new DiscoverDelegate());
        items.put(new BottomTabBean("{fa-shopping-cart}", "购物车"), new ShopCartDelegate());
        items.put(new BottomTabBean("{fa-user}", "我的"), new PersonalDelegate());
        return builder.addItems(items).build();
    }

    @Override
    public int setIndexDelegate() {
        return 0;
    }

    @Override
    public int setClickedColor() {
        return Color.parseColor("#00cc99");
    }
}
