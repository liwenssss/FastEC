package mall.online.com.latte.ec.main.cart.order;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Patterns;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.style.citypickerview.CityPickerView;

import butterknife.BindView;
import butterknife.OnClick;
import mall.online.com.latte.delegate.LatteDelegate;
import mall.online.com.latte.ec.R;
import mall.online.com.latte.ec.R2;
import mall.online.com.latte.net.RestClient;
import mall.online.com.latte.net.callback.ISuccess;
import mall.online.com.latte.utils.storage.PreferenceUtils;

/**
 * Created by liWensheng on 2018/4/23.
 */

public class ReceiveInfo extends LatteDelegate {

    @BindView(R2.id.position_name)
    TextInputEditText PositionName = null;
    @BindView(R2.id.position_phone)
    TextInputEditText PositionPhone = null;
    @BindView(R2.id.xiangxi_position)
    TextInputEditText Position_Xiangxi = null;

    @BindView(R2.id.position)
    TextView Position_diqu = null;

    private CityPickerView cityPickerView = new CityPickerView();

    private final String phoneId = PreferenceUtils.getCustomAppProfile("current");

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cityPickerView.init(getContext());
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_receive;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        if (PreferenceUtils.getAppFlag(phoneId+"position")) {
            PositionName.setText(PreferenceUtils.getCustomAppProfile(phoneId+"name"));
            PositionPhone.setText(PreferenceUtils.getCustomAppProfile(phoneId+"phone"));
            Position_diqu.setText(PreferenceUtils.getCustomAppProfile(phoneId+"diqu"));
            Position_Xiangxi.setText(PreferenceUtils.getCustomAppProfile(phoneId+"xiangxi"));
        }
    }

    @OnClick(R2.id.select_position)
    public void SelectPos() {
        CityConfig cityConfig = new CityConfig.Builder().build();
        cityConfig.setDefaultProvinceName("广东省");
        cityConfig.setDefaultCityName("广州市");
        cityConfig.setDefaultDistrict("番禺区");
        cityPickerView.setConfig(cityConfig);


        cityPickerView.showCityPicker();
        cityPickerView.setOnCityItemClickListener(new OnCityItemClickListener() {
            @Override
            public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
                String pos = "";
                if (province != null) {
                    pos += province.getName();
                }
                if (city != null) {
                    pos += city.getName();
                }
                if (district != null) {
                    pos += district.getName();
                }
                Position_diqu.setText(pos);
            }

            @Override
            public void onCancel() {
                super.onCancel();
            }
        });
    }

    @OnClick(R2.id.save_address)
    public void SaveAddress() {
        if (check()) {
            PreferenceUtils.setAppFlag(phoneId+"position", true);
            PreferenceUtils.addCustomAppProfile(phoneId+"name", PositionName.getText().toString());
            PreferenceUtils.addCustomAppProfile(phoneId+"phone", PositionPhone.getText().toString());
            PreferenceUtils.addCustomAppProfile(phoneId+"diqu", Position_diqu.getText().toString());
            PreferenceUtils.addCustomAppProfile(phoneId+"xiangxi", Position_Xiangxi.getText().toString());
            Toast.makeText(getContext(), "保存成功", Toast.LENGTH_SHORT).show();
            getSupportDelegate().start(new OrderDelegate(), SINGLETASK);
        }
    }

    private boolean check() {
        final String name = PositionName.getText().toString();
        final String phone = PositionPhone.getText().toString();
        final String diqu = Position_diqu.getText().toString();
        final String xiangxi = Position_Xiangxi.getText().toString();

        boolean flag = true;

        if (name.isEmpty()) {
            PositionName.setError("姓名不能为空");
            flag = false;
        } else {
            PositionName.setError(null);
        }

        if (phone.isEmpty() || !Patterns.PHONE.matcher(phone).matches()) {
            PositionPhone.setError("错误的手机格式");
            flag = false;
        } else {
            PositionPhone.setError(null);
        }

        if (diqu.isEmpty()) {
            Position_diqu.setError("地区选择不能为空");
            flag = false;
        } else {
            Position_diqu.setError(null);
        }

        if (xiangxi.isEmpty()) {
            Position_Xiangxi.setError("详细地址不能为空");
            flag = false;
        } else {
            Position_Xiangxi.setError(null);
        }

        return flag;
    }



}
