package mall.online.com.latte.net.interceptors;

import java.io.IOException;
import java.util.LinkedHashMap;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by liWensheng on 2018/1/29.
 */

public abstract class BaseInterceptor implements Interceptor {
//    @Override
//    public Response intercept(Chain chain) throws IOException {
//        return null;
//    }

    /**
     * 获取url参数对
     * @param chain
     * @return
     */
    protected LinkedHashMap<String, String> getUrlParameters(Chain chain) {
        final HttpUrl url = chain.request().url();
        int size = url.querySize();
        final LinkedHashMap<String, String> params = new LinkedHashMap<>();

        for (int i = 0; i < size; i++) {
            params.put(url.queryParameterName(i), url.queryParameterValue(i));
        }

        return params;
    }

    /**
     * 通过key值获取url中的参数
     * @param chain
     * @param key
     * @return
     */
    protected String getUrlParameters(Chain chain, String key) {
        final Request request = chain.request();
        return request.url().queryParameter(key);
    }

    /**
     * 获取POST请求中的参数对
     * @param chain
     * @return
     */
    protected LinkedHashMap<String, String> getBodyParameters(Chain chain) {
        final FormBody formBody = (FormBody) chain.request().body();
        final LinkedHashMap<String, String> params = new LinkedHashMap<>();
        int size = formBody.size();

        for (int i = 0; i < size; i++) {
            params.put(formBody.name(i), formBody.value(i));
        }

        return params;
    }

    /**
     * 通过key获取POST请求中的参数
     * @param chain
     * @param key
     * @return
     */
    private String getBodyParameters(Chain chain, String key) {
        return getBodyParameters(chain).get(key);
    }
}
