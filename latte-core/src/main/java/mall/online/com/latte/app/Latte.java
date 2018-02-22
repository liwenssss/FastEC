package mall.online.com.latte.app;

import android.content.Context;

import java.util.HashMap;

/**
 * Created by liWensheng on 2018/1/20.
 */

public final class Latte {
    public static Configurator init(Context context) {
        Configurator.getInstance()
                .getFunctionConfigs()
                .put(ConfigKeys.APPLICATION_CONTEXT, context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static Configurator getConfigurator() {
        return Configurator.getInstance();
    }

    public static <T> T getConfiguration(Object key) {
        return getConfigurator().getConfiguration(key);
    }

    public static Context getApplicationContext() {
        return getConfiguration(ConfigKeys.APPLICATION_CONTEXT);
    }

//    public static HashMap<String, Object> getConfigurations() {
//        return Configurator.getInstance().getFunctionConfigs();
//    }



//    public static Context getApplication() {
//        return (Context) getConfigurations().get(ConfigKeys.APPLICATION_CONTEXT.name());
//    }
}
