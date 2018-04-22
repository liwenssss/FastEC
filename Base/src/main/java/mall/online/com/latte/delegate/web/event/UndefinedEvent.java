package mall.online.com.latte.delegate.web.event;

import mall.online.com.latte.utils.log.LogUtil;

/**
 * Created by liWensheng on 2018/2/26.
 */

public class UndefinedEvent extends Event {
    @Override
    public String execute(String params) {
        LogUtil.e("UndefinedEvent", params);
        return null;
    }
}
