package mall.online.com.fastec.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import mall.online.com.latte.delegate.LatteDelegate;
import mall.online.com.latte.net.RestClient;
import mall.online.com.latte.net.callback.IError;
import mall.online.com.latte.net.callback.IFailure;
import mall.online.com.latte.net.callback.ISuccess;

/**
 * Created by liWensheng on 2018/1/22.
 */

public class MallDelegate extends LatteDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        testRestClient();
    }

    private void testRestClient() {
        RestClient.builder()
                .url("http://news.baidu.com/")
//                .params("", "")
                .success(new ISuccess() {
                    @Override
                    public void onSuceess(String response) {
                        Toast.makeText(getContext(), response, Toast.LENGTH_LONG).show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {

                    }
                })
                .build()
                .get();
    }
}
