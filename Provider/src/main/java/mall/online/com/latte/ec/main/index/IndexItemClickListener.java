package mall.online.com.latte.ec.main.index;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;

import mall.online.com.latte.delegate.LatteDelegate;
import mall.online.com.latte.ec.detail.GoodsDetailDelegate;
import mall.online.com.latte.ec.main.EcBottomDelegate;
import mall.online.com.latte.ui.recycler.ItemType;
import mall.online.com.latte.ui.recycler.MultipleFields;
import mall.online.com.latte.ui.recycler.MultipleItemEntity;

/**
 * Created by liWensheng on 2018/2/25.
 * 首页点击事件
 */

public class IndexItemClickListener extends SimpleClickListener {

    private final LatteDelegate DELEGATE;

    public IndexItemClickListener(LatteDelegate delegate) {
        this.DELEGATE = delegate;
    }

    public static SimpleClickListener create(LatteDelegate delegate) {
        return new IndexItemClickListener(delegate);
    }


    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        final MultipleItemEntity entity = (MultipleItemEntity) baseQuickAdapter.getData().get(position);
        final int type = entity.getField(MultipleFields.ITEM_TYPE);
        if (type != ItemType.TEXT && type != ItemType.BANNER && type != ItemType.BAR) {
            final String id = entity.getField(MultipleFields.ID);
            final GoodsDetailDelegate delegate = GoodsDetailDelegate.create(id);
            DELEGATE.getSupportDelegate().start(delegate);
        }
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
