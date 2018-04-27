package mall.online.com.latte.ec.detail.comment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;
import mall.online.com.latte.delegate.LatteDelegate;
import mall.online.com.latte.ec.R;
import mall.online.com.latte.ec.R2;
import mall.online.com.latte.ec.main.personal.order.comments.CommentsAdapter;
import mall.online.com.latte.ec.main.personal.order.comments.CommentsDataConverter;
import mall.online.com.latte.net.RestClient;
import mall.online.com.latte.net.callback.ISuccess;
import mall.online.com.latte.ui.recycler.MultipleItemEntity;

/**
 * Created by liWensheng on 2018/4/26.
 */

public class CommentInfoDelegate extends LatteDelegate {

    @BindView(R2.id.comment_rv)
    RecyclerView mRecyclerView = null;

    @BindView(R2.id.comment_count)
    TextView mCount = null;

    private final static String GOODSID = "GOODSIS";
    private String mGoodsid = "";

    public static CommentInfoDelegate create(String goodsId) {
        final Bundle args = new Bundle();
        args.putString(GOODSID, goodsId);
        final CommentInfoDelegate delegate = new CommentInfoDelegate();
        delegate.setArguments(args);
        return delegate;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        if (args != null) {
            mGoodsid = args.getString(GOODSID);
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_comment;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        RestClient.builder()
                .loader(getContext())
                .url("http://139.199.5.153:3000/ec/comments?id=" + mGoodsid)
                .success(new ISuccess() {
                    @Override
                    public void onSuceess(String response) {
                        final int size = JSON.parseObject(response).getJSONArray("data").size();
                        mCount.setText(Integer.toString(size));

                        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
                        manager.setStackFromEnd(true);
                        manager.setReverseLayout(true);
                        mRecyclerView.setLayoutManager(manager);
                        final List<MultipleItemEntity> data =
                                new CommentDataConverter().setJsonData(response).convert();
                        final CommentInfoAdapter afterAdapter = new CommentInfoAdapter(data);
                        mRecyclerView.setAdapter(afterAdapter);
                    }
                }).build().get();
    }
}
