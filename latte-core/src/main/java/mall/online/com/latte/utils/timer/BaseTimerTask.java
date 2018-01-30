package mall.online.com.latte.utils.timer;

import java.util.TimerTask;

/**
 * Created by liWensheng on 2018/1/30.
 */

public class BaseTimerTask extends TimerTask {

    private ITimerListener mItimerListener = null;

    public BaseTimerTask(ITimerListener timerListener) {
        this.mItimerListener = timerListener;
    }

    @Override
    public void run() {
        if (mItimerListener != null) {
            mItimerListener.onTimer();
        }
    }
}
