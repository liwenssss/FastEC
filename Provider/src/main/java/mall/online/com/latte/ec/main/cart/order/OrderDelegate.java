package mall.online.com.latte.ec.main.cart.order;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import org.greenrobot.greendao.annotation.Id;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.OnClick;
import mall.online.com.latte.delegate.LatteDelegate;
import mall.online.com.latte.ec.R;
import mall.online.com.latte.ec.R2;
import mall.online.com.latte.ec.main.EcBottomDelegate;
import mall.online.com.latte.ec.main.personal.order.OrderListDelegate;
import mall.online.com.latte.ec.pay.FastPay;
import mall.online.com.latte.net.RestClient;
import mall.online.com.latte.net.callback.ISuccess;
import mall.online.com.latte.utils.log.LogUtil;
import mall.online.com.latte.utils.storage.PreferenceUtils;
import retrofit2.http.DELETE;

/**
 * Created by liWensheng on 2018/4/22.
 */

public class OrderDelegate extends LatteDelegate {

    @BindView(R2.id.order_name)
    TextView OrderName = null;

    @BindView(R2.id.order_phone)
    TextView OrderPhone = null;

    @BindView(R2.id.order_position)
    TextView OrderPosition = null;

    @BindView(R2.id.order_thing_num)
    TextView Order_Thing_Num = null;

    @BindView(R2.id.order_arrive_time)
    TextView Arrive_Time = null;

    @BindView(R2.id.order_price)
    TextView Order_Price = null;

    @BindView(R2.id.order_total_price)
    TextView Order_Total_Price = null;

    private static final String ORDER_IDS = "ORDER_IDS";
    private static final String TOTAL_PRICE = "TOTAL_PRICE";
    private ArrayList<String> orders;
    private double Total_Price = 0;
    private final String phoneId = PreferenceUtils.getCustomAppProfile("current");

    private LatteDelegate DELEGATE = null;


    public static OrderDelegate create(ArrayList<String> orders, double total, LatteDelegate latteDelegate) {
        final Bundle args = new Bundle();
        args.putStringArrayList(ORDER_IDS, orders);
        args.putDouble(TOTAL_PRICE, total);
        final OrderDelegate delegate = new OrderDelegate();
        delegate.setDELEGATE(latteDelegate);
        delegate.setArguments(args);
        return delegate;
    }

    public void setDELEGATE(LatteDelegate latteDelegate) {
        this.DELEGATE = latteDelegate;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        if (args != null) {
            orders = args.getStringArrayList(ORDER_IDS);
            Total_Price = args.getDouble(TOTAL_PRICE);
        }
    }

    @OnClick(R2.id.order_receiver_info)
    public void editReceiver() {
        getSupportDelegate().start(new ReceiveInfo());
    }

    @OnClick(R2.id.order_info)
    public void OrderInfo() {
        OrderDetailDelegate orderDetailDelegate = OrderDetailDelegate.create(orders);
        getSupportDelegate().start(orderDetailDelegate);
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_order;
    }

    @SuppressLint({"DefaultLocale", "SetTextI18n"})
    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        if (orders != null) {
            final int num = orders.size();
            Order_Thing_Num.setText(Integer.toString(num));
        }
        // 设置到货日期
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, +2);
        final int month = calendar.get(Calendar.MONTH)+1;
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        final String date = Integer.toString(month) + " 月 " + Integer.toString(day) + " 日";
        Arrive_Time.setText(date);
        // 设置价钱
        Order_Price.setText(String.format("%.2f", Total_Price));
        Order_Total_Price.setText(String.format("%.2f", Total_Price+5));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onResume() {
        super.onResume();
        if (PreferenceUtils.getAppFlag(phoneId+"position")) {
            OrderName.setText(PreferenceUtils.getCustomAppProfile(phoneId+"name"));
            OrderPhone.setText(PreferenceUtils.getCustomAppProfile(phoneId+"phone"));
            OrderPosition.setText(PreferenceUtils.getCustomAppProfile(phoneId+"diqu")
            +"\n"+PreferenceUtils.getCustomAppProfile(phoneId+"xiangxi"));
        }
    }


    @OnClick(R2.id.tv_shop_cart_pay)
    public void pay() {
        String id = "";
        final int size = orders.size();
        for (int i = 0; i < size; i++) {
            id = id + orders.get(i)+ "|";
        }

        createOrder();

//        FastPay.create(this, id).beginPayDialog();
        RestClient.builder()
                .url("http://139.199.5.153:3000/ec/cart/delete?id="+ id)
                .success(new ISuccess() {
                    @Override
                    public void onSuceess(String response) {
                        Toast.makeText(getContext(), "支付成功", Toast.LENGTH_SHORT).show();

                        EcBottomDelegate ecBottomDelegate = EcBottomDelegate.create(2);
                        getSupportDelegate().start(ecBottomDelegate, SINGLETASK);
                    }
                }).build().post();
    }

    @Override
    public void onNewBundle(Bundle args) {
        super.onNewBundle(args);
    }

    private void createOrder() {
        String id = "";
        final int size = orders.size();
        for (int i = 0; i < size; i++) {
            id = id + orders.get(i)+ ",";
        }

        Calendar calendar = Calendar.getInstance();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("MM月dd日 HH时mm分");
        final String date =dateFormat.format(calendar.getTime());

        final String mUserPhone = PreferenceUtils.getCustomAppProfile("current");
        final String mName = PreferenceUtils.getCustomAppProfile(phoneId+"name");
        final String mPhone = PreferenceUtils.getCustomAppProfile(phoneId+"phone");
        final String mDiqu = PreferenceUtils.getCustomAppProfile(phoneId+"diqu");
        final String mXiangXi = PreferenceUtils.getCustomAppProfile(phoneId+"xiangxi");
        final int mCount = orders.size();
        final String mIds = id;
        final String mTime = date;
        final String mPrice = Double.toString(Total_Price+5);

        final String parms = "userphone="+mUserPhone + "&"
                + "name=" + mName + "&"
                + "phone=" + mPhone + "&"
                + "diqu=" + mDiqu + "&"
                + "xiangxi=" + mXiangXi + "&"
                + "count=" + Integer.toString(mCount) + "&"
                + "price=" + mPrice + "&"
                + "ids=" + mIds + "&"
                + "time=" + mTime;

        RestClient.builder()
                .loader(getContext())
                .url("http://139.199.5.153:3000/ec/orders?"+parms)
                .success(new ISuccess() {
                    @Override
                    public void onSuceess(String response) {
                        LogUtil.i("订单", "订单添加成");
                    }
                }).build().post();
    }
}
