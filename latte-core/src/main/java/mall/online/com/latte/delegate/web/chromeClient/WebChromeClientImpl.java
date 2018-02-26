package mall.online.com.latte.delegate.web.chromeClient;

import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/**
 * Created by liWensheng on 2018/2/26.
 */

public class WebChromeClientImpl  extends WebChromeClient{
    @Override
    public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
        return super.onJsAlert(view, url, message, result);
    }
}
