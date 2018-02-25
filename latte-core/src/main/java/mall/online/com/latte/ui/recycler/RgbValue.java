package mall.online.com.latte.ui.recycler;

import com.google.auto.value.AutoValue;

/**
 * Created by liWensheng on 2018/2/25.
 * 存储颜色信息
 */
@AutoValue
public abstract class RgbValue {

    public abstract int red();

    public abstract int green();

    public abstract int blue();

    public static RgbValue create(int red, int green, int blue) {
        return new AutoValue_RgbValue(red, green, blue);
    }
}
