package mall.online.com.latte.delegate.web.client;

import android.graphics.Bitmap;
import android.os.Handler;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import mall.online.com.latte.app.Latte;
import mall.online.com.latte.delegate.web.WebDelegate;
import mall.online.com.latte.delegate.web.route.Router;
import mall.online.com.latte.ui.loader.LatteLoader;
import mall.online.com.latte.utils.log.LogUtil;

/**
 * Created by liWensheng on 2018/2/26.
 */

public class WebViewClientImpl extends WebViewClient{

    private final WebDelegate DELEGATE;
    private IPageLoadListener mIPageLoadListener = null;

    private static final Handler HANDLER = Latte.getHandler();

    public void setPageLoadListener(IPageLoadListener listener) {
        this.mIPageLoadListener = listener;
    }

    public WebViewClientImpl(WebDelegate delegate) {
        this.DELEGATE = delegate;
    }


    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        LogUtil.i("shouldOverrideUrlLoading", url);
        return Router.getInstance().handleWebUrl(DELEGATE, url);
//        return false;
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);

        if (mIPageLoadListener != null) {
            mIPageLoadListener.onLoadStart();
        }

        LatteLoader.showLoading(view.getContext());
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);

        if (mIPageLoadListener != null) {
            mIPageLoadListener.onLoadEnd();
        }

        HANDLER.postDelayed(new Runnable() {
            @Override
            public void run() {
                LatteLoader.stopLoading();
            }
        }, 1000);
    }
}
