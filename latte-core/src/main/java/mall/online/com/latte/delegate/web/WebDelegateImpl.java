package mall.online.com.latte.delegate.web;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import mall.online.com.latte.delegate.web.chromeClient.WebChromeClientImpl;
import mall.online.com.latte.delegate.web.client.WebViewClientImpl;
import mall.online.com.latte.delegate.web.route.RouteKeys;
import mall.online.com.latte.delegate.web.route.Router;

/**
 * Created by liWensheng on 2018/2/26.
 */

public class WebDelegateImpl extends WebDelegate  {

    public static WebDelegateImpl create(String url) {
        final Bundle args = new Bundle();
        args.putString(RouteKeys.URL.name(), url);
        final WebDelegateImpl delegate = new WebDelegateImpl();
        delegate.setArguments(args);
        return delegate;
    }



    @Override
    public Object setLayout() {
        return getWebView();
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        if (getUrl() != null) { // 模拟原生Web跳转并加载
            Router.getInstance().loadPage(this, getUrl());

        }
    }

    @Override
    public IWebViewInitializer setInitializer() {
        return this;
    }

    @Override
    public WebView initWebView(WebView webView) {
        return new WebViewInitializar().createWebView(webView);
    }

    @Override
    public WebViewClient initWebViewClient() {
        final WebViewClientImpl client = new WebViewClientImpl(this);
        return client;
    }

    @Override
    public WebChromeClient intiWebChromeClient() {
        return new WebChromeClientImpl();
    }
}
