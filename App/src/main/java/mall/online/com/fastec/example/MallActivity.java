package mall.online.com.fastec.example;

import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import mall.online.com.latte.activities.ProxyActivity;
import mall.online.com.latte.app.Latte;
import mall.online.com.latte.delegate.LatteDelegate;
import mall.online.com.latte.ec.launcher.LauncherDelegate;
import mall.online.com.latte.ec.launcher.LauncherScrollDelegate;
import mall.online.com.latte.ec.main.EcBottomDelegate;
import mall.online.com.latte.ec.sign.ISignListener;
import mall.online.com.latte.ec.sign.SignInDelegate;
import mall.online.com.latte.ec.sign.SignUpDelegate;
import mall.online.com.latte.ui.launcher.ILauncherListener;
import mall.online.com.latte.ui.launcher.OnLauncherFinishTag;
import qiu.niorgai.StatusBarCompat;

public class MallActivity extends ProxyActivity implements
        ISignListener,
        ILauncherListener
{


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        Latte.getConfigurator().withActivity(this);
        StatusBarCompat.translucentStatusBar(this, true);
    }

    @Override
    public LatteDelegate setRootDelegate() {
        return new LauncherDelegate();
    }

    @Override
    public void onSignInSuccess() {
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSignUpSuccess() {
        Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLauncherFinish(OnLauncherFinishTag tag) {
        switch (tag) {
            case SIGNED:
                Toast.makeText(this, "启动结束,用户登录", Toast.LENGTH_LONG).show();
                startWithPop(new EcBottomDelegate());
                break;
            case NOT_SIGNED:
                Toast.makeText(this, "启动结束,用户没登录", Toast.LENGTH_LONG).show();
                startWithPop(new SignInDelegate());
                break;
            default:
                break;
        }
    }
}
