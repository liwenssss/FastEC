package mall.online.com.latte.delegate.web;

import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * Created by liWensheng on 2018/2/26.
 */

public class WebViewInitializar {

    public WebView createWebView(WebView webView) {
        WebView.setWebContentsDebuggingEnabled(true);
        // 不允许滚动
        webView.setHorizontalScrollBarEnabled(false);
        webView.setVerticalScrollBarEnabled(false);
        // 允许截图
        webView.setDrawingCacheEnabled(true);
        // 屏蔽长按事件
        webView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });
        // 初始化WebSettings
        final WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        final String ua = settings.getUserAgentString();
        settings.setUserAgentString(ua + "ec");

        // 隐藏缩放控件
        settings.setBuiltInZoomControls(false);
        settings.setDisplayZoomControls(false);
        // 禁止缩放
        settings.setSupportZoom(false);
        // 文件权限
        settings.setAllowFileAccess(true);
        settings.setAllowFileAccessFromFileURLs(true);
        settings.setAllowUniversalAccessFromFileURLs(true);
        settings.setAllowContentAccess(true);
        // 缓存
        settings.setAppCacheEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);


        return webView;
    }
}
