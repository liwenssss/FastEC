package mall.online.com.latte.ec.main.personal.order.after;

/**
 * Created by liWensheng on 2018/4/17.
 */

public interface IAfterResultListener {
    void onPaySuccess();

    void onPaying();

    void onPayFail();

    void onPayCancel();

    void onPayConnectError();
}
