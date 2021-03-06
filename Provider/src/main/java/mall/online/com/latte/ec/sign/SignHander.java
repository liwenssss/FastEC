package mall.online.com.latte.ec.sign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import org.greenrobot.greendao.annotation.Id;

import mall.online.com.latte.app.AccountManager;
import mall.online.com.latte.utils.storage.PreferenceUtils;

/**
 * Created by liWensheng on 2018/2/11.
 */

public class SignHander {
    public static void onSignUp(String phone, ISignListener signListener) {
//        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
//        final String name = profileJson.getString("name");

//        final UserProfile profile = new UserProfile(userId, name, avatar, gender, address);
//        DatabaseManager.getInstance().getDao().insert(profile);

        // 已经注册并登录成功
        PreferenceUtils.addCustomAppProfile("current", phone);
        AccountManager.setSignState(true);
        signListener.onSignUpSuccess();
    }

    public static void onSignIn(String phone, ISignListener signListener) {
//        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
//        final Long userId = profileJson.getLong("userId");
//        final String name = profileJson.getString("name");
//        final String avatar = profileJson.getString("avatar");
//        final String gender = profileJson.getString("gender");
//        final String address = profileJson.getString("address");

//        final UserProfile profile = new UserProfile(userId, name, avatar, gender, address);
//        DatabaseManager.getInstance().getDao().delete(profile);
//        DatabaseManager.getInstance().getDao().insert(profile);

        // 已经注册并登录成功
        PreferenceUtils.addCustomAppProfile("current", phone);
        AccountManager.setSignState(true);
        signListener.onSignInSuccess();
    }
}
