package mall.online.com.fastec.example;

import android.app.Application;

import com.joanzapata.iconify.fonts.FontAwesomeModule;

import mall.online.com.latte.app.Latte;
import mall.online.com.latte.ec.icon.FontEcModule;
import mall.online.com.latte.net.interceptors.DebugInterceptor;

/**
 * Created by liWensheng on 2018/1/21.
 */

public class MallApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .withApiHost("http://127.0.0.1/")
                .withInterceptor(new DebugInterceptor("index", R.raw.test))
                .configure();
    }
}
