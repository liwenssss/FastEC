package mall.online.com.latte.ec.main.index.message;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import butterknife.BindView;
import mall.online.com.latte.delegate.LatteDelegate;
import mall.online.com.latte.ec.R;
import mall.online.com.latte.ec.R2;
import mall.online.com.latte.net.RestClient;
import mall.online.com.latte.net.callback.ISuccess;
import mall.online.com.latte.ui.recycler.MultipleItemEntity;
import mall.online.com.latte.utils.storage.PreferenceUtils;

/**
 * Created by liWensheng on 2018/4/24.
 */

public class MessageDelegate extends LatteDelegate {

    @BindView(R2.id.rv_mes)
    RecyclerView mRecyclerView = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_message;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        RestClient.builder()
                .loader(getContext())
                .url("http://139.199.5.153:3000/ec/mes?phone="+ PreferenceUtils.getCustomAppProfile("current"))
                .success(new ISuccess() {
                    @Override
                    public void onSuceess(String response) {
                        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
                        manager.setStackFromEnd(true);
                        manager.setReverseLayout(true);
                        mRecyclerView.setLayoutManager(manager);
                        final List<MultipleItemEntity> data =
                                new  MessageDataConverter().setJsonData(response).convert();
                        final MessageAdapter adapter = new MessageAdapter(data);
                        mRecyclerView.setAdapter(adapter);
                    }
                })
                .build().get();
    }
}
