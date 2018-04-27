package mall.online.com.latte.ec.main.index.search.ans;

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

/**
 * Created by liWensheng on 2018/4/27.
 */

public class SearchAnsDelegate extends LatteDelegate {

    private static final String SEARCH = "SEARCH";
    private String mSearch = "";

    @BindView(R2.id.search_rv)
    RecyclerView mRecyclerView = null;

    public static SearchAnsDelegate create(String str) {
        final Bundle args = new Bundle();
        args.putString(SEARCH, str);
        final SearchAnsDelegate delegate = new SearchAnsDelegate();
        delegate.setArguments(args);
        return delegate;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        mSearch = args.getString(SEARCH);
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_search_ans;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        RestClient.builder()
                .loader(getContext())
                .url("http://139.199.5.153:3000/ec/search?str=" + mSearch)
                .success(new ISuccess() {
                    @Override
                    public void onSuceess(String response) {
                        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
                        mRecyclerView.setLayoutManager(manager);
                        final List<MultipleItemEntity> data =
                                new AnsDataConverter().setJsonData(response).convert();
                        final AnsRecyclerAdapter afterAdapter = new AnsRecyclerAdapter(data, getDelegate());
                        mRecyclerView.setAdapter(afterAdapter);
                    }
                }).build().get();
    }

    private SearchAnsDelegate getDelegate() {
        return this;
    }
}
