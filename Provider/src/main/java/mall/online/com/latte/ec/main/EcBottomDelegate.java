package mall.online.com.latte.ec.main;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;

import java.util.LinkedHashMap;

import mall.online.com.latte.delegate.bottom.BaseBottomDelegate;
import mall.online.com.latte.delegate.bottom.BottomItemDelagate;
import mall.online.com.latte.delegate.bottom.BottomTabBean;
import mall.online.com.latte.delegate.bottom.ItemBuilder;
import mall.online.com.latte.ec.main.cart.ShopCartDelegate;
import mall.online.com.latte.ec.main.index.IndexDelegate;
import mall.online.com.latte.ec.main.personal.PersonalDelegate;
import mall.online.com.latte.ec.main.sort.SortDelegate;
import mall.online.com.latte.utils.log.LogUtil;

/**
 * Created by liWensheng on 2018/2/22.
 */

public class EcBottomDelegate extends BaseBottomDelegate {
    private int id = 0;
    private static String ID ="ID";

    public static EcBottomDelegate create(int rid) {
        final Bundle args = new Bundle();
        args.putInt(ID, rid);
        final EcBottomDelegate delegate = new EcBottomDelegate();
        delegate.setArguments(args);
        return delegate;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        if (args != null) {
            id = args.getInt(ID);
        }
    }

    @Override
    public LinkedHashMap<BottomTabBean, BottomItemDelagate> setItems(ItemBuilder builder) {
        final LinkedHashMap<BottomTabBean, BottomItemDelagate> items = new LinkedHashMap<>();
        items.put(new BottomTabBean("{fa-home}", "主页"), new IndexDelegate());
        items.put(new BottomTabBean("{fa-sort}", "分类"), new SortDelegate());
        items.put(new BottomTabBean("{fa-shopping-cart}", "购物车"), new ShopCartDelegate());
        items.put(new BottomTabBean("{fa-user}", "我的"), new PersonalDelegate());
        return builder.addItems(items).build();
    }

    @Override
    public int setIndexDelegate() {
        return id;
    }

    @Override
    public int setClickedColor() {
        return Color.parseColor("#00cc99");
    }

    @Override
    public boolean onBackPressedSupport() {
        return false;
    }
}
