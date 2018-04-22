package mall.online.com.latte.ec.main.personal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import mall.online.com.latte.app.AccountManager;
import mall.online.com.latte.delegate.bottom.BottomItemDelagate;
import mall.online.com.latte.ec.R;
import mall.online.com.latte.ec.R2;
import mall.online.com.latte.ec.main.personal.list.ListAdapter;
import mall.online.com.latte.ec.main.personal.list.ListBean;
import mall.online.com.latte.ec.main.personal.list.ListItemType;
import mall.online.com.latte.ec.main.personal.order.OrderListDelegate;
import mall.online.com.latte.ec.main.personal.profile.UserProfileDelegate;
import mall.online.com.latte.ec.sign.SignInDelegate;
import mall.online.com.latte.ec.sign.SignUpDelegate;
import mall.online.com.latte.net.RestClient;
import mall.online.com.latte.net.callback.ISuccess;
import mall.online.com.latte.utils.storage.PreferenceUtils;

/**
 * Created by liWensheng on 2018/3/2.
 */

public class PersonalDelegate extends BottomItemDelagate{

    @BindView(R2.id.rv_personal_setting)
    RecyclerView mRvSettings = null;

    @BindView(R2.id.user_name)
    TextView UserName = null;

    public static final String ORDER_TYPE = "ORDER_TYPE";

    private Bundle mArgs = null;

    private void SetUserName() {
        RestClient.builder()
                .url("http://139.199.5.153:3000/ec/users?phone="+ PreferenceUtils.getCustomAppProfile("current").toString())
                .success(new ISuccess() {
                    @Override
                    public void onSuceess(String response) {
                        String name = JSON.parseObject(response).getString("name");
                        UserName.setText(name);
                    }
                }).build().get();
    }

    @OnClick(R2.id.tv_all_order)
    void onClickAllOrder() {
        mArgs.putString(ORDER_TYPE, "all");
        startOrderListByType();
    }

    @OnClick(R2.id.ll_pay)
    void onClickPay() {
        mArgs.putString(ORDER_TYPE, "pay");
        startOrderListByType();
    }

    @OnClick(R2.id.ll_receive)
    void onClickReceive() {
        mArgs.putString(ORDER_TYPE, "receive");
        startOrderListByType();
    }

    @OnClick(R2.id.ll_evaluate)
    void onClickEvaluate() {
        mArgs.putString(ORDER_TYPE, "evaluate");
        startOrderListByType();
    }

    @OnClick(R2.id.ll_after_market)
    void onClickAfterMarket() {
        mArgs.putString(ORDER_TYPE, "after");
        startOrderListByType();
    }

    @OnClick(R2.id.img_user_avatar)
    void onClickAvatar() {
        getParentDelegate().getSupportDelegate().start(new UserProfileDelegate());
    }

    @OnClick(R2.id.exit_sign_up)
    void exit() {
        AccountManager.setSignState(false);
        getParentDelegate().getSupportDelegate().startWithPop(new SignInDelegate());
    }

    private void startOrderListByType() {
        final OrderListDelegate delegate = new OrderListDelegate();
        delegate.setArguments(mArgs);
        getParentDelegate().getSupportDelegate().start(delegate);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mArgs = new Bundle();
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_personal;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        SetUserName();
        final ListBean address = new ListBean. Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(1)
                .setText("收货地址")
                .build();

        final ListBean system = new ListBean. Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(2)
                .setText("系统设置")
                .build();

        final ListBean collection = new ListBean. Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(3)
                .setText("我的收藏")
                .build();

        final List<ListBean> data = new ArrayList<>();
        data.add(address);
        data.add(system);
        data.add(collection);

        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRvSettings.setLayoutManager(manager);
        final ListAdapter adapter = new ListAdapter(data);
        mRvSettings.setAdapter(adapter);
    }
}
