package mall.online.com.latte.ec.sign;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;

import butterknife.BindView;
import butterknife.OnClick;
import mall.online.com.latte.delegate.LatteDelegate;
import mall.online.com.latte.ec.R;
import mall.online.com.latte.ec.R2;
import mall.online.com.latte.ec.main.EcBottomDelegate;
import mall.online.com.latte.net.RestClient;
import mall.online.com.latte.net.callback.IError;
import mall.online.com.latte.net.callback.IFailure;
import mall.online.com.latte.net.callback.ISuccess;
import mall.online.com.latte.utils.log.LogUtil;

/**
 * Created by liWensheng on 2018/2/11.
 * 登录
 */

public class SignInDelegate extends LatteDelegate {

    @BindView(R2.id.edit_sign_in_email)
    TextInputEditText mPhone = null;
    @BindView(R2.id.edit_sign_in_password)
    TextInputEditText mPassword = null;
    @BindView(R2.id.tv_sign_in)
    TextView mSignInTitle = null;

    ISignListener mISignListener = null;

    private long mExitTime = 0;
    private static final int EXIT_TIME = 2000;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISignListener) {
            mISignListener = (ISignListener) activity;
        }
    }


    @OnClick(R2.id.btn_sign_in)
    void onClickSignIn() {
        if (checkForm()) {
            RestClient.builder()
                    .url("http://139.199.5.153:3000/ec/signin?phone="
                            +mPhone.getText().toString()+"&"+
                            "password="+mPassword.getText().toString())
                    .success(new ISuccess() {
                        @Override
                        public void onSuceess(String response) {
                            int code = JSON.parseObject(response).getInteger("code");
                            if (code == 1) {
                                mPassword.setError("手机号或密码不正确");
                            } else {
                                mPassword.setError(null);
                                SignHander.onSignIn(mPhone.getText().toString(), mISignListener);
                            }
                        }
                    })
                    .build()
                    .get();
        }
    }


    @OnClick(R2.id.tv_link_sign_up)
    void onClikLink() {
        getSupportDelegate().start(new SignUpDelegate());
    }

    private boolean checkForm() {
        final String phone = mPhone.getText().toString();
        final String password = mPassword.getText().toString();

        boolean isPass = true;


        if (phone.isEmpty() || !Patterns.PHONE.matcher(phone).matches()) {
            mPhone.setError("错误的手机号");
            isPass = false;
        } else {
            mPhone.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            mPassword.setError("请填写至少6位密码");
            isPass = false;
        } else {
            mPassword.setError(null);
        }

        return isPass;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_in;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        Typeface typeface = Typeface.createFromAsset(
                getContext().getAssets(),
                "font.ttf");
        mSignInTitle.setTypeface(typeface);
    }

    @Override
    public boolean onBackPressedSupport() {
        if ((System.currentTimeMillis() - mExitTime) > EXIT_TIME) {
            Toast.makeText(getContext(), "双击退出应用", Toast.LENGTH_SHORT).show();
            mExitTime = System.currentTimeMillis();
        } else {
            _mActivity.finish();
            if (mExitTime != 0) {
                mExitTime = 0;
            }
        }
        return true;
    }
}
