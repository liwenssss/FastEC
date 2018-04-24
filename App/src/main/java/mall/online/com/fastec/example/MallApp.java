package mall.online.com.fastec.example;

import android.app.Application;

import com.facebook.stetho.Stetho;
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
                .withApiHost("http://114.67.145.163/RestServer/api/")
                .withInterceptor(new DebugInterceptor("index", R.raw.test))
                .withJavascriptInterface("ec")
                .configure();
        initStetho();
    }

    private void initStetho() {
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .build()
        );
    }
}
