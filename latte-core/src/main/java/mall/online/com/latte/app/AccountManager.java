package mall.online.com.latte.app;

import mall.online.com.latte.utils.storage.PreferenceUtils;

/**
 * Created by liWensheng on 2018/2/13.
 * 管理用户信息
 */

public class AccountManager {
    private enum SignTag {
        SIGN_TAG
    }

    /**
     * 设置登录状态
     * @param state
     */
    public static void setSignState(boolean state) {
        PreferenceUtils.setAppFlag(SignTag.SIGN_TAG.name(), state);
    }

    /**
     * 检查是否登录
     * @return
     */
    private static boolean isSignIn() {
        return PreferenceUtils.getAppFlag(SignTag.SIGN_TAG.name());
    }

    /**
     * 检查用户状态，执行相关操作
     * @param checker
     */
    public static void checkAccount(IUserChecker checker) {
        if (isSignIn()) {
            checker.onSignIn();
        } else {
            checker.onNotSignIn();
        }
    }
}
