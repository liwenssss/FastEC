package mall.online.com.latte.ec.main.personal.order.comments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.OnClick;
import mall.online.com.latte.delegate.LatteDelegate;
import mall.online.com.latte.ec.R;
import mall.online.com.latte.ec.R2;
import mall.online.com.latte.ec.main.personal.order.OrderListDelegate;
import mall.online.com.latte.net.RestClient;
import mall.online.com.latte.net.callback.ISuccess;
import mall.online.com.latte.utils.log.LogUtil;
import mall.online.com.latte.utils.storage.PreferenceUtils;

/**
 * Created by liWensheng on 2018/4/26.
 */

public class CommentDelegate extends LatteDelegate {

    @BindView(R2.id.comment_content)
    EditText mContent = null;

    private final static String GOODSID = "GOODSID";
    private final static String OBJECJID = "OBJECTID";
    private String mGoodsId="";
    private String mObjectId = "";

    @OnClick(R2.id.comment_button)
    public void Commit() {
        Calendar calendar = Calendar.getInstance();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("MM月dd日 HH时mm分");
        final String date =dateFormat.format(calendar.getTime());

        final String desc = String.valueOf(mContent.getText());
        if (desc.equals("")) {
            Toast.makeText(getContext(), "请填写内容！", Toast.LENGTH_SHORT).show();
        }
        else {
            String params = "phone=" + PreferenceUtils.getCustomAppProfile("current")
                    +"&desc=" + mContent.getText()
                    +"&time=" + date
                    +"&id=" + mGoodsId;
            RestClient.builder()
                    .loader(getContext())
                    .url("http://139.199.5.153:3000/ec/comments?"+params)
                    .success(new ISuccess() {
                        @Override
                        public void onSuceess(String response) {
                            LogUtil.i("评论", "评论发布成功！");
                        }
                    }).build().post();

            RestClient.builder()
                    .loader(getContext())
                    .url("http://139.199.5.153:3000/ec/after/comment?id="+mObjectId)
                    .success(new ISuccess() {
                        @Override
                        public void onSuceess(String response) {
                            LogUtil.i("评论", "评论状态更改成功!");
                        }
                    }).build().get();

            getSupportDelegate().start(OrderListDelegate.create("evaluate"), SINGLETASK);
        }



    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        mGoodsId = args.getString(GOODSID);
        mObjectId = args.getString(OBJECJID);
    }

    public static CommentDelegate create(String goodsid, String objectId) {
        final Bundle args = new Bundle();
        args.putString(GOODSID, goodsid);
        args.putString(OBJECJID, objectId);
        final CommentDelegate delegate = new CommentDelegate();
        delegate.setArguments(args);
        return delegate;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_text_comment;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

    }
}
