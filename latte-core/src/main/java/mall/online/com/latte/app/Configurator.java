package mall.online.com.latte.app;

import java.util.WeakHashMap;

/**
 * Created by liWensheng on 2018/1/20.
 */

public class Configurator {
    private static final WeakHashMap<String, Object> LATTE_CONFIGS = new WeakHashMap<>();

    private Configurator() {
        //初始化配置
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(), false);
    }

    // 静态内部类单例模式的初始化
    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }

    final WeakHashMap<String, Object> getFunctionConfigs() {
        return LATTE_CONFIGS;
    }

    public static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * set config is ready
     */
    public final void  configure() {
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(), true);
    }

    /**
     * set API_HOST
     * @param host
     * @return
     */
    public final Configurator withApiHost(String host){
        LATTE_CONFIGS.put(ConfigType.API_HOST.name(), host);
        return this;
    }

    /**
     * check if config is ready
     */
    private void checkConfiguration() {
        final boolean isReady = (boolean)LATTE_CONFIGS.get(ConfigType.CONFIG_READY.name());
        if (!isReady) {
            throw new RuntimeException("Configuration is not ready, call configure");
        }
    }

    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Enum<ConfigType> key) {
        checkConfiguration();
        return (T) LATTE_CONFIGS.get(key.name());
    }
}
