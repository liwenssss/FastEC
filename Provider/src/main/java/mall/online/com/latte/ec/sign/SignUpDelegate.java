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
import com.alibaba.fastjson.JSONObject;

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
 * Created by liWensheng on 2018/2/10.
 * 注册
 */

public class SignUpDelegate extends LatteDelegate {

    @BindView(R2.id.edit_sign_up_name)
    TextInputEditText mName = null;
    @BindView(R2.id.edit_sign_up_email)
    TextInputEditText mEmail = null;
    @BindView(R2.id.edit_sign_up_phone)
    TextInputEditText mPhone = null;
    @BindView(R2.id.edit_sign_up_password)
    TextInputEditText mPassword = null;
    @BindView(R2.id.edit_sign_up_re_password)
    TextInputEditText mRePassword = null;
    @BindView(R2.id.tv_sign_up)
    TextView mSignUpTitle = null;

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

    @OnClick(R2.id.btn_sign_up)
    void onClickSignUp() {
        if (checkForm()) {
            RestClient.builder()
                    .url("http://139.199.5.153:3000/ec/signup?" +
                            "name="+mName.getText().toString()+"&"+
                            "gender=1&"+"email="+mEmail.getText().toString()+"&"+
                            "phone="+mPhone.getText().toString()+"&"+
                            "password="+mPassword.getText().toString())
                    .success(new ISuccess() {
                        @Override
                        public void onSuceess(String response) {
                            LogUtil.json("USER_PROFILE", response);
                            final int code = JSON.parseObject(response).getInteger("code");
                            if (code == 0) {
                                mName.setError(null);
                                mPhone.setError(null);
                                SignHander.onSignUp(mPhone.getText().toString(), mISignListener);
                            }
                            else if (code == 1) {
                                mName.setError("该用户名已被注册！");
                            }
                            else if (code == 2) {
                                mPhone.setError("该手机号已被注册！");
                            }
                        }
                    })
                    .failure(new IFailure() {
                        @Override
                        public void onFailure() {
                            Log.i("fail", "fail: ");
                        }
                    })
                    .error(new IError() {
                        @Override
                        public void onError(int code, String msg) {
                            Log.i("error", "error: ");
                        }
                    })
                    .build()
                    .post();
//            Toast.makeText(getContext(), "验证通过", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R2.id.tv_link_sign_in)
    void onClikLink() {
        getSupportDelegate().start(new SignInDelegate());
    }

    /**
     * 校验输入样式
     * @return
     */
    private boolean checkForm() {
        final String name = mName.getText().toString();
        final String email = mEmail.getText().toString();
        final String phone = mPhone.getText().toString();
        final String password = mPassword.getText().toString();
        final String rePassword = mRePassword.getText().toString();

        boolean isPass = true;

        if (name.isEmpty()) {
            mName.setError("请输入姓名");
            isPass = false;
        }  else {
            mName.setError(null);
        }

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError("错误的邮箱格式");
            isPass = false;
        } else {
            mEmail.setError(null);
        }

        if (phone.isEmpty() || phone.length() != 11) {
            mPhone.setError("手机号码错误");
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

        if (rePassword.isEmpty() || rePassword.length() < 6 || !rePassword.equals(password)) {
            mRePassword.setError("密码验证错误");
            isPass = false;
        } else {
            mRePassword.setError(null);
        }

        return isPass;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_up;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        Typeface typeface = Typeface.createFromAsset(
                getContext().getAssets(),
                "font.ttf");
        mSignUpTitle.setTypeface(typeface);
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
