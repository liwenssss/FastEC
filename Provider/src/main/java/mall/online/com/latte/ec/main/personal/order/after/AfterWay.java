package mall.online.com.latte.ec.main.personal.order.after;

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
import mall.online.com.latte.net.RestClient;
import mall.online.com.latte.net.callback.ISuccess;

/**
 * Created by liWensheng on 2018/4/17.
 */

public class AfterWay implements View.OnClickListener {
    //设置支付回调监听
    private IAfterResultListener mIAfterResultListener = null;
    private Activity mActivity = null;

    private AlertDialog mDialog = null;
    private LatteDelegate DELEGATE = null;


    private AfterWay(LatteDelegate delegate) {
        this.DELEGATE = delegate;
        this.mActivity = delegate.getProxyActivity();
        this.mDialog = new AlertDialog.Builder(delegate.getContext()).create();
    }

    public static AfterWay create(LatteDelegate delegate) {
        return new AfterWay(delegate);
    }

    public void beginPayDialog() {
        mDialog.show();
        final Window window = mDialog.getWindow();
        if (window != null) {
            window.setContentView(R.layout.dialog_after);
            window.setGravity(Gravity.BOTTOM);
            window.setWindowAnimations(R.style.anim_panel_up_from_bottom);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            //设置属性
            final WindowManager.LayoutParams params = window.getAttributes();
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            params.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
            window.setAttributes(params);

            window.findViewById(R.id.after_tuihuo).setOnClickListener(this);
            window.findViewById(R.id.after_huanhuo).setOnClickListener(this);
            window.findViewById(R.id.after_cancle).setOnClickListener(this);
        }
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.after_cancle) {
            mDialog.cancel();
        }else  {
            mDialog.cancel();
        }
    }
}
