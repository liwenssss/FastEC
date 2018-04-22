package mall.online.com.latte.ec.main.sort.content;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import mall.online.com.latte.delegate.LatteDelegate;
import mall.online.com.latte.ec.R;
import mall.online.com.latte.ec.R2;
import mall.online.com.latte.net.GetRequest;
import mall.online.com.latte.net.RestClient;
import mall.online.com.latte.net.callback.ISuccess;
import okhttp3.ResponseBody;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by liWensheng on 2018/2/25.
 */

public class ContentDelegate extends LatteDelegate {

    private static final String ARG_CONTENT_ID = "CONTENT_ID";
    private int mContentId = -1;
    private List<SectionBean> mData = null;

    @BindView(R2.id.rv_list_content)
    RecyclerView mRecyclerView = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        if (args != null) {
            mContentId = args.getInt(ARG_CONTENT_ID);
        }
    }

    public static ContentDelegate newInstance(int contentId) {
        final Bundle args = new Bundle();
        args.putInt(ARG_CONTENT_ID, contentId);
        final ContentDelegate delegate = new ContentDelegate();
        delegate.setArguments(args);
        return delegate;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_list_content;
    }

    /**
     * 初始化数据
     */
    private void initData() {
        RestClient.builder()
                .url("http://139.199.5.153:3000/ec/sort_content?sortId="+mContentId)
                .success(new ISuccess() {
                    @Override
                    public void onSuceess(String response) {
                        mData = new  SectionDataConverter().converter(response);
                        final SectionAdapter sectionAdapter = new SectionAdapter(
                                R.layout.item_section_content,
                                R.layout.item_section_header,
                                mData);

                        mRecyclerView.setAdapter(sectionAdapter);
                    }
                }).build().get();

    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        final StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        initData();
    }
}
