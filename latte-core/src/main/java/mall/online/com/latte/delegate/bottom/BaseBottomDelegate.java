package mall.online.com.latte.delegate.bottom;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import mall.online.com.latte.R;
import mall.online.com.latte.R2;
import mall.online.com.latte.delegate.LatteDelegate;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by liWensheng on 2018/2/22.
 */

public abstract class BaseBottomDelegate extends LatteDelegate implements View.OnClickListener{
    private final ArrayList<BottomTabBean> TAB_BEANS = new ArrayList<>();
    private final ArrayList<BottomItemDelagate> ITEM_DELEGATES = new ArrayList<>();
    private final LinkedHashMap<BottomTabBean, BottomItemDelagate> ITEMS = new LinkedHashMap<>();
    private int mCurrentDelegate = 0;
    private int mIndexDelegate = 0;
    private int mClickColor = Color.RED;

    public abstract LinkedHashMap<BottomTabBean, BottomItemDelagate> setItems(ItemBuilder builder);

    public abstract int setIndexDelegate();

    public abstract int setClickedColor();

    @BindView(R2.id.bottom_bar)
    LinearLayoutCompat mBottomBar = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_bottom;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIndexDelegate = setIndexDelegate();

        if (setClickedColor() != 0) {
            mClickColor = setClickedColor();
        }

        final ItemBuilder builder = ItemBuilder.builder();
        final LinkedHashMap<BottomTabBean, BottomItemDelagate> items = setItems(builder);
        ITEMS.putAll(items);

        for (Map.Entry<BottomTabBean, BottomItemDelagate> item: ITEMS.entrySet()) {
            final BottomTabBean key = item.getKey();
            final BottomItemDelagate value = item.getValue();
            TAB_BEANS.add(key);
            ITEM_DELEGATES.add(value);
        }
    }

    /**
     * 绑定view，初始化tab，设置每个tab点击后的事件
     * @param savedInstanceState
     * @param rootView
     */
    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        final int size = ITEMS.size();
        for (int i = 0; i < size; i++) {
            LayoutInflater.from(getContext()).inflate(R.layout.bottom_item_icon_text_layout, mBottomBar);
            final RelativeLayout item = (RelativeLayout) mBottomBar.getChildAt(i);

            // 设置每个item点击事件
            item.setTag(i);
            item.setOnClickListener(this);

            // 初始化数据
            final IconTextView itemIcon = (IconTextView) item.getChildAt(0);
            final AppCompatTextView itemTitle = (AppCompatTextView) item.getChildAt(1);
            final BottomTabBean bean = TAB_BEANS.get(i);
            itemIcon.setText(bean.getIcon());
            itemTitle.setText(bean.getTitle());
            if (i == mIndexDelegate) { // 设置当前tab颜色
                itemIcon.setTextColor(mClickColor);
                itemTitle.setTextColor(mClickColor);
            }
        }

        final SupportFragment[] delegateArray = ITEM_DELEGATES.toArray(new SupportFragment[size]);
        loadMultipleRootFragment(R.id.bottom_bar_delegate_container, mIndexDelegate, delegateArray);
    }

    /**
     * 重置tab颜色
     */
    private void resetColor() {
        final int count = mBottomBar.getChildCount();

        for (int i = 0; i < count; i++) {
            final RelativeLayout item = (RelativeLayout) mBottomBar.getChildAt(i);
            final IconTextView itemIcon = (IconTextView) item.getChildAt(0);
            final AppCompatTextView itemTitle = (AppCompatTextView) item.getChildAt(1);
            itemIcon.setTextColor(Color.GRAY);
            itemTitle.setTextColor(Color.GRAY);
        }
    }

    /**
     * 点击tab事件，隐藏当前fragment，显示对应新的fragment，并将tab做对应变化
     * @param v
     */
    @Override
    public void onClick(View v) {
        final int tag = (int) v.getTag();
        resetColor();
        final RelativeLayout item = (RelativeLayout) v;

        final IconTextView itemIcon = (IconTextView) item.getChildAt(0);
        final AppCompatTextView itemTitle = (AppCompatTextView) item.getChildAt(1);
        itemIcon.setTextColor(mClickColor);
        itemTitle.setTextColor(mClickColor);

        // 此时显示tag对应的fragment，并把当前fragment隐藏
        showHideFragment(ITEM_DELEGATES.get(tag), ITEM_DELEGATES.get(mCurrentDelegate));
        mCurrentDelegate = tag;
    }
}
