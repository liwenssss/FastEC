package mall.online.com.latte.ec.main.index.search;

import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import java.util.List;

import mall.online.com.latte.delegate.LatteDelegate;
import mall.online.com.latte.ec.R;
import mall.online.com.latte.ec.main.index.IndexRecyclerAdapter;
import mall.online.com.latte.ec.main.index.search.ans.SearchAnsDelegate;
import mall.online.com.latte.ui.recycler.MultipleFields;
import mall.online.com.latte.ui.recycler.MultipleItemEntity;
import mall.online.com.latte.ui.recycler.MultipleViewHolder;

/**
 * Created by liWensheng on 2018/4/27.
 */

public class SearchAdapter extends IndexRecyclerAdapter {
    private LatteDelegate DELEGATE = null;

    protected SearchAdapter(List<MultipleItemEntity> data, LatteDelegate latteDelegate) {
        super(data);
        DELEGATE = latteDelegate;
        addItemType(SearchItemType.ITEM_SEARCH, R.layout.item_search);
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        super.convert(holder, entity);
        switch (entity.getItemType()) {
            case SearchItemType.ITEM_SEARCH:
                final AppCompatTextView tvSearchItem = holder.getView(R.id.tv_search_item);
                final String history = entity.getField(MultipleFields.TEXT);
                tvSearchItem.setText(history);

                holder.getView(R.id.search_history).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SearchAnsDelegate searchAnsDelegate = SearchAnsDelegate.create(history);
                        DELEGATE.getSupportDelegate().start(searchAnsDelegate);
                    }
                });
                break;
            default:
                break;
        }
    }
}
