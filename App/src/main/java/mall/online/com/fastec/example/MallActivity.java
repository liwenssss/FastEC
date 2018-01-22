package mall.online.com.fastec.example;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import mall.online.com.latte.activities.ProxyActivity;
import mall.online.com.latte.app.Latte;
import mall.online.com.latte.delegate.LatteDelegate;

public class MallActivity extends ProxyActivity {


    @Override
    public LatteDelegate setRootDelete() {
        return new MallDelegate();
    }
}
