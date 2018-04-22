package mall.online.com.latte.ec.main.index;

/**
 * Created by liWensheng on 2018/2/24.
 */

public class PagingBean {
    // 当前页数
    private int mPageIndex = 0;
    // 总页数
    private int mTotal = 0;
    // 每页的数据量
    private int mPageSize = 0;
    // 当前已显示数据量
    private int mCurrentCount = 0;
    // 加载延迟
    private int mDelayed = 0;

    public int getPageIndex() {
        return mPageIndex;
    }

    public PagingBean setPageIndex(int PageIndex) {
        this.mPageIndex = PageIndex;
        return this;
    }

    public int getTotal() {
        return mTotal;
    }

    public PagingBean setTotal(int Total) {
        this.mTotal = Total;
        return this;
    }

    public int getPageSize() {
        return mPageSize;
    }

    public PagingBean setPageSize(int PageSize) {
        this.mPageSize = PageSize;
        return this;
    }

    public int getCurrentCount() {
        return mCurrentCount;
    }

    public PagingBean setCurrentCount(int CurrentCount) {
        this.mCurrentCount = CurrentCount;
        return this;
    }

    public int getDelayed() {
        return mDelayed;
    }

    public PagingBean setDelayed(int Drlayed) {
        this.mDelayed = Drlayed;
        return this;
    }

    PagingBean addIndex() {
        mPageIndex++;
        return this;
    }
}
