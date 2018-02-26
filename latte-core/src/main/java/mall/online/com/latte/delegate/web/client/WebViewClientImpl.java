package mall.online.com.latte.delegate.web.client;

import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import mall.online.com.latte.delegate.web.WebDelegate;
import mall.online.com.latte.delegate.web.route.Router;
import mall.online.com.latte.utils.log.LogUtil;

/**
 * Created by liWensheng on 2018/2/26.
 */

public class WebViewClientImpl extends WebViewClient{

    private final WebDelegate DELEGATE;

    public WebViewClientImpl(WebDelegate delegate) {
        this.DELEGATE = delegate;
    }


    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        LogUtil.i("shouldOverrideUrlLoading", url);
        return Router.getInstance().handleWebUrl(DELEGATE, url);
//        return false;
    }
}
