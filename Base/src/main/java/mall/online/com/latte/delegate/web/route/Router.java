package mall.online.com.latte.delegate.web.route;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.webkit.URLUtil;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import mall.online.com.latte.delegate.LatteDelegate;
import mall.online.com.latte.delegate.web.WebDelegate;
import mall.online.com.latte.delegate.web.WebDelegateImpl;
import mall.online.com.latte.utils.log.LogUtil;

/**
 * Created by liWensheng on 2018/2/26.
 * web内容拦截
 */

public class Router {
    private Router() {

    }

    private static class Holder{
        private static final Router INSTANCE = new Router();
    }

    public static Router getInstance() {
        return Holder.INSTANCE;
    }

    public final boolean handleWebUrl(WebDelegate delegate, String url) {
        if (url.contains("tel:")) { // 如果是电话协议
            callPhone(delegate.getContext(), url);
            return true;
        }

        final LatteDelegate topDelegate = delegate.getTopDelegate();

        final WebDelegateImpl webDelegate = WebDelegateImpl.create(url);
        topDelegate.getSupportDelegate().start(webDelegate);

        return true;
    }

    private void loadWebPage(final WebView webView, final String url) {
        if (webView != null) {
            LogUtil.i("loadWebPage", url);
            webView.loadUrl(url);

        } else {
            throw new NullPointerException("WebView is null!");
        }
    }

    private void loadLocalPage(WebView webView, String url) {
        LogUtil.i("loadLocalPage", url);
        loadWebPage(webView, "file:///android_asset/" + url);
    }

    private void loadPage(WebView webView, String url) {
        if (URLUtil.isNetworkUrl(url) || URLUtil.isAssetUrl(url)) {
            loadWebPage(webView, url);
        } else {
            loadLocalPage(webView, url);
        }
    }

    public final void loadPage(WebDelegate delegate, String url) {
        loadPage(delegate.getWebView(), url);
    }

    /**
     * 电话协议执行
     * @param context
     * @param uri
     */
    private void callPhone(Context context, String uri) {
        final Intent intent = new Intent(Intent.ACTION_DIAL);
        final Uri data = Uri.parse(uri);
        intent.setData(data);
        ContextCompat.startActivity(context, intent, null);
    }
}
