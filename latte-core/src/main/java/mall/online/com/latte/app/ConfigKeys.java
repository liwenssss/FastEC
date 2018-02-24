package mall.online.com.latte.app;

/**
 * Created by liWensheng on 2018/1/20.
 * 应用程序内唯一单例，只能被初始化一次
 */

public enum ConfigKeys {
    API_HOST,               //网络请求域名
    APPLICATION_CONTEXT,    //全局上下文
    CONFIG_READY,           //控制初始化及配置的完成
    ICON,                    //存取个人初始化
    LOADER_DELAYED,
    INTERCEPTOR,
    HANDLER,
    ACTIVITY
}
