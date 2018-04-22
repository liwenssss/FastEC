package mall.online.com.latte.ec.main.cart.order;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import mall.online.com.latte.delegate.LatteDelegate;
import mall.online.com.latte.ec.R;
import mall.online.com.latte.ec.R2;
import mall.online.com.latte.ec.main.personal.order.OrderListDelegate;
import retrofit2.http.DELETE;

/**
 * Created by liWensheng on 2018/4/22.
 */

public class OrderDelegate extends LatteDelegate {

    @BindView(R2.id.order_thing_num)
    TextView Order_Thing_Num = null;

    private static final String ORDER_IDS = "ORDER_IDS";
    private ArrayList<String> orders;

    public static OrderDelegate create(ArrayList<String> orders) {
        final Bundle args = new Bundle();
        args.putStringArrayList(ORDER_IDS, orders);
        final OrderDelegate delegate = new OrderDelegate();
        delegate.setArguments(args);
        return delegate;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        if (args != null) {
            orders = args.getStringArrayList(ORDER_IDS);
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_order;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        if (orders != null) {
            final int num = orders.size();
            Order_Thing_Num.setText(Integer.toString(num));
        }
    }
}
