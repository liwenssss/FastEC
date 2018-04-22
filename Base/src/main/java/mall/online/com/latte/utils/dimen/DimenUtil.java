package mall.online.com.latte.utils.dimen;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import mall.online.com.latte.app.Latte;

/**
 * Created by liWensheng on 2018/1/24.
 *
 */

public class DimenUtil {

    /**
     * get screen width
     * @return
     */
    public static int getScreenWidth() {
        final Resources resources = Latte.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    /**
     * get screen height
     * @return
     */
    public static int getScreenHeight() {
        final Resources resources = Latte.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }
}
