package mall.online.com.latte.ec.main.personal.profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import mall.online.com.latte.delegate.LatteDelegate;
import mall.online.com.latte.ec.R;
import mall.online.com.latte.ec.R2;
import mall.online.com.latte.ec.main.personal.list.ListAdapter;
import mall.online.com.latte.ec.main.personal.list.ListBean;
import mall.online.com.latte.ec.main.personal.list.ListItemType;
import mall.online.com.latte.ec.main.personal.profile.settings.NameDelegate;

/**
 * Created by liWensheng on 2018/3/2.
 */

public class UserProfileDelegate extends LatteDelegate {

    @BindView(R2.id.rv_user_profile)
    RecyclerView recyclerView = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_user_profile;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        final ListBean image = new ListBean. Builder()
                .setItemType(ListItemType.ITEM_AVATAR)
                .setId(1)
                .setImageUrl("https://ws2.sinaimg.cn/large/006tNc79gy1foyt6xdhh0j30e60e6q31.jpg")
                .build();

        final ListBean name = new ListBean. Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(2)
                .setText("姓名")
                .setValue("lws")
                .setDelegate(new NameDelegate())
                .build();

        final ListBean gender = new ListBean. Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(3)
                .setText("性别")
                .setValue("男")
                .build();

        final ListBean birth = new ListBean. Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(4)
                .setText("生日")
                .setValue("1996.4.5")
                .build();

        final List<ListBean> data = new ArrayList<>();
        data.add(image);
        data.add(name);
        data.add(gender);
        data.add(birth);

        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        final ListAdapter adapter = new ListAdapter(data);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new UserProfileClickListener(this));
    }
}
