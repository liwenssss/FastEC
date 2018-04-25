package mall.online.com.latte.ec.main.index.bar.Cloth;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;


import butterknife.BindView;
import mall.online.com.latte.delegate.LatteDelegate;
import mall.online.com.latte.ec.R;
import mall.online.com.latte.ec.R2;
import mall.online.com.latte.net.RestClient;
import mall.online.com.latte.net.callback.IError;
import mall.online.com.latte.net.callback.IFailure;
import mall.online.com.latte.net.callback.ISuccess;
import mall.online.com.latte.ui.recycler.BaseDecoration;
import mall.online.com.latte.ui.recycler.DataConverter;
import mall.online.com.latte.utils.log.LogUtil;

/**
 * Created by liWensheng on 2018/4/25.
 */

public class ClothDelegate extends LatteDelegate {


    @BindView(R2.id.cloth_rv)
    RecyclerView mRecyclerView = null;


    private void initRecyclerView() {
        final GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 4);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(BaseDecoration.create(ContextCompat.getColor(getContext(), R.color.app_background), 2));
    }

    private ClothDelegate getDelegate() {
        return this;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_cloth;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

        initRecyclerView();

        RestClient.builder()
                .loader(getContext())
                .url("http://139.199.5.153:3000/ec/cloth")
                .success(new ISuccess() {
                    @Override
                    public void onSuceess(String response) {

                        final JSONObject object = JSON.parseObject(response);
                        final DataConverter converter = new ClothDataConverter().setJsonData(response);
                        LogUtil.i("cloth", response);
                        final ClothAdapter adapter = new ClothAdapter(converter.convert(), getDelegate());
                        mRecyclerView.setAdapter(adapter);

                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        LogUtil.i("cloth", "fail");
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        LogUtil.i("cloth", msg);
                    }
                })
                .build().get();
    }
}
