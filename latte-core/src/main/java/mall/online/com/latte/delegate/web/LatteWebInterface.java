package mall.online.com.latte.delegate.web;

import android.webkit.JavascriptInterface;

import com.alibaba.fastjson.JSON;

import mall.online.com.latte.delegate.web.event.Event;
import mall.online.com.latte.delegate.web.event.EventManager;

/**
 * Created by liWensheng on 2018/2/26.
 */

final class LatteWebInterface {
    private final WebDelegate DELEGATE;

    public LatteWebInterface(WebDelegate delegate) {
        DELEGATE = delegate;
    }

    static LatteWebInterface create(WebDelegate delegate) {
        return new LatteWebInterface(delegate);
    }

    @SuppressWarnings("unused")
    @JavascriptInterface
    public String event(String params) {
        final String action = JSON.parseObject(params).getString("action");
        final Event event = EventManager.getInstance().createEvent(action);

        if (event != null) {
            event.setAction(action);
            event.setDelegate(DELEGATE);
            event.setContext(DELEGATE.getContext());
            event.setUrl(DELEGATE.getUrl());
            return event.execute(params);
        }
        return null;
    }
}
