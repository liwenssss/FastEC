package mall.online.com.latte.ec.pay;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import mall.online.com.latte.delegate.LatteDelegate;
import mall.online.com.latte.ec.R;
import mall.online.com.latte.ec.main.EcBottomDelegate;
import mall.online.com.latte.ec.main.cart.order.OrderDelegate;

/**
 * Created by liWensheng on 2018/4/17.
 */

public class FastPay implements View.OnClickListener {
    //设置支付回调监听
    private IAlPayResultListener mIAlPayResultListener = null;
    private Activity mActivity = null;

    private AlertDialog mDialog = null;
    private int mOrderID = -1;

    private LatteDelegate DELEGATE = null;

    private FastPay(LatteDelegate delegate, LatteDelegate parentDelegate) {
        this.mActivity = delegate.getProxyActivity();
        this.mDialog = new AlertDialog.Builder(delegate.getContext()).create();
        this.DELEGATE = parentDelegate;
    }

    public static FastPay create(LatteDelegate delegate, LatteDelegate parent) {
        return new FastPay(delegate, parent);
    }

    public void beginPayDialog() {
        mDialog.show();
        final Window window = mDialog.getWindow();
        if (window != null) {
            window.setContentView(R.layout.dialog_pay_panel);
            window.setGravity(Gravity.BOTTOM);
            window.setWindowAnimations(R.style.anim_panel_up_from_bottom);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            //设置属性
            final WindowManager.LayoutParams params = window.getAttributes();
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            params.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
            window.setAttributes(params);

            window.findViewById(R.id.btn_dialog_pay_alpay).setOnClickListener(this);
            window.findViewById(R.id.btn_dialog_pay_wechat).setOnClickListener(this);
            window.findViewById(R.id.btn_dialog_pay_cancel).setOnClickListener(this);
        }
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_dialog_pay_cancel) {
            mDialog.cancel();
        }else  {
            DELEGATE.getSupportDelegate().start(new OrderDelegate());
        }
    }
}
