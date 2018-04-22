package mall.online.com.latte.delegate;

/**
 * Created by liWensheng on 2018/1/22.
 */

public abstract class LatteDelegate extends PermissionCheckDelegate {

    public <T extends LatteDelegate> T getParentDelegate() {
        return (T) getParentFragment();
    }
}
